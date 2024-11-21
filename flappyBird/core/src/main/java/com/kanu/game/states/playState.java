package com.kanu.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.kanu.game.Core;
import sprites.bird;
import sprites.tube;

public class playState extends state{
    private static final float TUBE_SPACING=125;
    private static final float TUBE_COUNT=4;
    private static final int GROUND_Y_OFFSET=-50;

    private bird bird;
    private Texture background;
    private tube tube;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;

    private Array<tube> tubes;
    public playState(GameStateManager gsm){
        super(gsm);
        bird=new bird(50,100);
        background=new Texture("bg.png");
        tube=new tube(100);
        ground=new Texture("ground.png");
        groundPos1=new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2=new Vector2((cam.position.x-cam.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);
        cam.setToOrtho(false, Core.WIDTH/2,Core.HEIGHT/2);
        tubes=new Array<tube>();
        for(int i=0;i<TUBE_COUNT;i++){
            tubes.add(new tube(i*(TUBE_SPACING+tube.TUBE_WIDTH)));


        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
            return;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x=bird.getPosition().x+80;
        cam.update();

        for(int i=0;i<tubes.size;i++){
            tube tube=tubes.get(i);
           if(cam.position.x-(cam.viewportWidth/2)>tube.getPosTopTube().x+tube.TUBE_WIDTH){
               tube.reposition((tube.getPosBotTube().x+(tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
           }
           if(tube.collides((bird.getBounds()))){
               gsm.set(new playState(gsm));
           }
        }
        if(bird.getPosition().y<=ground.getHeight()+GROUND_Y_OFFSET){
            gsm.set(new playState(gsm));
        }
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        for (tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for(tube tube:tubes){
            tube.dispose();
        }
        System.out.println("play state disposed");
    }
    private void updateGround(){
        if(cam.position.x-(cam.viewportWidth/2)>groundPos1.x+ground.getWidth()){
            groundPos1.add(ground.getWidth()*2,0);
        }
        if(cam.position.x-(cam.viewportWidth/2)>groundPos2.x+ground.getWidth()){
            groundPos2.add(ground.getWidth()*2,0);
        }
    }
}

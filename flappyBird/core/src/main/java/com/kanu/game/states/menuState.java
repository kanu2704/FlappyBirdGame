package com.kanu.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kanu.game.Core;


public class menuState extends state{
    private Texture background;
    private Texture playBtn;
    public menuState(GameStateManager gsm){
        super(gsm);
        background=new Texture("bg.png");
        playBtn=new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new playState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0,Core.WIDTH, Core.HEIGHT);
        sb.draw(playBtn,Core.WIDTH/2-playBtn.getWidth()/2,Core.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("menu state disposed");
    }
}

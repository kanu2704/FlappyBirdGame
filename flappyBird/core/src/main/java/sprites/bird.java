package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class bird {
    private Vector3 position;
    private Vector3 velocity;
    private final float GRAVITY= -9.8F;
    private final float JUMP_VELOCITY=250.0F;
    private static final int MOVEMENT=100;
    private Texture bird;
    private Rectangle bounds;

    public bird(int x,int y){
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        bird=new Texture("bird.png");
        bounds=new Rectangle(x,y,bird.getWidth(),bird.getHeight());

    }
    public void update(float dt) {
        // Apply gravity to the velocity
        if(position.y>0){
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);

        // Apply velocity to position
        position.add(MOVEMENT*dt, velocity.y, 0);

        // Prevent the bird from going below the ground level
        if (position.y < 0) {
            position.y = 0;
            velocity.y = 0; // Reset the velocity when it hits the ground
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }



    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
    public void jump(){
        velocity.y=JUMP_VELOCITY;
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void dispose(){
        bird.dispose();
    }
}

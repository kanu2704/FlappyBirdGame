package com.kanu.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kanu.game.states.GameStateManager;
import com.kanu.game.states.menuState;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends ApplicationAdapter {
    public static final int WIDTH=480;
    public static final int HEIGHT=700;

    public static final String TITLE="flappy Bird";
    private GameStateManager gsm;

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        gsm=new GameStateManager();
        gsm.push(new menuState(gsm));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Update game state manager
        gsm.update(Gdx.graphics.getDeltaTime());

        // Render the game state
        gsm.render(batch);


    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

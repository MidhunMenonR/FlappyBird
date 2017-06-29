package com.android.midhun.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.android.midhun.flappybird.states.GameStateManager;
import com.android.midhun.flappybird.states.MenuState;

public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH= 480;
	public static final int HEIGHT= 800;
	public static final String TITLE= "Flappy";

	private static Preferences prefs;

	public static int score;

    private GameStateManager gsm;

	private SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm=new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);

		prefs = Gdx.app.getPreferences("FlappyBird");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}


		gsm.push(new MenuState(gsm));


    }

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();

	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
}


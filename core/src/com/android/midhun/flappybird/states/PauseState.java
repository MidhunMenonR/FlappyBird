package com.android.midhun.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.android.midhun.flappybird.FlappyBird;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by midhun on 29/6/17.
 */

public class PauseState extends State {
    private Texture background;
    private Texture playbtn;

    boolean playsound=false;

    BitmapFont gameOver;
    private static GlyphLayout glyphLayout = new GlyphLayout();



    public PauseState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH,FlappyBird.HEIGHT);
        background=new Texture("gobg.png");
        playbtn=new Texture("restart.png");

        gameOver = new BitmapFont(Gdx.files.internal("myfont.fnt"));

    }



    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            if (FlappyBird.score>FlappyBird.getHighScore())
                FlappyBird.setHighScore(FlappyBird.score);
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        ShapeRenderer shapeRenderer=new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(80 / 255.0f, 80 / 255.0f, 50 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 160, 160);
        shapeRenderer.end();
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(playbtn,cam.position.x-playbtn.getWidth()/2,cam.position.y-300);

        glyphLayout.setText(gameOver, "Game Over");
        gameOver.draw(sb, glyphLayout, cam.position.x-glyphLayout.width/2, 700);

        glyphLayout.setText(gameOver, "Score: "+FlappyBird.score);
        gameOver.draw(sb, glyphLayout, cam.position.x-glyphLayout.width/2, 600);

        glyphLayout.setText(gameOver, "High Score: "+FlappyBird.getHighScore());
        gameOver.draw(sb, glyphLayout, cam.position.x-glyphLayout.width/2, 500);

        if (FlappyBird.score>FlappyBird.getHighScore()){
            glyphLayout.setText(gameOver, "New High-Score!");
            gameOver.draw(sb, glyphLayout, cam.position.x-glyphLayout.width/2, 400);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();

    }

}



package com.android.midhun.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by midhun on 28/6/17.
 */

public class Tube {
    private static final int FLUCTUATION = 330;
    private static final int TUBE_GAP = 200;
    private static final int LOWEST = 200;
    public static final int TUBE_WIDTH = 228;
    private Texture topTube,bottomTube;
    private Vector2 posTop,posBot,posScore;
    private Rectangle boundsTop,boundsBot,boundsScore;
    private Random rand;

    public Tube (float x){
        topTube=new Texture("obstacle_top.png");
        bottomTube=new Texture("obstacle_bottom.png");
        rand=new Random();

        posTop=new Vector2(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST);
        posBot=new Vector2(x,posTop.y-TUBE_GAP-bottomTube.getHeight());
        posScore=new Vector2(x,posTop.y-TUBE_GAP);

        boundsTop=new Rectangle(posTop.x,posTop.y,topTube.getWidth(),topTube.getHeight());
        boundsBot=new Rectangle(posBot.x,posBot.y,bottomTube.getWidth(),bottomTube.getHeight());
        boundsScore=new Rectangle(posScore.x,posScore.y,bottomTube.getWidth(),TUBE_GAP);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTop() {
        return posTop;
    }

    public Vector2 getPosBot() {
        return posBot;
    }

    public void reposition(float  x){
        posTop.set(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST);
        posBot.set(x,posTop.y-TUBE_GAP-bottomTube.getHeight());
        posScore.set(x,posTop.y-TUBE_GAP);

        boundsTop.setPosition(posTop.x,posTop.y);
        boundsBot.setPosition(posBot.x,posBot.y);
        boundsScore.setPosition(posScore.x,posScore.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop)|| player.overlaps(boundsBot);
    }

    public boolean incScore(Rectangle player){
        return boundsScore.contains(player);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}

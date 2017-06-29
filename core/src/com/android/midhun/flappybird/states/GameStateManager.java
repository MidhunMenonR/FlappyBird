package com.android.midhun.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by midhun on 28/6/17.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states=new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
         states.peek().update(dt);
    }

    public void render(SpriteBatch sp){
        states.peek().render(sp);
    }

}

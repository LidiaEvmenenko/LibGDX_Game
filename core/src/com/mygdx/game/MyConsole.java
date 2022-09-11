package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
public class MyConsole implements InputProcessor{
    private Body bodyHero;
    private AnimHero animHero;


    public MyConsole(Body bodyHero, AnimHero animHero) {
        this.bodyHero = bodyHero;
        this.animHero = animHero;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Input.Keys.LEFT:
                animHero.setAnimationHero("go");
                bodyHero.applyForceToCenter(new Vector2(-500000, 0), true);
                animHero.setLeftMove(true);
                break;
            case Input.Keys.RIGHT:
                animHero.setAnimationHero("go");
                bodyHero.applyForceToCenter(new Vector2(500000, 0), true);
                animHero.setRightMove(true);
                break;
            case Input.Keys.UP:
                animHero.setAnimationHero("jump");
                bodyHero.getFixtureList().get(0).setRestitution(0.5f);
                bodyHero.applyForceToCenter(new Vector2(10, 700000), true);
                break;
        }

        return true;

    }

    @Override
    public boolean keyUp(int keycode) {

        animHero.setAnimationHero("sleep");
        switch (keycode)
        {
            case Input.Keys.LEFT:
                animHero.setLeftMove(false);
                break;
            case Input.Keys.RIGHT:
                animHero.setRightMove(false);
                break;
        }
        return true;

    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

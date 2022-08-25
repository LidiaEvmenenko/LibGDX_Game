package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Anim;
import com.mygdx.game.Main;

public class GameScreen implements Screen {
    private Vector2 pos;
    private Vector2 v;
    private boolean dir=false;
    private Anim animation;
    private Main game;
    private SpriteBatch batch;

    public GameScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        System.out.println("Ggame = "+game);
    }

    @Override
    public void show() {
        animation = new Anim("atlas/unnamed.atlas", Animation.PlayMode.LOOP);
        pos = new Vector2();
        v = new Vector2(1, 0);
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            System.out.println("batch = "+batch);
            dispose();
            game.setScreen(new MenuScreen(this.game));}
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) { dir = true; }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) { dir = false; }
        if (!animation.getFrame().isFlipX() && dir) {
            animation.getFrame().flip(true, false);
            v.set(-1, 0);
        }//налево
        if (animation.getFrame().isFlipX() && !dir) {
            animation.getFrame().flip(true, false);
            v.set(1, 0);
        }//направо
        animation.setTime(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(0.5F, 0.2f, 1, 1);
        batch.begin();
        batch.draw(animation.getFrame(), pos.x, pos.y);
        batch.end();
        pos.add(v);
        if (pos.x >= Gdx.graphics.getWidth() - animation.getFrame().getRegionWidth()){
            v.set(-1, 0);
            animation.getFrame().flip(true, false);
            dir= !dir;
        }
        if (pos.x == 0){
            v.set(1, 0);
            animation.getFrame().flip(true, false);
            dir= !dir;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

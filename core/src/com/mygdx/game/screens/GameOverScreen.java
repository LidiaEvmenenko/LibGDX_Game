package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Main;

public class GameOverScreen implements Screen{
    private Texture img;
    private Texture imgOver;
    private SpriteBatch batch;
    private Main game;
    private Rectangle overRect;
    private Rectangle endRect;
    private float xOverRect;
    private float yOverRect;
    private float xEndRect;
    private float yEndRect;

    private Music music;

    public GameOverScreen(Main game) {
        super();
        this.game = game;
        batch = new SpriteBatch();
        xOverRect = 200;
        yOverRect = 225;
        overRect = new Rectangle(xOverRect, yOverRect, 260,80);
        xEndRect = 280;
        yEndRect = 95;
        endRect = new Rectangle(xEndRect, yEndRect, 90,90);
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/game-over.mp3"));
        music.setLooping(true);
        music.setVolume(0.05f);
        music.play();
    }
    @Override
    public void show() {
        imgOver = new Texture("End.png");
    }

    @Override
    public void render(float delta) {
        if ((Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))){
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (overRect.contains(x, y)){
                dispose();
                game.setScreen(new MenuScreen(this.game));
            }
            if (endRect.contains(x, y)){
                dispose();
                System.exit(0);
            }
        }
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        batch.draw(imgOver, 0,0);
        batch.end();
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
        imgOver.dispose();
        batch.dispose();
        music.dispose();
    }
}

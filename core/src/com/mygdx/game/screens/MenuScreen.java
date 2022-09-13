package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.*;

public class MenuScreen implements Screen {
    private Texture imgStart;
    private SpriteBatch batch;
    private Main game;
    private Rectangle startRect;
    private float xStart;
    private float yStart;
    private Music music;


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

    public MenuScreen(Main game) {
        super();
        this.game = game;
        batch = new SpriteBatch();
        xStart = 215;
        yStart = 185;
        startRect = new Rectangle(xStart, yStart,
                210, 90);
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/Kalimba.mp3"));
        music.setLooping(true);// зациклить музыку
        music.setVolume(0.05f);//громкость
        music.play();//воспроизведение

    }

    @Override
    public void show() {
        imgStart = new Texture("Start.png");
    }

    @Override
    public void render(float delta) {
        if ((Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))){
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (startRect.contains(x, y)){
                dispose();
                game.setScreen(new GameScreen(this.game));
            }
        }
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        batch.draw(imgStart, 0,0);
        batch.end();
    }

    @Override
    public void dispose() {
        imgStart.dispose();
        batch.dispose();
        music.dispose();
    }
}

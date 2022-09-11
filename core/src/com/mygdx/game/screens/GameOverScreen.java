package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Main;

//import static com.badlogic.gdx.Gdx.graphics;
public class GameOverScreen implements Screen{
    private Texture img;
    private Texture imgOver;
    private Texture imgBtnOver;
    private SpriteBatch batch;
    private Vector2 vStartBtn;
    private Main game;
    private Rectangle overRect;
    private float xStart;
    private float yStart;
    private Music music;

    public GameOverScreen(Main game) {
        super();
        this.game = game;
        batch = new SpriteBatch();
        imgBtnOver = new Texture("over.png");
        vStartBtn = new Vector2();
        xStart = (Gdx.graphics.getWidth() - imgBtnOver.getWidth()) / 2;
        yStart = (Gdx.graphics.getHeight() - imgBtnOver.getHeight()) / 2;
        //  shapeRenderer = new ShapeRenderer();
        overRect = new Rectangle(xStart, yStart, imgBtnOver.getWidth(), imgBtnOver.getHeight());
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/game-over.mp3"));
        music.setLooping(true);// зациклить музыку
        music.setVolume(0.05f);//громкость
        music.play();
    }
    @Override
    public void show() {
        imgOver = new Texture("over.png");
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
        }
        ScreenUtils.clear(0.3f, 0.9f, 1, 1);
        batch.begin();
        //   batch.draw(img, 0,0);
        batch.draw(imgOver, xStart,yStart);
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
        imgBtnOver.dispose();
        imgOver.dispose();
        batch.dispose();
        music.dispose();
    }
}

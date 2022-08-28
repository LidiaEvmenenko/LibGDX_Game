package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Anim;
import com.mygdx.game.Main;
import com.mygdx.game.base.BaseScreen;
public class MenuScreen implements Screen {
    private Texture img;
    private Texture imgStart;
    private Texture imgBtnStart;
    private SpriteBatch batch;
    private Vector2 vStartBtn;
    private Main game;
    private Rectangle startRect;
    private float xStart;
    private float yStart;

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
        imgBtnStart = new Texture("start.png");
        vStartBtn = new Vector2();
        xStart = (Gdx.graphics.getWidth() - imgBtnStart.getWidth())/2;
        yStart = (Gdx.graphics.getHeight() - imgBtnStart.getHeight())/2;
        //  shapeRenderer = new ShapeRenderer();
        startRect = new Rectangle(xStart, yStart,
                imgBtnStart.getWidth(), imgBtnStart.getHeight());
    }

    @Override
    public void show() {
        imgStart = new Texture("start.png");
    }

    @Override
    public void render(float delta) {
        if ((Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))){
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (startRect.contains(x, y)){
                dispose();
                System.out.println("Mgame = "+game);
                game.setScreen(new GameScreen(this.game));
            }
        }
        ScreenUtils.clear(0.3f, 0.9f, 1, 1);
        batch.begin();
        batch.draw(imgStart, xStart,yStart);
        batch.end();
    }

    @Override
    public void dispose() {
        imgBtnStart.dispose();
        imgStart.dispose();
        batch.dispose();
    }
}
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Vector2 mapPosition;
    private float STEP = 3;
    private Rectangle mapSize;

    public GameScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        map = new TmxMapLoader().load("map/карта2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        map.getLayers().get("объекты").getObjects().getByType(RectangleMapObject.class);//выбор объектов по типу
        RectangleMapObject tmp = (RectangleMapObject) map.getLayers().get("объекты").getObjects().get("камера");//выбор объектов по имени

        camera.position.x = tmp.getRectangle().x;
        camera.position.y = tmp.getRectangle().y;

        tmp = (RectangleMapObject) map.getLayers().get("объекты").getObjects().get("граница");
        mapSize = tmp.getRectangle();
    }

    @Override
    public void show() {
        animation = new Anim("atlas/unnamed.atlas", Animation.PlayMode.LOOP);
        pos = new Vector2();
        v = new Vector2(1, 0);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            dispose();
            game.setScreen(new MenuScreen(this.game));}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                mapSize.width*2 -animation.getFrame().getRegionWidth() > camera.position.x-1) camera.position.x += STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mapSize.x+animation.getFrame().getRegionWidth()/2 < camera.position.x+1) camera.position.x -= STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && mapSize.y+animation.getFrame().getRegionWidth()/2 < camera.position.y-1) camera.position.y -= STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                && mapSize.height - animation.getFrame().getRegionWidth()/2 > camera.position.y+1) camera.position.y += STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.P)) camera.zoom += 0.01f;
        if (Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0) camera.zoom -= 0.01f;
        camera.update();

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) { dir = true; }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) { dir = false; }
        if (!animation.getFrame().isFlipX() && dir) {
            animation.getFrame().flip(true, false);

        }//налево
        if (animation.getFrame().isFlipX() && !dir) {
            animation.getFrame().flip(true, false);

        }//направо
        animation.setTime(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(0.5F, 0.2f, 1, 1);
        mapRenderer.setView(camera);
        mapRenderer.render();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(animation.getFrame(), camera.position.x-animation.getFrame().getRegionWidth()/2,
                camera.position.y-animation.getFrame().getRegionHeight()/2);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
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

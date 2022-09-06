package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Anim;
import com.mygdx.game.Main;
import com.mygdx.game.PhysX;

import java.util.ArrayList;
import java.util.List;

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
    private int[] bg;
    private int[] l1;

    private PhysX physX;
    private Body bodyHero;
    private Body bodyAAA;
    private Body bodyScull;
    private Body bodyEared1;
    private Rectangle heroRect;
    private Rectangle aaaRect;
    private Rectangle scullRect;
    private Rectangle earedRect1;
    public static List<Body> bodies;
    private Sound sound;
    private long id;

    public GameScreen(Main game) {
        sound = Gdx.audio.newSound(Gdx.files.internal("sound/skrip-otkryivayuscheysya-dveri.mp3"));
        sound.play();
        bodies = new ArrayList<>();
        animation = new Anim("atlas/unnamed.atlas", Animation.PlayMode.LOOP);
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.25f;
        map = new TmxMapLoader().load("map/карта2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        bg = new int[1];
        bg[0] = map.getLayers().getIndex("фон");
        l1 = new int[2];
        l1[0] = map.getLayers().getIndex("Слой 2");
        l1[1] = map.getLayers().getIndex("Слой 3");
        physX = new PhysX();
        map.getLayers().get("объекты").getObjects().getByType(RectangleMapObject.class);//выбор объектов по типу
        RectangleMapObject tmpHero = (RectangleMapObject) map.getLayers().get("сеттинг").getObjects().get("hero");//выбор объектов по имени
        RectangleMapObject tmpAAA = (RectangleMapObject) map.getLayers().get("сеттинг").getObjects().get("aaa");//выбор объектов по имени
        RectangleMapObject tmpScull = (RectangleMapObject) map.getLayers().get("сеттинг").getObjects().get("scull");//выбор объектов по имени
        RectangleMapObject tmpEared1 = (RectangleMapObject) map.getLayers().get("сеттинг").getObjects().get("eared1");//выбор объектов по имени

        heroRect = tmpHero.getRectangle();
        bodyHero = physX.addObject(tmpHero);

        aaaRect = tmpAAA.getRectangle();
        bodyAAA = physX.addObject(tmpAAA);

        scullRect = tmpScull.getRectangle();
        bodyScull = physX.addObject(tmpScull);

        earedRect1 = tmpEared1.getRectangle();
        bodyEared1 = physX.addObject(tmpEared1);

        Array<RectangleMapObject> objects =  map.getLayers().get("объекты").getObjects().getByType(RectangleMapObject.class);
        for (int i = 0; i < objects.size; i++) {
            physX.addObject(objects.get(i));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        animation.setAnimation("sleep");
        sound.stop(id);
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            dispose();
            game.setScreen(new MenuScreen(this.game));}

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            animation.setAnimation("go");
            dir = true;
            bodyHero.applyForceToCenter(new Vector2(-10000, 0), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            animation.setAnimation("go");
            dir = false;
            bodyHero.applyForceToCenter(new Vector2(10000, 0), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) ) {
            animation.setAnimation("jump");
            bodyHero.applyForceToCenter(new Vector2(0, 30000), true);
            sound = Gdx.audio.newSound(Gdx.files.internal("sound/teleportatsii.mp3"));
            id = sound.play(0.5f);
            sound.play(0.5f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.P)) camera.zoom += 0.01f;
        if (Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0) camera.zoom -= 0.01f;

        camera.position.x = bodyHero.getPosition().x* physX.PPM;
        camera.position.y = bodyHero.getPosition().y* physX.PPM;
        camera.update();

        if (!animation.getFrame().isFlipX() && dir) {
            animation.getFrame().flip(true, false);

        }//налево
        if (animation.getFrame().isFlipX() && !dir) {
            animation.getFrame().flip(true, false);

        }//направо
        animation.setTime(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(0.5F, 0.2f, 1, 1);
        mapRenderer.setView(camera);
        mapRenderer.render(bg);

        batch.setProjectionMatrix(camera.combined);
        heroRect.x = bodyHero.getPosition().x - heroRect.width/2;
        heroRect.y = bodyHero.getPosition().y - heroRect.height/2;

        batch.begin();
        batch.draw(animation.getFrame(), heroRect.x, heroRect.y, heroRect.width, heroRect.height);
        batch.end();

        mapRenderer.render(l1);
        physX.step();
        physX.debugDraw(camera);
        for (int i = 0; i < bodies.size(); i++) {
            physX.destroyBody(bodies.get(i));
        }
        bodies.clear();
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
        sound.dispose();
    }
}

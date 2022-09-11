package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.mygdx.game.*;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private Vector2 posDragon;
    private Vector2 posBeetle;
    private Vector2 posHedgehog;
    private Vector2 vDragon;
    private Vector2 vBeetle;
    private Vector2 vHedgehog;
    private boolean dir = false;
    private boolean dirDragon = false;
    private boolean dirBeetle = false;
    private boolean dirHedgehog = false;
    private AnimHero animHero;
    private AnimDragon animDragon;
    private AnimBeetle animBeetle;
    private AnimHedgehog animHedgehog;
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

    private MyConsole myConsole;
    private PhysX physX;
    private Body bodyHero;

    private Body bodyHedgehog;
    private Body bodyBeetle;
    private Body bodyDragon;
    //    private Body bodyKey;
    private Rectangle heroRect;

    private Rectangle hedgehogRect;
    private Rectangle beetleRect;
    private Rectangle dragonRect;
    //   private Rectangle keyRect;

    public static List<Body> bodies;
    private Sound sound = null;
    private long id;
    private MyContList myContList;
    private RectangleMapObject tmpDragon;
    private RectangleMapObject tmpHedgehog;
    private RectangleMapObject tmpBeetle;

    public GameScreen(Main game) {
//        sound = Gdx.audio.newSound(Gdx.files.internal("sound/skrip-otkryivayuscheysya-dveri.mp3"));
//        sound.play();
        bodies = new ArrayList<>();
        animHero = new AnimHero();
        animDragon = new AnimDragon();
        animBeetle = new AnimBeetle();
        animHedgehog = new AnimHedgehog();
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.25f;
        map = new TmxMapLoader().load("map/карта5.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        bg = new int[1];
        bg[0] = map.getLayers().getIndex("фон");
        l1 = new int[2];
        l1[0] = map.getLayers().getIndex("Слой 2");
        l1[1] = map.getLayers().getIndex("Слой 3");
        physX = new PhysX();
        this.myContList = physX.getMyContList();
        map.getLayers().get("объекты").getObjects().getByType(RectangleMapObject.class);//выбор объектов по типу
        RectangleMapObject tmpHero = (RectangleMapObject) map.getLayers().get("heroes").getObjects().get("hero");//выбор объектов по имени
        tmpHedgehog = (RectangleMapObject) map.getLayers().get("heroes").getObjects().get("hedgehog");//выбор объектов по имени
        tmpBeetle = (RectangleMapObject) map.getLayers().get("heroes").getObjects().get("beetle");//выбор объектов по имени
        //    tmpKey = (RectangleMapObject) map.getLayers().get("heroes").getObjects().get("key");//выбор объектов по имени
        tmpDragon = (RectangleMapObject) map.getLayers().get("heroes").getObjects().get("dragon");//выбор объектов по имени

        heroRect = tmpHero.getRectangle();
        bodyHero = physX.addObject(tmpHero);

        posDragon = new Vector2(tmpDragon.getRectangle().x, tmpDragon.getRectangle().y);
        vDragon = new Vector2(1, 0);
        tmpDragon.getRectangle().setPosition(posDragon);
        dragonRect = tmpDragon.getRectangle();
        bodyDragon = physX.addObject(tmpDragon);
        bodyDragon.getPosition().set(posDragon);
        animDragon.getFrameDragon().flip(true, false);

        posHedgehog = new Vector2(tmpHedgehog.getRectangle().x, tmpHedgehog.getRectangle().y);
        vHedgehog = new Vector2(1, 0);
        tmpHedgehog.getRectangle().setPosition(posHedgehog);
        hedgehogRect = tmpHedgehog.getRectangle();
        bodyHedgehog = physX.addObject(tmpHedgehog);
        bodyHedgehog.getPosition().set(posHedgehog);

        posBeetle = new Vector2(tmpBeetle.getRectangle().x, tmpBeetle.getRectangle().y);
        vBeetle = new Vector2(1, 0);
        tmpBeetle.getRectangle().setPosition(posBeetle);
        beetleRect = tmpBeetle.getRectangle();
        bodyBeetle = physX.addObject(tmpBeetle);
        bodyBeetle.getPosition().set(posBeetle);

        hedgehogRect = tmpHedgehog.getRectangle();
        bodyHedgehog = physX.addObject(tmpHedgehog);

        myConsole = new MyConsole(bodyHero, animHero);

        Gdx.input.setInputProcessor(myConsole);

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
//        animation.setAnimation("sleep");
//        sound.stop(id);
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            dispose();
            game.setScreen(new MenuScreen(this.game));}
        if (myContList.isOnBeetle() && myContList.isOnDragon() &&
                myContList.isOnHedgehog() && myContList.isOnEnd()) {
            dispose();
            game.setScreen(new GameOverScreen(this.game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            dir = true;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            dir = false;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.P)) camera.zoom += 0.01f;
        if (Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0) camera.zoom -= 0.01f;

        camera.position.x = bodyHero.getPosition().x* physX.PPM;
        camera.position.y = bodyHero.getPosition().y* physX.PPM;
        camera.update();

        if (!animHero.getFrame().isFlipX() && dir) {
            animHero.getFrame().flip(true, false);

        }//налево
        if (animHero.getFrame().isFlipX() && !dir) {
            animHero.getFrame().flip(true, false);

        }//направо
        animHero.setTime(Gdx.graphics.getDeltaTime());
        animDragon.setTime(Gdx.graphics.getDeltaTime());
        animBeetle.setTime(Gdx.graphics.getDeltaTime());
        animHedgehog.setTime(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(0.5F, 0.2f, 1, 1);
        mapRenderer.setView(camera);
        mapRenderer.render(bg);

        batch.setProjectionMatrix(camera.combined);
        heroRect.x = bodyHero.getPosition().x - heroRect.width/2;
        heroRect.y = bodyHero.getPosition().y - heroRect.height/2;

        dragonRect.x = bodyDragon.getPosition().x - dragonRect.width/2;
        dragonRect.y = bodyDragon.getPosition().y - dragonRect.height/2;
        dragonRect.x = bodyDragon.getPosition().x* physX.PPM;
        dragonRect.y = bodyDragon.getPosition().y* physX.PPM;

        beetleRect.x = bodyBeetle.getPosition().x * physX.PPM;
        beetleRect.y = bodyBeetle.getPosition().y * physX.PPM;
        bodyBeetle.applyForceToCenter(new Vector2(7000 * vBeetle.x, 0), true);

        hedgehogRect.x = bodyHedgehog.getPosition().x * physX.PPM;
        hedgehogRect.y = bodyHedgehog.getPosition().y * physX.PPM;
        bodyHedgehog.applyForceToCenter(new Vector2(7000 * vHedgehog.x, 0), true);

        batch.begin();
        batch.draw(animHero.getFrame(), heroRect.x, heroRect.y, heroRect.width, heroRect.height);
        if (myContList.countBeetle < 3) {
            batch.draw(animBeetle.getFrameBeetle(), beetleRect.x - beetleRect.width / 2,
                    beetleRect.y - beetleRect.height / 2, beetleRect.width, beetleRect.height);
        }
        if (myContList.countDragon >= 3) {
            animDragon.setAnimationDragon("dead");
            batch.draw(animDragon.getFrameDragon(), dragonRect.x - dragonRect.width / 2,
                    dragonRect.y - dragonRect.height / 2, dragonRect.width, dragonRect.height);
        } else {
            batch.draw(animDragon.getFrameDragon(), dragonRect.x - dragonRect.width / 2,
                    dragonRect.y - dragonRect.height / 2, dragonRect.width, dragonRect.height);
        }
        if (myContList.countHedgehog < 3) {
            batch.draw(animHedgehog.getFrameHedgehog(), hedgehogRect.x - hedgehogRect.width / 2,
                    hedgehogRect.y - hedgehogRect.height / 2, hedgehogRect.width, hedgehogRect.height);
        }
        batch.end();

        mapRenderer.render(l1);
        physX.step();
        physX.debugDraw(camera);
        for (int i = 0; i < bodies.size(); i++) {
            physX.destroyBody(bodies.get(i));
        }
        bodies.clear();

        if (myContList.countBeetle < 3) {
            if (posBeetle.x >= 560) {
                vBeetle.set(-50f, 0);
                animBeetle.getFrameBeetle().flip(true, false);
                dirBeetle = !dirBeetle;
            }
            if (posBeetle.x <= 250) {
                vBeetle.set(50f, 0);
                animBeetle.getFrameBeetle().flip(true, false);
                dirBeetle = !dirBeetle;
            }
        }
        if (myContList.countHedgehog < 3) {
            if (posHedgehog.x >= 780) {
                vHedgehog.set(-10f, 0);
                animHedgehog.getFrameHedgehog().flip(true, false);
                dirHedgehog = !dirHedgehog;
            }
            if (posHedgehog.x <= 680) {
                vHedgehog.set(10f, 0);
                animHedgehog.getFrameHedgehog().flip(true, false);
                dirHedgehog = !dirHedgehog;
            }
        }

        posBeetle.add(vBeetle);
        beetleRect.setCenter(posBeetle);

        posHedgehog.add(vHedgehog);
        hedgehogRect.setCenter(posHedgehog);
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
        if (sound != null) { sound.dispose(); }
    }
}

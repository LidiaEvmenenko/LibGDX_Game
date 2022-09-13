package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class PhysX {
    private  World world;
    private final Box2DDebugRenderer debugRenderer;
    public final int PPM = 1;
    private MyContList myContList;
    private PolygonShape polygonShape;
    private Body body;
    private FixtureDef fdef;

    public PhysX() {
        world = new World(new Vector2(0, -9.81f), true);
        myContList = new MyContList();
        world.setContactListener(myContList);
        debugRenderer = new Box2DDebugRenderer();
    }

    public MyContList getMyContList() {
        return myContList;
    }

    public Body addObject(RectangleMapObject object) {
        Rectangle rect = object.getRectangle();
        String type = (String) object.getProperties().get("BodyType");
        BodyDef def = new BodyDef();
        fdef = new FixtureDef();
        polygonShape = new PolygonShape();
        if (type.equals("StaticBody"))  def.type = BodyDef.BodyType.StaticBody;
        if (type.equals("DynamicBody")) {
            def.type = BodyDef.BodyType.DynamicBody;
            fdef.friction = (float) object.getProperties().get("friction");
        }
        def.position.set((rect.x + rect.width / 2)/PPM, (rect.y + rect.height / 2)/PPM);
        def.gravityScale = (float) object.getProperties().get("gravityScale");
        polygonShape.setAsBox(rect.width / 2/PPM, rect.height / 2/PPM);
        fdef.shape = polygonShape;// тело имеет форму прямоугольника
        fdef.density = 1;//плотность ,если =0 то объект не сможет наклоняться
        fdef.restitution = (float) object.getProperties().get("restitution");
        String name = "";
        if (object.getName() != null) name = object.getName();
        body = world.createBody(def);
        body.setUserData(new PhysBody(name, new Vector2(rect.x, rect.y), new Vector2(rect.width, rect.height)));
        if (type.equals("DynamicBody")) { body.setFixedRotation(true); }//запретили телу крутиться
        else {
            body.setFixedRotation(false);
        }
        name = object.getName();
        body.createFixture(fdef).setUserData(name);//создаем тело, текстуру, ключ
        if (name != null && name.equals("hero")){
            createSensor(rect);
        }
        createObj(object);
        polygonShape.dispose();
        return body;
    }

    public void setGravity(Vector2 gravity) {
        world.setGravity(gravity);
    }

    public void step() {
        world.step(1 / 60f, 3, 3);
    }

    public void debugDraw(OrthographicCamera cam) {
        debugRenderer.render(world, cam.combined);
    }

    public void destroyBody(Body body){ world.destroyBody(body); }

    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }

    public void setRestitut(RectangleMapObject object, float restitution) {

    }
    public Array<Body> getBodys(String name){
        Array<Body> ab = new Array<>();
        world.getBodies(ab);//возвращает спмсок всех тел, кот сейчас присутствуют в физ мире
        Iterator<Body> it = ab.iterator();
        while (it.hasNext()){
            String  text = ((PhysBody) it.next().getUserData()).name;
            if (!text.equals(name)) it.remove();
        }
        return ab;
    }
    private void createSensor(Rectangle rect){
        polygonShape.setAsBox(rect.width/2/PPM-2, rect.height/12/PPM, new Vector2(0,-rect.width/2/PPM),0);
        body.createFixture(fdef).setUserData("legs");
        body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);

        polygonShape.setAsBox(rect.width/12/PPM, rect.height/2/PPM-1, new Vector2(-5*rect.width/12/PPM-1,0),0);
        body.createFixture(fdef).setUserData("legsL");
        body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);

        polygonShape.setAsBox(rect.width/12/PPM, rect.height/2/PPM-1, new Vector2(5*rect.width/12/PPM+1,0),0);
        body.createFixture(fdef).setUserData("legsR");
        body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);
    }

    private void createObj(RectangleMapObject object){
        if (object.getName() != null) {
            switch (object.getName()) {
                case "descent1":
                    body.setTransform(74, 150, 43);
                    body.getFixtureList().get(0).setFriction(3f);
                    break;
                case  "climb1":
                    body.setTransform(170, 122, -40);
                    body.getFixtureList().get(0).setFriction(3f);
                    break;
                case "descent2":
                    body.setTransform(489, 104, 40);
                    body.getFixtureList().get(0).setFriction(3f);
                    break;
                case "descent3":
                    body.setTransform(565, 64, 43);
                    body.getFixtureList().get(0).setFriction(3f);
                    break;
                case "climb2":
                    body.setTransform(645, 66, -40);
                    body.getFixtureList().get(0).setFriction(3f);
                    break;
                case "climb3":
                    body.setTransform(819, 85, -40);
                    body.getFixtureList().get(0).setFriction(3f);
                    break;
                case "climb4":
                    body.setTransform(965, 150, 20);
                    body.getFixtureList().get(0).setFriction(3f);
                    break;
            }
        }
    }
}

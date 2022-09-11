package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
public class PhysX {
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    public final int PPM = 1;
    private float restitution;
    private MyContList myContList;

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

        FixtureDef fdef = new FixtureDef();

        PolygonShape polygonShape = new PolygonShape();
        //   def.type = BodyDef.BodyType.StaticBody;


        if (type.equals("StaticBody"))  def.type = BodyDef.BodyType.StaticBody;
        if (type.equals("DynamicBody")) {
            def.type = BodyDef.BodyType.DynamicBody;
            fdef.friction = (float) object.getProperties().get("friction");
        }
        def.position.set((rect.x + rect.width / 2)/PPM, (rect.y + rect.height / 2)/PPM);
        //    def.gravityScale = 1; //гравитация
        def.gravityScale = (float) object.getProperties().get("gravityScale");
        polygonShape.setAsBox(rect.width / 2/PPM, rect.height / 2/PPM);
        fdef.shape = polygonShape;// тело имеет форму прямоугольника
        fdef.friction = 0;//трение =0 - лед
        fdef.density = 1;//плотность ,если =0 то объект не сможет наклоняться
        //   fdef.restitution = 0;//прыгучесть =0 полностью поглащает энерцию(упал не отскочил), если >0(упал-отскочил)
        fdef.restitution = (float) object.getProperties().get("restitution");

        Body body;
        body = world.createBody(def);
        if (type.equals("DynamicBody")) { body.setFixedRotation(true); }//запретили телу крутиться
        else { body.setFixedRotation(false); }
        String name = object.getName();
        body.createFixture(fdef).setUserData(name);//создаем тело, текстуру, ключ
        if (name != null && name.equals("hero")){
            polygonShape.setAsBox(rect.width/12/PPM, rect.height/12/PPM,new Vector2(-7*rect.width/12/PPM, 0),0);
            body.createFixture(fdef).setUserData("arms");
            body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);

            polygonShape.setAsBox(rect.width/12/PPM, rect.height/12/PPM,new Vector2(7*rect.width/12/PPM, 0),0);
            body.createFixture(fdef).setUserData("arms");
            body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);

            polygonShape.setAsBox(rect.width/12/PPM, rect.height/12/PPM, new Vector2(0,-rect.width/2/PPM),0);
            body.createFixture(fdef).setUserData("legs");
            body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);

            polygonShape.setAsBox(rect.width/12/PPM, rect.height/12/PPM, new Vector2(-7*rect.width/12/PPM,-rect.width/2/PPM),0);
            body.createFixture(fdef).setUserData("legsL");
            body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);

            polygonShape.setAsBox(rect.width/12/PPM, rect.height/12/PPM, new Vector2(7*rect.width/12/PPM,-rect.width/2/PPM),0);
            body.createFixture(fdef).setUserData("legsR");
            body.getFixtureList().get(body.getFixtureList().size-1).setSensor(true);

            //  body.createFixture(fdef).setUserData(new String("стена"));//создаем тело, текстуру, ключ

        }

        if (object.getName() != null && object.getName().equals("descent1")) {
            body.setTransform(74,150,43);
        }
        if (object.getName() != null && object.getName().equals("climb1")) {
            body.setTransform(170,122,-40);
        }
        if (object.getName() != null && object.getName().equals("descent2")) {
            body.setTransform(489,104,40);
        }
        if (object.getName() != null && object.getName().equals("descent3")) {
            body.setTransform(565,64,43);
        }
        if (object.getName() != null && object.getName().equals("climb2")) {
            body.setTransform(645,66,-40);
        }
        if (object.getName() != null && object.getName().equals("climb3")) {
            body.setTransform(819,85,-40);
        }
        if (object.getName() != null && object.getName().equals("climb4")) {
            body.setTransform(965,150,20);
        }
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

    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }

    public void destroyBody(Body body){ world.destroyBody(body); }
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
public class PhysX {
    private final World world;
    private final Box2DDebugRenderer debugRenderer;

    public PhysX() {
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
    }

    public Body addObject(RectangleMapObject object) {

        Rectangle rect = object.getRectangle();
        String type = (String) object.getProperties().get("BodyType");

        BodyDef def = new BodyDef();

        FixtureDef fdef = new FixtureDef();

        PolygonShape polygonShape = new PolygonShape();
        //   def.type = BodyDef.BodyType.StaticBody;


        if (type.equals("StaticBody"))  def.type = BodyDef.BodyType.StaticBody;
        if (type.equals("DynamicBody"))  def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(rect.x + rect.width / 2, rect.y + rect.height / 2);
        //    def.gravityScale = 1; //гравитация
        def.gravityScale = (float) object.getProperties().get("gravityScale");
        polygonShape.setAsBox(rect.width / 2, rect.height / 2);
        fdef.shape = polygonShape;// тело имеет форму прямоугольника
        fdef.friction = 0;//трение =0 - лед
        fdef.density = 1;//плотность ,если =0 то объект не сможет наклоняться
        //   fdef.restitution = 0;//прыгучесть =0 полностью поглащает энерцию(упал не отскочил), если >0(упал-отскочил)
        fdef.restitution = (float) object.getProperties().get("restitution");

        Body body;
        body = world.createBody(def);
        body.createFixture(fdef).setUserData("стена");//создаем тело, текстуру, ключ
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
}

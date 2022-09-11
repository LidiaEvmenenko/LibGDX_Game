package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;
public class MyContList implements ContactListener{
    private Sound sound;
    private Fixture a;
    private Fixture b;
    public int count = 0;
    public int countDragon = 0;
    public int countBeetle = 0;
    public int countHedgehog = 0;
    private String tmpA;
    private String tmpB;
    private boolean contactEnd = false;

    public MyContList() {

    }
    public Sound getSound() {
        return sound;
    }

    @Override
    public void beginContact(Contact contact) {
        a = contact.getFixtureA();
        b = contact.getFixtureB();


        if (a.getUserData() != null && b.getUserData() != null){
            String tmpA = (String) a.getUserData();
            String tmpB = (String) b.getUserData();
            sound = null;
            count++;
            if (tmpA.equals("legs") && tmpB.equals("hedgehog")) {
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                //   sound.play();
                countHedgehog++;
                if (countHedgehog >= 3) {
                    GameScreen.bodies.add(b.getBody());
                    a.setRestitution(1);
                }
            }
            if (tmpB.equals("hero") && tmpA.equals("hedgehog")) {
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                //   sound.play();
                b.setRestitution(1);

            }
            if (tmpA.equals("legs") && tmpB.equals("beetle")) {
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                //   sound.play();
                countBeetle++;
                if (countBeetle >= 3) {
                    GameScreen.bodies.add(b.getBody());
                    a.setRestitution(1);
                }
            }
            if (tmpB.equals("hero") && tmpA.equals("beetle")) {
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                //   sound.play();
                b.setRestitution(1);

            }
            if (tmpA.equals("hero") && tmpB.equals("земля")) {

                sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                //   sound.play();
                a.setRestitution(0.1f);


            }
            if (tmpB.equals("hero") && tmpA.equals("земля")) {

                sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                //  sound.play();
                b.setRestitution(0.1f);
            }

            if (tmpA.equals("legs") && tmpB.equals("dragon")) {
                countDragon++;
                if (countDragon >= 3) {
                    GameScreen.bodies.add(b.getBody());
                    a.setRestitution(1);
                }
            }
            if (tmpB.equals("hero") && tmpA.equals("dragon")) {
                b.setRestitution(1);
            }
            if (tmpA.equals("legs") && tmpB.equals("dragon")) {
                countDragon++;
                if (countDragon >= 3) {
                    GameScreen.bodies.add(b.getBody());
                    a.setRestitution(1);
                }
            }
            if (tmpB.equals("hero") && tmpA.equals("dragon")) {
                b.setRestitution(1);
            }
            if (tmpA.equals("legsL") && tmpB.equals("descent1")) {
                a.getBody().applyForceToCenter(0, -90000, true);

                a.setRestitution(1);

            }
            if (tmpB.equals("legsL") && tmpA.equals("descent1")) {

                b.getBody().applyForceToCenter(0, -90000, true);
                b.setRestitution(1);
            }
            if (tmpA.equals("legsL") && tmpB.equals("descent2")) {
                a.getBody().applyForceToCenter(0, -90000, true);

                a.setRestitution(1);

            }
            if (tmpB.equals("legsL") && tmpA.equals("descent2")) {

                b.getBody().applyForceToCenter(0, -90000, true);
                b.setRestitution(1);
            }
            if (tmpA.equals("legsL") && tmpB.equals("descent3")) {
                a.getBody().applyForceToCenter(0, -90000, true);

                a.setRestitution(1);

            }
            if (tmpB.equals("legsL") && tmpA.equals("descent3")) {

                b.getBody().applyForceToCenter(0, -90000, true);
                b.setRestitution(1);
            }
            if (tmpA.equals("legsR") && tmpB.equals("climb1")) {
                a.getBody().applyForceToCenter(90000, -90000, true);

                a.setRestitution(1);

            }
            if (tmpB.equals("legsR") && tmpA.equals("climb1")) {

                b.getBody().applyForceToCenter(0, -900000, true);
                b.setRestitution(1);
            }
            if (tmpA.equals("legsR") && tmpB.equals("climb2")) {
                a.getBody().applyForceToCenter(90000, -90000, true);

                a.setRestitution(1);

            }
            if (tmpB.equals("legsR") && tmpA.equals("climb2")) {

                b.getBody().applyForceToCenter(0, -900000, true);
                b.setRestitution(1);
            }
            if (tmpA.equals("legsR") && tmpB.equals("climb3")) {
                a.getBody().applyForceToCenter(90000, -90000, true);

                a.setRestitution(1);

            }
            if (tmpB.equals("legsR") && tmpA.equals("climb3")) {

                b.getBody().applyForceToCenter(0, -900000, true);
                b.setRestitution(1);
            }
            if (tmpA.equals("legsR") && tmpB.equals("climb4")) {
                a.getBody().applyForceToCenter(90000, -90000, true);

                a.setRestitution(1);

            }
            if (tmpB.equals("legsR") && tmpA.equals("climb4")) {

                b.getBody().applyForceToCenter(0, -900000, true);
                b.setRestitution(1);
            }
            if (tmpA.equals("legs") && tmpB.equals("end")) {
                if (isOnBeetle() && isOnDragon() && isOnHedgehog()) {
                    contactEnd = true;
                }

            }
            if (tmpB.equals("legs") && tmpA.equals("end")) {

                b.getBody().applyForceToCenter(0, -900000, true);
                b.setRestitution(1);
            }
        }


    }

    @Override
    public void endContact(Contact contact) {
        a = contact.getFixtureA();
        b = contact.getFixtureB();
        count--;

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {// работает за 1 просчета физики как тела пересеклись

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {//работает за 1 просчета физики как тела разъедутся

    }

    public boolean isOnDragon() {
        return countDragon > 0;
    }

    public boolean isOnBeetle() {
        return countBeetle > 0;
    }

    public boolean isOnHedgehog() {
        return countHedgehog > 0;
    }

    public boolean isOnEnd() {
        return contactEnd;
    }
}

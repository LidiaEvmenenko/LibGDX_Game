package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

public class MyContList implements ContactListener{
    private Sound sound;
    private Fixture a;
    private Fixture b;
    public boolean count = false;
    public int countDragon = 0;
    public int countBeetle = 0;
    public int countHedgehog = 0;
    private String tmpA;
    private String tmpB;
    private boolean contactEnd = false;
    private boolean cont;
    private boolean contLet = false;
    private int countLife=9;

    public MyContList() {

    }

    public int getCountLife() {
        return countLife;
    }

    public boolean isContLet() {
        return contLet;
    }
    public Sound getSound() {
        return sound;
    }

    @Override
    public void beginContact(Contact contact) {
        if (countLife != 0) {
            a = contact.getFixtureA();
            b = contact.getFixtureB();
            cont = false;

            if (a.getUserData() != null && b.getUserData() != null) {
                tmpA = (String) a.getUserData();
                tmpB = (String) b.getUserData();
                sound = null;

                if (tmpA.equals("legs") && tmpB.equals("hedgehog")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/zvuk-udara.mp3"));
                    sound.play();
                    countHedgehog++;
                    a.setRestitution(1);
                    if (countHedgehog >= 3) {
                        GameScreen.bodies.add(b.getBody());
                    }
                }
                if ((tmpB.equals("legsR") && tmpA.equals("hedgehog")) || (tmpB.equals("legsL") && tmpA.equals("hedgehog"))) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук неприятного удара.mp3"));
                    sound.play();
                    b.setRestitution(1);
                    countLife--;
                }
                if ((tmpA.equals("legsR") && tmpB.equals("hedgehog")) || (tmpA.equals("legsL") && tmpB.equals("hedgehog"))) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук неприятного удара.mp3"));
                    sound.play();
                    b.setRestitution(1);
                    countLife--;
                }
                if (tmpA.equals("legs") && tmpB.equals("beetle")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/zvuk-udara.mp3"));
                    sound.play();
                    countBeetle++;
                    a.setRestitution(1);
                    if (countBeetle >= 3) {
                        GameScreen.bodies.add(b.getBody());
                    }
                }
                if ((tmpB.equals("legsR") && tmpA.equals("beetle")) || (tmpB.equals("legsL") && tmpA.equals("beetle"))) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук неприятного удара.mp3"));
                    sound.play();
                    b.setRestitution(1);
                    countLife--;
                }
                if ((tmpA.equals("legsR") && tmpB.equals("beetle")) || (tmpA.equals("legsL") && tmpB.equals("beetle"))) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук неприятного удара.mp3"));
                    sound.play();
                    b.setRestitution(1);
                    countLife--;
                }
                if (tmpA.equals("legs") && tmpB.equals("dragon")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/zvuk-udara.mp3"));
                    sound.play();
                    countDragon++;
                    a.setRestitution(1);
                    if (countDragon >= 3) {
                        GameScreen.bodies.add(b.getBody());

                    }
                }
                if ((tmpA.equals("legsR") && tmpB.equals("dragon")) || (tmpA.equals("legsL") && tmpB.equals("dragon"))) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук неприятного удара.mp3"));
                    sound.play();
                    a.setRestitution(1);
                    countLife--;
                }
                if ((tmpB.equals("legsR") && tmpA.equals("dragon")) || (tmpB.equals("legsL") && tmpA.equals("dragon"))) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук неприятного удара.mp3"));
                    sound.play();
                    b.setRestitution(1);
                    countLife--;
                }

                if (tmpA.equals("hero") && tmpB.equals("земля")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.setRestitution(0);
                    a.setRestitution(0.1f);
                    a.setFriction(0.1f);
                    count = true;

                }
                if (tmpB.equals("hero") && tmpA.equals("земля")) {
                    count = true;
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    b.setRestitution(0);
                    b.setRestitution(0.1f);
                    b.setFriction(0.1f);
                }


                if (tmpA.equals("legsL") && tmpB.equals("descent1")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.getBody().applyForceToCenter(0, 900000, true);
                    contLet = true;
                    a.setRestitution(1);
                    count = true;
                }
                if (tmpB.equals("legsL") && tmpA.equals("descent1")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    contLet = true;
                    b.getBody().applyForceToCenter(0, 900000, true);
                    b.setRestitution(1);
                    count = true;
                }
                if (tmpA.equals("legsL") && tmpB.equals("descent2")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.getBody().applyForceToCenter(0, 900000, true);
                    contLet = true;
                    a.setRestitution(1);
                    count = true;
                }
                if (tmpB.equals("legsL") && tmpA.equals("descent2")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    contLet = true;
                    b.getBody().applyForceToCenter(0, 900000, true);
                    b.setRestitution(1);
                    count = true;
                }
                if (tmpA.equals("legsL") && tmpB.equals("descent3")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.getBody().applyForceToCenter(0, 900000, true);
                    contLet = true;
                    a.setRestitution(1);
                    count = true;
                }
                if (tmpB.equals("legsL") && tmpA.equals("descent3")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    contLet = true;
                    b.getBody().applyForceToCenter(0, 900000, true);
                    b.setRestitution(1);
                    count = true;
                }
                if (tmpA.equals("legsR") && tmpB.equals("climb1")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.getBody().applyForceToCenter(90000, 700000, true);
                    contLet = true;
                    a.setRestitution(1);
                    count = true;
                }
                if (tmpB.equals("legsR") && tmpA.equals("climb1")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    contLet = true;
                    b.getBody().applyForceToCenter(90000, 700000, true);
                    b.setRestitution(1);
                    count = true;
                }
                if (tmpA.equals("legsR") && tmpB.equals("climb2")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.getBody().applyForceToCenter(90000, 700000, true);
                    contLet = true;
                    a.setRestitution(1);
                    count = true;
                }
                if (tmpB.equals("legsR") && tmpA.equals("climb2")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    contLet = true;
                    b.getBody().applyForceToCenter(90000, 700000, true);
                    b.setRestitution(1);
                    count = true;
                }
                if (tmpA.equals("legsR") && tmpB.equals("climb3")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.getBody().applyForceToCenter(90000, 700000, true);
                    contLet = true;
                    a.setRestitution(1);
                    count = true;
                }
                if (tmpB.equals("legsR") && tmpA.equals("climb3")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    contLet = true;
                    b.getBody().applyForceToCenter(90000, 700000, true);
                    b.setRestitution(1);
                    count = true;
                }
                if (tmpA.equals("legsR") && tmpB.equals("climb4")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    a.getBody().applyForceToCenter(90000, 90000000, true);
                    contLet = true;
                    a.setRestitution(1);
                    count = true;
                }
                if (tmpB.equals("legsR") && tmpA.equals("climb4")) {
                    sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
                    contLet = true;
                    b.getBody().applyForceToCenter(90000, 90000000, true);
                    b.setRestitution(1);
                    count = true;
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
    }

    @Override
    public void endContact(Contact contact) {
        a = contact.getFixtureA();
        b = contact.getFixtureB();
        contLet = false;
        count = false;
        if (tmpA.equals("hero") && tmpB.equals("земля") || tmpA.equals("legs") && tmpB.equals("земля")) {

            sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
            sound.stop();
            sound = null;
        }
        if (tmpB.equals("hero") && tmpA.equals("земля") || tmpB.equals("legs") && tmpA.equals("земля")) {
            sound = Gdx.audio.newSound(Gdx.files.internal("sound/Звук шага.mp3"));
            sound.stop();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {// работает за 1 просчета физики как тела пересеклись

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {//работает за 1 просчета физики как тела разъедутся

    }

    public boolean isOnGraund() {
        return count;
    }

    public boolean isOnDragon() {
        return countDragon > 2;
    }

    public boolean isOnBeetle() {
        return countBeetle > 2;
    }

    public boolean isOnHedgehog() {
        return countHedgehog > 2;
    }

    public boolean isOnEnd() {
        return contactEnd;
    }

    public boolean getCont() {
        return cont;
    }

    public int getCountDragon() {
        return countDragon;
    }

    public int getCountBeetle() {
        return countBeetle;
    }

    public int getCountHedgehog() {
        return countHedgehog;
    }
}

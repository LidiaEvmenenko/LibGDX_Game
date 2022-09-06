package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;
public class MyContList implements ContactListener{
    private Sound sound;
    private Fixture a;
    private Fixture b;

    public MyContList() {

    }

    @Override
    public void beginContact(Contact contact) {
        a = contact.getFixtureA();
        b = contact.getFixtureB();


        if (a.getUserData() != null && b.getUserData() != null){
            String tmpA = (String) a.getUserData();
            String tmpB = (String) b.getUserData();
            if (tmpA.equals("hero") && tmpB.equals("aaa")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/zvuk-udara.mp3"));
                sound.play();
                a.setRestitution(0);
                GameScreen.bodies.add(b.getBody());

            }
            if (tmpB.equals("hero") && tmpA.equals("aaa")){
                b.setRestitution(0);
                GameScreen.bodies.add(a.getBody());
            }
            if (tmpA.equals("hero") && tmpB.equals("scull")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                sound.play();
                GameScreen.bodies.add(b.getBody());

            }
            if (tmpB.equals("hero") && tmpA.equals("scull")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                sound.play();
                GameScreen.bodies.add(a.getBody());

            }
            if (tmpA.equals("hero") && tmpB.equals("eared1")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                sound.play();
                a.setRestitution(0);
                GameScreen.bodies.add(b.getBody());

            }
            if (tmpB.equals("hero") && tmpA.equals("eared1")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/gluhoy-zvuk-ne-silnogo-stolknoveniya.mp3"));
                sound.play();
                b.setRestitution(0);
                GameScreen.bodies.add(a.getBody());
            }
            if (tmpA.equals("hero") && tmpB.equals("земля")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/pryijok.mp3"));
                sound.play();
                a.setRestitution(0);

            }
            if (tmpB.equals("hero") && tmpA.equals("земля")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/pryijok.mp3"));
                sound.play();
                b.setRestitution(0);

            }
            if (tmpA.equals("hero") && tmpB.equals("дверь правая")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/game-over.mp3"));
                sound.play();
                a.setRestitution(0);

            }
            if (tmpB.equals("hero") && tmpA.equals("дверь правая")){
                sound = Gdx.audio.newSound(Gdx.files.internal("sound/game-over.mp3"));
                sound.play();
                b.setRestitution(0);

            }
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {// работает за 1 просчета физики как тела пересеклись

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {//работает за 1 просчета физики как тела разъедутся

    }
}

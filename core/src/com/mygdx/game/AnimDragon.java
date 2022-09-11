package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AnimDragon {
    private TextureAtlas atlasDragon;
    private Animation<TextureRegion> anmDragonAttack;
    private Animation<TextureRegion> anmDragonDead;
    //    private Animation<TextureRegion> anmDragonFlight;
//    private Animation<TextureRegion> anmDragonHurt;
//    private Animation<TextureRegion> anmDragonIdle;
//    private Animation<TextureRegion> anmDragonJump;
//    private Animation<TextureRegion> anmDragonWalk;
    private Animation<TextureRegion> anmDragon;
    private float time;

    public AnimDragon() {
        atlasDragon = new TextureAtlas("atlas/dragon/unnamed.atlas");
        //   anmDragonWalk = new Animation<TextureRegion>(1/5f, atlasDragon.findRegions("walk"));
        anmDragonAttack = new Animation<TextureRegion>(1/5f, atlasDragon.findRegions("attack"));
        anmDragonDead = new Animation<TextureRegion>(1/5f, atlasDragon.findRegions("dead"));
//        anmDragonFlight = new Animation<TextureRegion>(1/5f, atlasDragon.findRegions("flight"));
//        anmDragonHurt = new Animation<TextureRegion>(1/5f, atlasDragon.findRegions("hurt"));
//        anmDragonIdle = new Animation<TextureRegion>(1/5f, atlasDragon.findRegions("idle"));
//        anmDragonJump = new Animation<TextureRegion>(1/5f, atlasDragon.findRegions("jumpD"));
        anmDragon = anmDragonAttack;
        //  anmDragonWalk.setPlayMode(Animation.PlayMode.LOOP);
        anmDragonAttack.setPlayMode(Animation.PlayMode.LOOP);
        anmDragonDead.setPlayMode(Animation.PlayMode.NORMAL);
//        anmDragonFlight.setPlayMode(Animation.PlayMode.LOOP);
//        anmDragonHurt.setPlayMode(Animation.PlayMode.LOOP);
//        anmDragonIdle.setPlayMode(Animation.PlayMode.LOOP);
//        anmDragonJump.setPlayMode(Animation.PlayMode.NORMAL);
        time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrameDragon(){ return anmDragon.getKeyFrame(time);}

    public void setTime(float time) {
        this.time += time;
    }

    public void dispose () {
        atlasDragon.dispose();
    }

    public void setAnimationDragon(String traffic){
        if (traffic.equals("dead")) { anmDragon = anmDragonDead; }
    }
}

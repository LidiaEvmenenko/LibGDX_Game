package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimHero {
    private TextureAtlas atlasHero;
    private Animation<TextureRegion> anmHit;
    private Animation<TextureRegion> anmOver;
    private Animation<TextureRegion> anmGo;
    private Animation<TextureRegion> anmJump;
    private Animation<TextureRegion> anmRun;
    private Animation<TextureRegion> anm;
    private Animation<TextureRegion> anmSleep;
    private float time, x;
    private String traffic;
    boolean leftMove;
    boolean rightMove;


    public AnimHero(){
        atlasHero = new TextureAtlas("atlas/unnamed.atlas");
        anm = new Animation<TextureRegion>(1/5f, atlasHero.findRegions("go").get(1));
        anmSleep = anm;
        anmRun = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("run"));
        anmHit = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("hit"));
        anmGo = new Animation<TextureRegion>(1/5f, atlasHero.findRegions("go"));
        anmJump = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("jump"));
        anmOver = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("over"));
        anmRun.setPlayMode(Animation.PlayMode.LOOP);
        anmHit.setPlayMode(Animation.PlayMode.LOOP);
        anmGo.setPlayMode(Animation.PlayMode.LOOP);
        anm.setPlayMode(Animation.PlayMode.LOOP);
        anmJump.setPlayMode(Animation.PlayMode.LOOP);
        anmOver.setPlayMode(Animation.PlayMode.LOOP);
        time += Gdx.graphics.getDeltaTime();
    }
    public TextureRegion getFrame(){
        return anm.getKeyFrame(time);}

    public void setTime(float time) {
        this.time += time;
    }

    public boolean isAnimationOver() {return anm.isAnimationFinished(time);}
    public void zeroTime() {this.time = 0;}
    public void setPlayMode(Animation.PlayMode playMode) {anm.setPlayMode(playMode);}
    public void dispose () { atlasHero.dispose();}
    public String getTraffic(){ return traffic; }
    public void setAnimationHero(String traffic){
        if (traffic.equals("run")) { anm = anmRun; }
        if (traffic.equals("hit")) { anm = anmHit; }
        if (traffic.equals("go")) { anm = anmGo; }
        if (traffic.equals("jump")) { anm = anmJump; }
        if (traffic.equals("over")) { anm = anmOver; }
        if (traffic.equals("sleep")) { anm = anmSleep;}
        this.traffic = traffic;
    }
    public void updateMotion() {
        if (leftMove)
        {
            x -= 5 * Gdx.graphics.getDeltaTime();
        }
        if (rightMove)
        {
            x += 5 * Gdx.graphics.getDeltaTime();
        }
    }
    public void setLeftMove(boolean t) {
        if(leftMove && t) leftMove = false;
        rightMove = t;
//        if(rightMove && t) rightMove = false;
//        leftMove = t;
    }
    public void setRightMove(boolean t) {
        if(rightMove && t) rightMove = false;
        leftMove = t;
//        if(leftMove && t) leftMove = false;
//        rightMove = t;
    }

}

package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Anim {
    private TextureAtlas atlas;
    private Animation<TextureRegion> anmHit;
    private Animation<TextureRegion> anmOver;
    private Animation<TextureRegion> anmGo;
    private Animation<TextureRegion> anmJump;
    private Animation<TextureRegion> anmRun;
    private Animation<TextureRegion> anm;
    private Animation<TextureRegion> anmSleep;
    private float time;
    private String traffic;


    public Anim(String name, Animation.PlayMode playMode){
        atlas = new TextureAtlas("map/unnamed.atlas");
        anm = new Animation<TextureRegion>(1/5f, atlas.findRegions("go").get(1));
        anmSleep = anm;
        anmRun = new Animation<TextureRegion>(1/15f, atlas.findRegions("run"));
        anmHit = new Animation<TextureRegion>(1/15f, atlas.findRegions("hit"));
        anmGo = new Animation<TextureRegion>(1/5f, atlas.findRegions("go"));
        anmJump = new Animation<TextureRegion>(1/15f, atlas.findRegions("jump"));
        anmOver = new Animation<TextureRegion>(1/15f, atlas.findRegions("over"));
        anmRun.setPlayMode(playMode);
        anmHit.setPlayMode(playMode);
        anmGo.setPlayMode(playMode);
        anm.setPlayMode(playMode);
        anmJump.setPlayMode(playMode);
        anmOver.setPlayMode(playMode);
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
    public void dispose () { atlas.dispose();}
    public String getTraffic(){ return traffic; }
    public void setAnimation(String traffic){
        if (traffic.equals("run")) { anm = anmRun; }
        if (traffic.equals("hit")) { anm = anmHit; }
        if (traffic.equals("go")) { anm = anmGo; }
        if (traffic.equals("jump")) { anm = anmJump; }
        if (traffic.equals("over")) { anm = anmOver; }
        if (traffic.equals("sleep")) { anm = anmSleep;}
        this.traffic = traffic;
    }

}

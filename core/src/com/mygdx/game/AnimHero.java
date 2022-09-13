package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class AnimHero {
    private TextureAtlas atlasHero;
    private Animation<TextureRegion> anmHit;
    private Animation<TextureRegion> anmOver;
    private Animation<TextureRegion> anmGo;
    private Animation<TextureRegion> anmUdar;
    private Animation<TextureRegion> anmJumpUp;
    private Animation<TextureRegion> anmJumpDown;
    private Animation<TextureRegion> anm;
    private Animation<TextureRegion> anmSleep;
    private float time, x;
    private String traffic;
    private static float dScale = 3.8f;
    boolean leftMove;
    boolean rightMove;

    public AnimHero(){
        atlasHero = new TextureAtlas("atlas/unnamed.atlas");
        anmSleep = new Animation<TextureRegion>(1/5f, atlasHero.findRegions("go").get(1));
        anm = anmSleep;
        anmJumpUp = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("run").get(4)); // 1/60 это скорость отрисовки
        anmJumpDown = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("run").get(5)); // 1/60 это скорость отрисовки
        anmHit = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("hit")); // 1/60 это скорость отрисовки
        anmGo = new Animation<TextureRegion>(1/5f, atlasHero.findRegions("go")); // 1/60 это скорость отрисовки
        anmUdar = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("jump")); // 1/60 это скорость отрисовки
        anmOver = new Animation<TextureRegion>(1/15f, atlasHero.findRegions("over")); // 1/60 это скорость отрисовки
        anmUdar.setPlayMode(Animation.PlayMode.LOOP); // режим воспроизведения
        anmHit.setPlayMode(Animation.PlayMode.LOOP); // режим воспроизведения
        anmGo.setPlayMode(Animation.PlayMode.LOOP); // режим воспроизведения
        anm.setPlayMode(Animation.PlayMode.LOOP); // режим воспроизведения
        anmJumpUp.setPlayMode(Animation.PlayMode.NORMAL); // режим воспроизведения
        anmJumpDown.setPlayMode(Animation.PlayMode.NORMAL); // режим воспроизведения
        anmOver.setPlayMode(Animation.PlayMode.NORMAL); // режим воспроизведения
        time += Gdx.graphics.getDeltaTime();
    }
    public TextureRegion getFrame(){ return anm.getKeyFrame(time);}
    public String getTraffic(){ return traffic; }
    public void setTime(float time) {
        this.time += time;
    }
    public void setAnimationHero(String traffic){
        if (traffic.equals("jumpUp")) { anm = anmJumpUp; }
        if (traffic.equals("hit")) { anm = anmHit; }
        if (traffic.equals("go")) { anm = anmGo; }
        if (traffic.equals("jumpDown")) { anm = anmJumpDown; }
        if (traffic.equals("over")) { anm = anmOver; }
        if (traffic.equals("sleep")) { anm = anmSleep;}
        this.traffic = traffic;
    }

    public boolean isAnimationOver() {return anm.isAnimationFinished(time);}
    public void zeroTime() {this.time = 0;}
    public void setPlayMode(Animation.PlayMode playMode) {anm.setPlayMode(playMode);}
    public void dispose () { atlasHero.dispose(); }

    public Rectangle getRect(OrthographicCamera camera, TextureRegion region){
        float cx = Gdx.graphics.getWidth()/2 - region.getRegionWidth()/2 / camera.zoom /dScale;
        float cy = Gdx.graphics.getHeight()/2 - region.getRegionHeight()/2 / camera.zoom /dScale;
        float cW = region.getRegionWidth() / camera.zoom / dScale;
        float cH = region.getRegionHeight() / camera.zoom / dScale;
        return new Rectangle(cx, cy, cW, cH);
    }


}

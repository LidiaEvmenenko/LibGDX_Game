package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Anim {
    private TextureAtlas atlas;
    private Animation<TextureRegion> anm;
    private float time;


    public Anim(String textureAtlas, Animation.PlayMode playMode){
        atlas = new TextureAtlas(textureAtlas);
        anm = new Animation<TextureRegion>(1/15f, atlas.findRegions("run"));
        anm.setPlayMode(playMode);
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

}

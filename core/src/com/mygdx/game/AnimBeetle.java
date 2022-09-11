package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AnimBeetle {
    private TextureAtlas atlasBeetle;
    private Animation<TextureRegion> anmBeetle;
    private float time;
    private String traffic;

    public AnimBeetle() {
        atlasBeetle = new TextureAtlas("atlas/beetle/unnamed.atlas");
        anmBeetle = new Animation<TextureRegion>(1/5f, atlasBeetle.findRegions("go"));
        anmBeetle.setPlayMode(Animation.PlayMode.LOOP);
        time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrameBeetle(){ return anmBeetle.getKeyFrame(time);}

    public void setTime(float time) {
        this.time += time;
    }

    public void dispose () { atlasBeetle.dispose(); }
}

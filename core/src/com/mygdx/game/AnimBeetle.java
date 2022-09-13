package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AnimBeetle {
    private TextureAtlas atlas;
    private Animation<TextureRegion> anm;
    private float time;


    public AnimBeetle() {
        atlas = new TextureAtlas("atlas/beetle/unnamed.atlas");
        anm = new Animation<TextureRegion>(1/5f, atlas.findRegions("go"));
        anm.setPlayMode(Animation.PlayMode.LOOP);
        time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrameBeetle(){ return anm.getKeyFrame(time);}

    public void setTime(float time) {
        this.time += time;
    }

    public void dispose () { atlas.dispose(); }
}

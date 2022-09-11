package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AnimHedgehog {
    private TextureAtlas atlasHedgehog;
    private Animation<TextureRegion> anmHedgehog;
    private float time;

    public AnimHedgehog() {
        atlasHedgehog = new TextureAtlas("atlas/hedgehog/unnamed.atlas");
        anmHedgehog = new Animation<TextureRegion>(1/5f, atlasHedgehog.findRegions("hedgehog"));
        anmHedgehog.setPlayMode(Animation.PlayMode.LOOP);
        time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrameHedgehog(){ return anmHedgehog.getKeyFrame(time);}

    public void setTime(float time) {
        this.time += time;
    }

    public void dispose () { atlasHedgehog.dispose(); }
}

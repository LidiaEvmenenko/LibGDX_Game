package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AnimDragon {
    private TextureAtlas atlas;
    private Animation<TextureRegion> anmAttack;
    private Animation<TextureRegion> anmDead;
    private Animation<TextureRegion> anm;
    private float time;

    public AnimDragon() {
        atlas = new TextureAtlas("atlas/dragon/unnamed.atlas");
        anmAttack = new Animation<TextureRegion>(1/5f, atlas.findRegions("attack"));
        anmDead = new Animation<TextureRegion>(1/5f, atlas.findRegions("dead"));
        anm = anmAttack;
        anmAttack.setPlayMode(Animation.PlayMode.LOOP);
        anmDead.setPlayMode(Animation.PlayMode.NORMAL);
        time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getFrameDragon(){ return anm.getKeyFrame(time);}

    public void setTime(float time) {
        this.time += time;
    }

    public void dispose () {
        atlas.dispose();
    }

    public void setDragonDead(){
        anm = anmDead;
    }
}

package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Anim;
import com.mygdx.game.base.BaseScreen;
public class MenuScreen extends BaseScreen{
    private Vector2 pos;
    private Vector2 v;
    private boolean dir=false;
    private Anim animation;

    @Override
    public void show() {
        super.show();
        animation = new Anim("boy.png", 7, 4, Animation.PlayMode.LOOP);
        pos = new Vector2();
        v = new Vector2(1, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        animation.setTime(Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) { dir = true; }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) { dir = false; }
        if (!animation.getFrame().isFlipX() && dir) {
            animation.getFrame().flip(true, false);
            v.set(-1, 0);
        }//налево
        if (animation.getFrame().isFlipX() && !dir) {
            animation.getFrame().flip(true, false);
            v.set(1, 0);
        }//направо
        batch.begin();
        batch.draw(animation.getFrame(), pos.x, pos.y);
        batch.end();
        pos.add(v);
        if (pos.x >= Gdx.graphics.getWidth() - animation.getFrame().getRegionWidth()){
            v.set(-1, 0);
            animation.getFrame().flip(true, false);
            dir= !dir;
        }
        if (pos.x == 0){
            v.set(1, 0);
            animation.getFrame().flip(true, false);
            dir= !dir;
        }

    }

    @Override
    public void dispose() {
        super.dispose();
        animation.dispose();
    }
}

package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Anim {

    private Texture img;
    private Animation<TextureRegion> anm;
    private float time;


    public Anim(String name, int col, int row, Animation.PlayMode playMode){
        img = new Texture(name);
        img = new Texture("boy.png");

        TextureRegion region0 = new TextureRegion(img);
        int xCnt = region0.getRegionWidth() / col;
        int yCnt = region0.getRegionHeight() / row;
        TextureRegion[][] regions0 = region0.split(xCnt, yCnt);
        TextureRegion[] region1 = new TextureRegion[regions0.length * regions0[0].length];
        int cnt = 0;
        for (int i=0; i < regions0.length; i++){
            for (int j=0; j < regions0[0].length; j++){
                region1[cnt++] = regions0[i][j];
            }
        }
        anm = new Animation<>(1/60f, region1); // 1/60 это скорость отрисовки
        //  anm.setFrameDuration(1/15f); //изменение скорости отрисовки
        anm.setPlayMode(playMode); // режим воспроизведения
        //NORMAL при увеличении времени работает с 0 кадра и до последнего
        //REVERSED при 0 времени показывает последний кадр
        //LOOP ходит по циклу от 0 до конца
        //LOOP_PINGPONG ходит слева на право до конца и обратно
        //LOOP_RANDOM даем время - показывает разные кадры
        //LOOP_REVERSED LOOP задом наперед
    }
    public TextureRegion getFrame(){
        return anm.getKeyFrame(time);}

    public void setTime(float time) {
        this.time += time;
    }

    public boolean isAnimationOver() {return anm.isAnimationFinished(time);}
    public void zeroTime() {this.time = 0;}
    public void setPlayMode(Animation.PlayMode playMode) {anm.setPlayMode(playMode);}
    public void dispose () { img.dispose();}

}

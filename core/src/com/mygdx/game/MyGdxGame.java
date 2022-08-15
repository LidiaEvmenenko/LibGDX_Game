package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int clk;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		img = new Texture("piglet.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear((float) 0.7, (float) 0.7, (float) 0.7, 1);
		float x = Gdx.input.getX() - img.getWidth()/2; // х где находится мышка
		float y = Gdx.graphics.getHeight() - Gdx.input.getY() - img.getHeight()/2;
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) clk++;
		Gdx.graphics.setTitle("Clicked " + clk + " times");
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

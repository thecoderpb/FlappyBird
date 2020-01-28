package com.pratik.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;
	private int flapState = 0;

	private Texture[] birds;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
	}

	@Override
	public void render () {


		batch.begin();

		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(flapState == 0){
			flapState=1;
		}else {
			flapState=0;
		}

		batch.draw(birds[flapState], Gdx.graphics.getWidth()/2f - birds[flapState].getWidth()/2f,Gdx.graphics.getHeight()/2f - birds[flapState].getHeight()/2f);


		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}

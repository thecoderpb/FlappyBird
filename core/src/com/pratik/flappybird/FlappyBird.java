package com.pratik.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background,topTube,bottomTube;
	private int flapState = 0;
	private int gravity,velocity;
	private float birdY;

	private Texture[] birds;
	private int gameState = 0;

	private Random random;
	private int gap = 200;

	@Override
	public void create () {

		batch = new SpriteBatch();
		background = new Texture("bg.png");
		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");

		birdY = Gdx.graphics.getHeight()/2f - birds[flapState].getHeight()/2f;
		velocity = 2;
		gravity = 2;

		random = new Random();

	}

	@Override
	public void render () {


		batch.begin();

		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(Gdx.input.justTouched()){
			gameState = 1;
			velocity = -30;
			gap = random.nextInt(Gdx.graphics.getHeight()/2);
		}

		if(gameState == 1){

			if(flapState == 0){
				flapState=1;
			}else {
				flapState=0;
			}

			if(birdY>0 || velocity<0){

				velocity = velocity +gravity;
				birdY = birdY - velocity;

			}

		}

        batch.draw(topTube,Gdx.graphics.getWidth()/2f - topTube.getWidth()/2f,Gdx.graphics.getHeight()/2f + gap );
        batch.draw(bottomTube,Gdx.graphics.getWidth()/2f - topTube.getWidth()/2f,-Gdx.graphics.getHeight()/2f + gap );

		batch.draw(birds[flapState], Gdx.graphics.getWidth()/2f - birds[flapState].getWidth()/2f,birdY);

		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}

package com.pratik.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

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
	private int gap = 0;

	private int[] tubeX;
	private float[] offsets;
	private int distanceBetweenTubes;

	Circle birdCircle = new Circle();
	Rectangle[] topTubeRectangle;
	Rectangle[] bottomTopTubeRectangle;
	ShapeRenderer shapeRenderer;

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



		tubeX = new int[4];
		offsets = new float[4];
		topTubeRectangle = new Rectangle[4];
		bottomTopTubeRectangle = new Rectangle[4];
		distanceBetweenTubes = Gdx.graphics.getWidth()*3/4;    // for every distance 0.75 of screen width, new tube is generated;

		for(int i = 0; i<4 ; i++){

			tubeX[i] = Gdx.graphics.getWidth()/2-topTube.getWidth()/2 + Gdx.graphics.getWidth()+ i*distanceBetweenTubes;
			offsets[i] = random.nextInt(Gdx.graphics.getHeight()*3/4)*0.5f;

			topTubeRectangle[i] = new Rectangle();
			bottomTopTubeRectangle[i] = new Rectangle();
		}



		birdCircle = new Circle();
		shapeRenderer = new ShapeRenderer();


	}

	@Override
	public void render () {


		batch.begin();

		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(Gdx.input.justTouched()){
			gameState = 1;
			velocity = -30;

		}

		if(gameState == 1){

			if(flapState == 0){           // Check Flap State
				flapState=1;
			}else {
				flapState=0;
			}

			if(birdY>0 || velocity<0){

				velocity = velocity +gravity;
				birdY = birdY - velocity;            // Makes the bird flap

			}

			/**
			  This for loop checks if tube has moved away from screen. If yes then generate a tube
			 */

			for (int i = 0; i < 4; i++) {          //Generates set of 4 tubes

				if(tubeX[i]<-topTube.getWidth()){   // If tube is out of the screen move it to the other side of the screen

					tubeX[i] += 4*distanceBetweenTubes; // 4 is the number of tubes


				}else {
					tubeX[i] = tubeX[i] - 4;

				}

				//draw tubes
				batch.draw(topTube,tubeX[i],Gdx.graphics.getHeight()/2f + gap/2f + offsets[i]);
				batch.draw(bottomTube,tubeX[i],-Gdx.graphics.getHeight()/2f + gap/2f  + offsets[i]);

				topTubeRectangle[i] = new Rectangle( tubeX[i], Gdx.graphics.getHeight() / 2f + gap / 2f + offsets[i],topTube.getWidth(),topTube.getHeight());
				bottomTopTubeRectangle[i] = new Rectangle( tubeX[i], -Gdx.graphics.getHeight() / 2f + gap / 2f  + offsets[i],topTube.getWidth(),topTube.getHeight());


			}

		}

		batch.draw(birds[flapState], Gdx.graphics.getWidth()/2f - birds[flapState].getWidth()/2f,birdY);

		birdCircle.set(Gdx.graphics.getWidth()/2,birdY +birds[flapState].getHeight()/2f,birds[flapState].getHeight()/2);


		//ShapeRenderer Class
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(Gdx.graphics.getWidth()/2,birdY +birds[flapState].getHeight()/2f,birds[flapState].getHeight()/2);


		for (int i=0;i<4;i++) {
			shapeRenderer.rect(tubeX[i],Gdx.graphics.getHeight() / 2f + gap / 2f + offsets[i],topTube.getWidth(),topTube.getHeight());
			shapeRenderer.rect(tubeX[i],-Gdx.graphics.getHeight() / 2f + gap / 2f  + offsets[i],topTube.getWidth(),topTube.getHeight());

			/*if(Intersector.overlaps(birdCircle,topTubeRectangle[i])||Intersector.overlaps(birdCircle,bottomTopTubeRectangle[i])){
				gameState=2;

			}*/


		}

		shapeRenderer.end();

		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}

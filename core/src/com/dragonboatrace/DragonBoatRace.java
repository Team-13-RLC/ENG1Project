package com.dragonboatrace;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Boat;
import com.dragonboatrace.entities.BoatType;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.ObstacleType;


public class DragonBoatRace extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Boat myBoat;
	int frameDelay;

	ArrayList<Obstacle> obstacleList;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		obstacleList = new ArrayList<Obstacle>();
		myBoat = new Boat(new Vector2(50, 100), BoatType.SMALL);
		frameDelay = 0;
	}

	@Override
	public void render () {
		batch.begin();
		float deltaTime = Gdx.graphics.getDeltaTime();
		if (frameDelay <= 0){
			if ((Math.random()) > 0.5){
				obstacleList.add(new Obstacle(ObstacleType.BIRD));
			} else{
				 obstacleList.add(new Obstacle(ObstacleType.ROCK));
			}
			frameDelay = (int)(Math.random()*(175 - 50)+50);
		}
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

		Iterator<Obstacle> eIter = obstacleList.iterator();
		while (eIter.hasNext()){
			Obstacle ent = eIter.next();
			if (ent.getPos().y+ent.getType().getHeight() <= 0){
				eIter.remove();
				ent.dispose();
			} else if (myBoat.collision(ent)){
				eIter.remove();
				ent.dispose();
			} else {
				ent.update(deltaTime);
				ent.render(batch);
			}
		}


		myBoat.update(deltaTime);
		myBoat.render(batch);
		frameDelay--;
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Obstacle ent : obstacleList){
			ent.dispose();
		}
		myBoat.dispose();
	}

}

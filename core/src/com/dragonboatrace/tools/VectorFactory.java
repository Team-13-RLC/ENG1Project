package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;

import java.util.concurrent.ThreadLocalRandom;

public class VectorFactory {
    public static Vector2 randomPosition (EntityType entityType, float laneLeftBound, int laneWidth) {
        float middleOfLane = ((int) laneLeftBound + laneWidth) / 2.0f;
        return new Vector2(
                ((int) laneLeftBound + laneWidth) / 2.0f +
                        ThreadLocalRandom.current().nextInt(
                                -(int)middleOfLane + entityType.getWidth() / 2,
                                (int)middleOfLane + entityType.getWidth() / 2
                        ),
                Gdx.graphics.getHeight());
    }

    public static Vector2 boatPosition(float laneLeftBound, int laneWidth){
        return new Vector2(
                laneLeftBound + (laneWidth - EntityType.BOAT.getWidth()) / 2.0f,
                100
        );
    }

}

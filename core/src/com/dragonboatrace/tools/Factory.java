package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.EntityType;

import java.util.concurrent.ThreadLocalRandom;

public class Factory {
    public static Vector2 randPosVec (EntityType entityType, float laneLeftBound, int laneWidth) {
        return new Vector2(
                ((int) laneLeftBound + laneWidth) / 2.0f +
                        ThreadLocalRandom.current().nextInt(
                                -((int) laneLeftBound + laneWidth) / 2 + entityType.getWidth() / 2,
                                ((int) laneLeftBound + laneWidth) / 2 + entityType.getWidth() / 2
                        ),
                Gdx.graphics.getHeight());
    }
}

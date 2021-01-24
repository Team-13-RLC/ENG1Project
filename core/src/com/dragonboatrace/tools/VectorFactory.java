package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Contains static methods to create various vectors.
 */
public class VectorFactory {
    /**
     * Returns a random position inside of a lane.
     * @param entityType type of an entity this is the position of.
     * @param laneLeftBound x value of the left edge of the lane.
     * @param laneWidth width of the lane
     * @return Vector2 of the position
     */
    public static Vector2 randomPosition(EntityType entityType, float laneLeftBound, int laneWidth) {
        float middleOfLane = ((int) laneLeftBound + laneWidth) / 2.0f;
        return new Vector2(
                ((int) laneLeftBound + laneWidth) / 2.0f +
                        ThreadLocalRandom.current().nextInt(
                                -(int) middleOfLane + entityType.getWidth() / 2,
                                (int) middleOfLane + entityType.getWidth() / 2
                        ),
                Gdx.graphics.getHeight());
    }

    /**
     * Returns a boat's starting position in a lane
     * @param laneLeftBound x value of the left edge of the lane.
     * @param laneWidth width of the lane
     * @return Vector2 of the position
     */
    public static Vector2 boatPosition(float laneLeftBound, int laneWidth) {
        return new Vector2(
                laneLeftBound + (laneWidth - EntityType.BOAT.getWidth()) / 2.0f,
                100
        );
    }

}

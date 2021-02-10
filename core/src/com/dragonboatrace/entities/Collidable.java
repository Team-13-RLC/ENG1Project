package com.dragonboatrace.entities;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.tools.CollidableEffect;
import com.dragonboatrace.tools.CollidableStats;
import com.dragonboatrace.tools.VectorFactory;

/**
 * Represents a Collidable.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class Collidable extends Entity {

    /**
     * The speed of the collidable.
     */
    private final float speed;
    /**
     * Effect the collidable will have on the boat upon collision (positive or negative)
     */
    private final CollidableEffect effect;

    private final CollidableType type;

    /**
     * Creates a new Collidable of a specific type and bounds in which it can be created.
     *
     * @param type          The type of collidable.
     * @param laneLeftBound The starting x value the collidable can be created in.
     * @param laneWidth     How far from startX the collidable can be created.
     */
    public Collidable(CollidableType type, float laneLeftBound, int laneWidth) {
        /* Entity creation */
        /* Form of Entity(Vector2 pos, Vector2 vel, EntityType type, String texture) */
        super(VectorFactory.randomPosition(EntityType.OBSTACLE, laneLeftBound, laneWidth),
                new Vector2(),
                EntityType.OBSTACLE,
                type.getTexture());

        this.speed = type.getSpeed();
        this.effect = type.getEffect();
        this.type = type;

    }

    /**
     * Update the collidable's position relative to the time passed since last frame and the velocity of the boat in that lane.
     *
     * @param deltaTime The time since last frame.
     * @param velY      The y-velocity of the boat in the same lane as the collidable.
     */
    public void update(float deltaTime, float velY) {
        this.position.add(0, -1 * (velY + this.speed) * deltaTime);
        this.hitbox.move(this.position.x, this.position.y);
    }

    /**
     * Get the collidables speed attribute, not the velocity it is moving at currently.
     *
     * @return A float of the collidables speed attribute.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Invoke the effect of the collidable on the boat.
     *
     * @param boat Which boat is affected
     */
    public void takeEffect(Boat boat) {
        effect.invoke(boat);
    }

    /**
     * Is the object a powerup or an obstacle
     * @return true for powerup, false otherwise
     */
    public boolean isPowerup() {
        return type.ordinal() >= CollidableStats.POWERUPS_START_AT_INDEX;
    }

    /**
     * The position of the collidable.
     *
     * @return A Vector2 of the position of the collidable.
     */
    public Vector2 getPos() {
        return this.position;
    }

}

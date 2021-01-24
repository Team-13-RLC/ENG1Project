package com.dragonboatrace.entities;

import com.dragonboatrace.tools.PowerupEffect;

import static com.dragonboatrace.tools.PowerupStats.*;

/**
 * Represents a type of collidable (obstacle or powerup)
 *
 * @author Benji Garment, Joe Wrieden
 */
public enum ObstacleType {
    /* ENUM(texture, speed, effect)*/
    ROCK("rock.png", 50, boat -> {
        boat.addHealth(-ROCK_DAMAGE * boat.getBuff());
    }),
    BRANCH("branch.png", 60, boat -> {
        boat.addHealth(-BRANCH_DAMAGE * boat.getBuff());
    }),
    LEAF("leaf.png", 75, boat -> {
        boat.addHealth(-LEAF_DAMAGE * boat.getBuff());
    });

    /**
     * The texture of the obstacle.
     */
    private final String texture;
    /**
     * The base speed at which the obstacle moves.
     */
    private final float speed;
    /**
     * The damage the obstacle type deal at a collision.
     */
    private final PowerupEffect effect;

    /**
     * Creates a new type of obstacle with a given texture, base speed and an effect.
     *
     * @param texture The path to the obstacles texture.
     * @param speed   The speed of the obstacle type.
     * @param effect  The effect of the Collidable on the boat
     */
    ObstacleType(String texture, float speed, PowerupEffect effect) {
        this.texture = texture;
        this.speed = speed;
        this.effect = effect;
    }

    /**
     * Get the base speed of the obstacle type.
     *
     * @return A float representing the speed of the obstacle type.
     */
    public float getSpeed() {
        return speed;
    }

    public PowerupEffect getEffect(){
        return effect;
    }

    /**
     * Get the obstacle types texture
     *
     * @return A string representing the path to the obstacles texture.
     */
    public String getTexture() {
        return this.texture;
    }
}
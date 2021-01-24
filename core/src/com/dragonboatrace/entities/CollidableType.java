package com.dragonboatrace.entities;

import com.dragonboatrace.tools.CollidableEffect;
import com.dragonboatrace.tools.CollidableTimer;
import com.dragonboatrace.tools.Settings;

import java.util.concurrent.ThreadLocalRandom;

import static com.dragonboatrace.tools.CollidableStats.*;

/**
 * Represents a type of collidable (obstacle or powerup)
 *
 * @author Benji Garment, Joe Wrieden
 */
public enum CollidableType {
    /* ENUM(texture, speed, effect)*/
    ROCK("rock.png", 50, boat -> {
        boat.addHealth(-ROCK_DAMAGE * boat.getBuff());
    }),
    BRANCH("branch.png", 60, boat -> {
        boat.addHealth(-BRANCH_DAMAGE * boat.getBuff());
    }),
    LEAF("leaf.png", 75, boat -> {
        boat.addHealth(-LEAF_DAMAGE * boat.getBuff());
    }),
    /**
     * Invulnerability. Setts the damage multiplier to 0 for a set amount of time, then sets it back to 1.
     */
    INVULN("invuln.png", 0, boat -> {
        boat.setBuff(0);
        new CollidableTimer(INVULN_FOR, () -> {
            boat.setBuff(1); // no buff
        });
    }),

    /**
     * Increase the speed of the boat. Adds a certain amount to the current speed.
     * Subtracts that amount after a certain time has passed.
     * Speed is not just reset to the original number, in case the boat picks up another speed boost.
     */
    SPEEDUP("speedup.png", 0, boat -> {
        boat.addSpeed(SPEEDUP_BY);
        new CollidableTimer(SPEEDUP_FOR, () -> boat.addSpeed(-SPEEDUP_BY));
    }),

    /**
     * Reduce how much damage the boat takes. Like INVULN, but multiplier is not set to 0,
     * so the boat still takes some damage. Multiplier is reset after a certain time has passed.
     * These do not stack. No matter how many are picked up between picking up the first one and it running out,
     * the multiplier will be reset to 1 when the first one runs out.
     */
    LESSDAMAGE("lessdamage.png", 0, boat -> {
        boat.setBuff(LESSDAMAGE_BY);
        new CollidableTimer(LESSDAMAGE_FOR, () -> {
            boat.setBuff(1); // no buff
        });
    }),

    /**
     * Reduce the amount of recorded time for the current round. No timeout.
     */
    LESSTIME("lesstime.png", 0, boat -> {
        boat.setTime(boat.getTime() - LESSTIME_BY);
    }),

    /**
     * Increase the amount of health a boat has, up to the limit of its type. No timeout.
     * This one adds health as a fraction of total health, unlike the others which all are all constant.
     * This is subject to change.
     */
    HEAL("heal.png", 0, boat -> {
        boat.addHealth(boat.getBoatType().getHealth() * HEAL_BY);
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
    private final CollidableEffect effect;

    /**
     * Creates a new type of obstacle with a given texture, base speed and an effect.
     *
     * @param texture The path to the obstacles texture.
     * @param speed   The speed of the obstacle type.
     * @param effect  The effect of the Collidable on the boat
     */
    CollidableType(String texture, float speed, CollidableEffect effect) {
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

    public CollidableEffect getEffect() {
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

    public static int getWeightedRandom(){
        double p = Math.random();
        if (p < Settings.OBSTACLE_SPAWN_CHANCE){
            return ThreadLocalRandom.current().nextInt(0, POWERUPS_START_AT_INDEX);
        }
        return ThreadLocalRandom.current().nextInt(POWERUPS_START_AT_INDEX, CollidableType.values().length);


    }
}
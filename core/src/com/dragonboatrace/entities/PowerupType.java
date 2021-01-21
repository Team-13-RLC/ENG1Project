package com.dragonboatrace.entities;

import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.tools.PowerupEffect;
import com.dragonboatrace.tools.PowerupTimer;

/**
 * Represents a type of powerup. Specifies the effect of each one.
 *
 * Each value in the enum contains the name of the texture
 * and a functional interface which takes a Boat object as a parameter and returns nothing.
 * This is the effect of the powerup.
 *
 * For all types which require a timer, a PowerupTimer object is constructed.
 * Another functional interface is passed to it, however, the Boat is already bound to it,
 * hence it takes no parameters (and still returns nothing).
 */
public enum PowerupType {

    /**
     * Invulnerability. Setts the damage multiplier to 0 for a set amount of time, then sets it back to 1.
     */
    INVULN("invuln.png", boat -> {
        float invulnFor = 5;
        boat.setBuff(0);
        new PowerupTimer(invulnFor,  () -> {
            boat.setBuff(1); // no buff
        });
    }),

    /**
     * Increase the speed of the boat. Adds a certain amount to the current speed.
     * Subtracts that amount after a certain time has passed.
     * Speed is not just reset to the original number, in case the boat picks up another speed boost.
     */
    SPEEDUP("speedup.png", boat -> {
        float speedUpFor = 5;
        float speedUpBy = 30;
        boat.addSpeed(speedUpBy);
        new PowerupTimer(speedUpFor, () -> boat.addSpeed(-speedUpBy));
    }),

    /**
     * Reduce how much damage the boat takes. Like INVULN, but multiplier is not set to 0,
     * so the boat still takes some damage. Multiplier is reset after a certain time has passed.
     * These do not stack. No matter how many are picked up between picking up the first one and it running out,
     * the multiplier will be reset to 1 when the first one runs out.
     */
    LESSDAMAGE("lessdamage.png", boat -> {
        float lessDamageFor = 5;
        float lessDamageBy = 0.5F;
        boat.setBuff(lessDamageBy);
        new PowerupTimer(lessDamageFor, () -> {
            boat.setBuff(1); // no buff
        });
    }),

    /**
     * Reduce the amount of recorded time for the current round. No timeout.
     */
    LESSTIME("lesstime.png", boat -> {
        float decreaseTimeBy = 5F; // TODO: move all of these into a separate file
        boat.setTime(boat.getTime() - decreaseTimeBy);
    }),

    /**
     * Increase the amount of health a boat has, up to the limit of its type. No timeout.
     * This one adds health as a fraction of total health, unlike the others which all are all constant.
     * This is subject to change.
     */
    HEAL("heal.png", boat -> {
        float healBy = 0.25F;
        boat.addHealth(boat.getBoatType().getHealth() * healBy);
    });

    /**
     * Name of the powerup's texture
     */
    private final String texture;

    /**
     * Functional interface controlling the behaviour of the powerup
     */
    private final PowerupEffect effect;

    /**
     * Create new powerup type.
     * @param texture name of the texture
     * @param effect effect of the powerup
     */
    PowerupType(String texture, PowerupEffect effect) {
        this.texture = texture;
        this.effect = effect;
    }


    /**
     * Invokes the functional interface associated with a powerup type.
     * @param boat boat which the powerup applies to.
     */
    public void takeEffect(Boat boat) {
        effect.invoke(boat);
    }

    /**
     * @return name of the texture
     */
    public String getTexture() {
        return texture;
    }
}

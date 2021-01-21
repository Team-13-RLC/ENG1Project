package com.dragonboatrace.entities;

import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.tools.PowerupEffect;
import com.dragonboatrace.tools.PowerupTimer;

public enum PowerupType {
    INVULN("invuln.png", boat -> {
        float invulnFor = 5;
        new PowerupTimer(invulnFor,  () -> {
            // be invulnerable
        });
    }),
    SPEEDUP("speedup.png", boat -> {
        float speedUpFor = 5;
        float speedUpBy = 30;
        boat.addSpeed(speedUpBy);
        new PowerupTimer(speedUpFor, () -> boat.addSpeed(-speedUpBy));

    }),
    LESSDAMAGE("lessdamage.png", boat -> {
        float lessDamageFor = 5;
        new PowerupTimer(lessDamageFor, () -> {
            // Take less damage
        });

    }),
    LESSTIME("lesstime.png", boat -> {
        float decreaseTimeBy = -5F; // TODO: move all of these into a separate file
        boat.setTime(decreaseTimeBy);
    }),
    HEAL("heal.png", boat -> {
        float healBy = 0.25F;
        boat.addHealth(boat.getBoatType().getHealth() * healBy);
    });

    private final String texture;
    private final PowerupEffect effect;

    PowerupType(String texture, PowerupEffect effect) {
        this.texture = texture;
        this.effect = effect;
    }

    public void takeEffect(Boat boat) {
        effect.invoke(boat);
    }

    public String getTexture() {
        return texture;
    }
}

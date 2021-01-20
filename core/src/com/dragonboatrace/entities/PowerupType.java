package com.dragonboatrace.entities;

import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.tools.PowerupEffect;

public enum PowerupType {
    INVULN("invuln.png", boat -> {
    }),
    SPEEDUP("speedup.png", boat -> {
    }),
    LESSDAMAGE("lessdamage.png", boat -> {
    }),
    LESSTIME("lesstime.png", boat -> {
    }),
    HEAL("heal.png", boat -> {
    });

    String texture;
    PowerupEffect effect;

    PowerupType(String texture, PowerupEffect effect) {
        this.texture = texture;
        this.effect = effect;
    }

    public void takeEffect(Boat boat){
        effect.invoke(boat);
    }

    public String getTexture() {
        return texture;
    }
}

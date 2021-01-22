package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.VectorFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class of all powerups. Currently inheriting from Entity, but should probably inherit from something else.
 * Constructs the powerup and stores its type. Updates the position whe update is called.
 */
public class Powerup extends Entity{
    PowerupType type;
    Powerup(PowerupType type, float startX, int width){
        super(VectorFactory.randPosVec(EntityType.POWERUP, startX, width),
                new Vector2(),
                EntityType.POWERUP,
                type.getTexture()
        );
        this.type = type;
    }

    public void update(float deltaTime, float velY) {
        this.position.add(0, -1 * (velY) * deltaTime);
        this.hitbox.move(this.position.x, this.position.y);
    }
}

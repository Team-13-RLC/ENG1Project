package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class of all powerups. Currently inheriting from Entity, but should probably inherit from something else.
 * Constructs the powerup and stores its type. Updates the position whe update is called.
 */
public class Powerup extends Entity{
    PowerupType type;
    Powerup(PowerupType type, float startX, int width){
        super(new Vector2(((int) startX + width) / 2.0f + ThreadLocalRandom.current().nextInt(-((int) startX + width) / 2 + EntityType.OBSTACLE.getWidth() / 2, ((int) startX + width) / 2 + EntityType.POWERUP.getWidth() / 2), Gdx.graphics.getHeight()), new Vector2(), EntityType.POWERUP, type.getTexture());
        this.type = type;
    }

    public void update(float deltaTime, float velY) {
        this.position.add(0, -1 * (velY) * deltaTime);
        this.hitbox.move(this.position.x, this.position.y);
    }
}

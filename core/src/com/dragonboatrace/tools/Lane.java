package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Collidable;
import com.dragonboatrace.entities.CollidableType;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

import static com.dragonboatrace.tools.Settings.COLLIDABLE_SPAWN_RATE_MAX;
import static com.dragonboatrace.tools.Settings.COLLIDABLE_SPAWN_RATE_MIN;

/**
 * Represents a Lane in a {@link Race}.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class Lane {

    /**
     * The hit box of the lane, used to check if a boat is in the lane.
     */
    private final Hitbox area;
    /**
     * A list of the collidables currently in the lane.
     */
    private final ArrayList<Collidable> collidables;
    /**
     * A list of times to wait before adding a new collidable to the lane.
     */
    private final ArrayList<Float> randomWaitTimes;

    /**
     * Creates a new lane at a position and with a width and uses the round number to change the number of collidables.
     * @param pos The position of the lane in the screen.
     * @param width The width of the lane.
     * @param round The current round, used to increase difficulty.
     */
    public Lane(Vector2 pos, int width, int round) {
        this.area = new Hitbox(pos.x, pos.y, width, Gdx.graphics.getHeight() + 200);
        this.collidables = new ArrayList<>();
        this.randomWaitTimes = new ArrayList<>();
        populateList(round);
    }

    /**
     * Update the collidables in the lane, remove any that are no longer on screen and replace them at a random time.
     *
     * @param deltaTime The time since the last frame.
     * @param velY      The y-velocity of the boat in the lane.
     */
    public void update(float deltaTime, float velY) {

        /* Check for collisions */
        ListIterator<Collidable> iter = collidables.listIterator();
        while (iter.hasNext()) {
            Collidable collidable = iter.next();
            collidable.update(deltaTime, velY);
            if (collidable.getHitBox().leaves(this.area)) {
                iter.remove();
                replaceCollidable();
            }
        }

        /* Randomly replace collidables */
        ListIterator<Float> times = randomWaitTimes.listIterator();
        while (times.hasNext()) {
            float time = times.next() - deltaTime;
            if (time > 0) {
                times.set(time);
            } else {
                collidables.add(randomCollidable());
                times.remove();
            }
        }
    }

    /**
     * Render the collidables in the lane.
     *
     * @param batch The SpriteBatch to be added to.
     */
    public void render(SpriteBatch batch) {
        for (Collidable collidable : collidables) {
            collidable.render(batch);
        }
    }

    /**
     * Get the list of all collidables in the lane.
     *
     * @return An {@link ArrayList} of type {@link Collidable} with all the collidables in the lane.
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    public ArrayList<Float> getRandomWaitTimes() {
        return randomWaitTimes;
    }

    /**
     * Get the lanes hit box
     *
     * @return A {@link Hitbox} representing the lanes area.
     */
    public Hitbox getHitbox() {
        return this.area;
    }

    /**
     * Create a random time at which to add an {@link Collidable} to the lane.
     */
    public void replaceCollidable() {
        randomWaitTimes.add(COLLIDABLE_SPAWN_RATE_MIN + COLLIDABLE_SPAWN_RATE_MAX * ThreadLocalRandom.current().nextFloat());
    }

    /**
     * Create a new random {@link Collidable}
     *
     * @return a new {@link Collidable} in the lanes area.
     */
    private Collidable randomCollidable() {
        int rand = CollidableType.getWeightedRandom();
        return new Collidable(CollidableType.values()[rand], this.area.getX(), this.area.getWidth());
    }

    /**
     * Fill the list with collidables that will start at random times.
     * @param round The current round increases the number of collidables.
     */
    private void populateList(int round) {
        for (int i = 0; i < (11 - Settings.PLAYER_COUNT + round - 1); i++) {
            replaceCollidable();
        }
    }

    public void dispose() {
        for (Collidable obst : collidables) {
            obst.dispose();
        }
    }

}
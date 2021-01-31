package com.dragonboatrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.screens.MainMenuScreen;
import com.dragonboatrace.tools.Prefs;
import com.dragonboatrace.tools.Settings;

import java.util.Arrays;

/**
 * Represents the Game itself and holds all the screens.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class DragonBoatRace extends Game {

    /**
     * The Spritebatch used to group all renders.
     */
    protected SpriteBatch batch;

    /**
     * The current round.
     */
    protected int round = 1;

    /**
     * A list of cumulative times for all boats.
     */
    protected Float[] totalTimes = new Float[Settings.PLAYER_COUNT];
    //This is using Float instead of float, because of what happens to this array later.

    /**
     * The players total time.
     */
    protected float playerTotalTime = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));

        Arrays.fill(totalTimes, 0f);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public int getRound() {
        return this.round;
    }

    public void setRound(int i) {
        this.round += i;
    }

    public void upRound() {
        this.round += 1;
    }

    public void setTimeAt(int i, float t) {
        totalTimes[i] += t;
    }

    public float getPlayerTotalTime() {
        return this.playerTotalTime;
    }

    public void setPlayerTotalTime(float t) {
        this.playerTotalTime += t;
    }

    public Float[] getTotalTimes() {
        return this.totalTimes;
    }

    public void restore() {
        round = Prefs.Restore.getInteger("round");
        playerTotalTime = Prefs.Restore.getFloat("playerTotalTime");
        totalTimes = Prefs.Restore.getArray("totalTimes");
    }
}

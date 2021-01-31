package com.dragonboatrace.tools;

/**
 * Static class used to hold settings
 *
 * @author Benji Garment, Joe Wrieden
 */
public class Settings {

    /**
     * The width of the window.
     */
    public static int WIDTH = 1920;

    /**
     * The height of the window.
     */
    public static int HEIGHT = 1080;

    /**
     * If the game is fullscreen or not.
     */
    public static boolean FULLSCREEN = true;

    /**
     * The global scalar at which to scale entities.
     */
    public static int SCALAR = 1;

    /**
     * The number of boats in the game, including the player itself.
     */
    public static int PLAYER_COUNT = 8;

    /**
     * Reduces the scalar that stamina gives when accelerating
     */
    public static int STAMINA_SPEED_DIVISION = 2;

    /**
     * Minimum time, in seconds, before the next collidable can spawn
     */
    public static float COLLIDABLE_SPAWN_RATE_MIN = 0.5f;

    /**
     * Maximum time, in seconds, before the next collidable can spawn
     */
    public static float COLLIDABLE_SPAWN_RATE_MAX = 1f;

    /**
     * Chance of an obsticle spawning rather then a powerup
     */
    public static double OBSTACLE_SPAWN_CHANCE = 0.5;

    /**
     * The velocity penalty given when a collision occurs
     */
    public static int OBSTACLE_COLLISION_PENALTY = -20;

    /**
     * The the boat must wait before moving again after a collision.
     */
    public static float OBSTACLE_COLLISION_TIME = 0.5f;

    /**
     * This number multiplied by the obstacle's damage gives the total amount of damage an obstacle deals.
     */
    public static float OBSTACLE_DAMAGE_MULTIPLIER = 1.0f;

    /**
     * Set the resolution of the screen.
     *
     * @param width  The width of the screen.
     * @param height The height of the screen.
     */
    public static void setResolution(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        SCALAR = 1920 / WIDTH + ((1920 % WIDTH == 0) ? 0 : 1);
    }

    /**
     * Set if the window should be fullscreen or not.
     *
     * @param FULLSCREEN A boolean if the window should be fullscreen.
     */
    public static void setFULLSCREEN(boolean FULLSCREEN) {
        Settings.FULLSCREEN = FULLSCREEN;
    }

    /**
     * Set the number of players in the game.
     *
     * @param playerCount The number of players to change to.
     */
    public static void setPlayerCount(int playerCount) {
        PLAYER_COUNT = playerCount;
    }

    /**
     * Update the Stamina Speed Scalar.
     *
     * @param staminaSpeedDivision The new scalar to use.
     */
    public static void setStaminaSpeedDivision(int staminaSpeedDivision) {
        STAMINA_SPEED_DIVISION = staminaSpeedDivision;
    }

    /**
     * Update the obstacle collision velocity penalty.
     *
     * @param obstacleCollisionPenalty The new penalty to use.
     */
    public static void setObstacleCollisionPenalty(int obstacleCollisionPenalty) {
        OBSTACLE_COLLISION_PENALTY = obstacleCollisionPenalty;
    }

    /**
     * Update the obstacle collision time penalty.
     *
     * @param obstacleCollisionTime The new penalty to use.
     */
    public static void setObstacleCollisionTime(float obstacleCollisionTime) {
        OBSTACLE_COLLISION_TIME = obstacleCollisionTime;
    }

    public static void restore() {
        STAMINA_SPEED_DIVISION = Prefs.Restore.getInteger("STAMINA_SPEED_DIVISION");
        COLLIDABLE_SPAWN_RATE_MIN = Prefs.Restore.getFloat("COLLIDABLE_SPAWN_RATE_MIN");
        COLLIDABLE_SPAWN_RATE_MAX = Prefs.Restore.getFloat("COLLIDABLE_SPAWN_RATE_MAX");
        OBSTACLE_SPAWN_CHANCE = Prefs.Restore.getFloat("OBSTACLE_SPAWN_CHANCE");
        OBSTACLE_COLLISION_PENALTY = Prefs.Restore.getInteger("OBSTACLE_COLLISION_PENALTY");
        OBSTACLE_COLLISION_TIME = Prefs.Restore.getFloat("OBSTACLE_COLLISION_TIME");
        OBSTACLE_DAMAGE_MULTIPLIER = Prefs.Restore.getFloat("OBSTACLE_DAMAGE_MULTIPLIER");
    }

    public static void save() {
        Prefs.Save.putInteger("STAMINA_SPEED_DIVISION", STAMINA_SPEED_DIVISION );
        Prefs.Save.putFloat("COLLIDABLE_SPAWN_RATE_MIN", COLLIDABLE_SPAWN_RATE_MIN );
        Prefs.Save.putFloat("COLLIDABLE_SPAWN_RATE_MAX", COLLIDABLE_SPAWN_RATE_MAX );
        Prefs.Save.putFloat("OBSTACLE_SPAWN_CHANCE",(float) OBSTACLE_SPAWN_CHANCE );
        Prefs.Save.putInteger("OBSTACLE_COLLISION_PENALTY", OBSTACLE_COLLISION_PENALTY );
        Prefs.Save.putFloat("OBSTACLE_COLLISION_TIME", OBSTACLE_COLLISION_TIME );
        Prefs.Save.putFloat("OBSTACLE_DAMAGE_MULTIPLIER", OBSTACLE_DAMAGE_MULTIPLIER );
    }
}
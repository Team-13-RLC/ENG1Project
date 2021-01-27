package com.dragonboatrace.tools;

public enum Difficulty {
    /* ENUM(setter)*/
    EASY(() -> setVars(0.2f, 0.5f, 0, 5, 10, 10)),
    MEDIUM(() -> setVars(0.5f, 1, 0.5f, 2.5f, 5, 5)),
    HARD(() -> setVars(0.8f, 2, 1, 0, 2.5f, 2.5f));

    private final Runnable setter;

    Difficulty(Runnable setter) {
        this.setter = setter;
    }
    public void set(){
        setter.run();
    }

    private static void setVars(float obstacleSpawnChance, float damageMult, float timePenalty, float invilnDur, float speedupDur, float lessdamageDur){
        Settings.OBSTACLE_SPAWN_CHANCE = obstacleSpawnChance;
        Settings.OBSTACLE_DAMAGE_MULTIPLIER = damageMult;
        Settings.OBSTACLE_COLLISION_TIME = timePenalty;

        CollidableStats.INVULN_FOR = invilnDur;
        CollidableStats.SPEEDUP_FOR = speedupDur;
        CollidableStats.LESSDAMAGE_FOR = lessdamageDur;
    }
}

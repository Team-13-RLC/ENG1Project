package com.dragonboatrace.tools;

public class CollidableStats {
    //TODO: final could get removed later, if these are used with the difficulty.
    public static float INVULN_FOR = 5.F;
    public static float SPEEDUP_FOR = 5.F;
    public static float LESSDAMAGE_FOR = 5.F;

    public final static float SPEEDUP_BY = 50.F;
    public final static float LESSDAMAGE_BY = 0.5F;
    public final static float LESSTIME_BY = 0.5F;
    public final static float HEAL_BY = 0.25F;


    public final static float ROCK_DAMAGE = 20F;
    public final static float BRANCH_DAMAGE = 10F;
    public final static float LEAF_DAMAGE = 5F;

    /**
     * Which Collidable is teh first powerup.
     */
    public final static int POWERUPS_START_AT_INDEX = 3;

    private CollidableStats(){} //Make sure this is never instantiated
}

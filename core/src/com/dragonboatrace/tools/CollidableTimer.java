package com.dragonboatrace.tools;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Asynchronous timer used for powerups which meed to be timed out.
 */
public class CollidableTimer {
    /**
     * The actual timer object.
     */
    Timer timer;

    /**
     * Instantiate teh timer and schedule a task to get executed.
     * @param seconds how long should the timer run for
     * @param effect function interface which is ran whe the timer expires.
     *               (in the form of a java Runnable)
     */
    public CollidableTimer(float seconds, Runnable effect) {
        timer = new Timer();
        timer.schedule(new RunTask(effect), (long)(seconds* 1000L));
    }

    /**
     * A task class, inheriting from TimerTask.
     * Stores the Runnable which will need to be executed when the timer is up.
     */
    class RunTask extends TimerTask {
        private final Runnable effect;
        RunTask(Runnable effect){
            super();
            this.effect = effect;
        }

        /**
         * Runs teh Runnable, terminates timer thread.
         * Is executed when teh timer is up.
         */
        public void run() {
            effect.run();
            timer.cancel();
        }
    }

}

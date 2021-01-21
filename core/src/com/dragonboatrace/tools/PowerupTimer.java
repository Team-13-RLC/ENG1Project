package com.dragonboatrace.tools;

import java.util.Timer;
import java.util.TimerTask;

public class PowerupTimer {
    Timer timer;

    public PowerupTimer(float seconds, Runnable effect) {
        timer = new Timer();
        timer.schedule(new RunTask(effect), (long)(seconds* 1000L));
    }

    class RunTask extends TimerTask {
        private final Runnable effect;
        RunTask(Runnable effect){
            super();
            this.effect = effect;
        }

        public void run() {
            effect.run();
            timer.cancel(); //Terminate the timer thread
        }
    }

}

package com.safenar.timer;

/**
 * States in which {@link TimerUtil timer} can be.
 */
public enum TimerStates {
    /**Timer in this state is up and running, ready to work.*/
    RUNNING,
    /**This state means that the timer is stopped, and no task scheduled will run. */
    STOPPED,
    /**The timer is cancelled, the memory is freed and
     * the best thing that can happen for this timer is GC destroying it.*/
    TO_DESTROY
}

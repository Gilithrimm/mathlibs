package com.safenar.timer;

import java.util.TimerTask;

//responsibility: improved TTask: can pause & resume
// TODO: 30.11.2022 documentation shit

/**
 * This class was meant to be (to some extent) a replacement for {@link TimerTask}
 * @apiNote cannot actually extend {@link TimerTask} 'cause it leaves huge memory footprint
 */
public class Task {
    /**
     * The actual task to be executed.
     * @see TimerTask
     */
    TimerTask task;
    /**
     * The initial delay after which the task is executed first time.
     */
    private final long delay;
    /**
     * Period of time at which the task is executed.
     */
    final long period;
    /**
     * How much time is left until next execution of the task.
     * Important for stopping and starting the timer.
     */
    long timeLeft;
    
    /**
     * Creates this task for one-time use.
     * @param task task to be executed
     * @param delay delay (in milliseconds) after which the task is executed.
     */
    public Task(TimerTask task, long delay) {
        this(task,delay,0);
        this.task=task;
    }
    
    /**
     * Creates this task for periodic use.
     * @param task task to be executed.
     * @param delay initial delay (in milliseconds) after which the task is executed.
     * @param period period after which task is sequentially executed.
     */
    public Task(TimerTask task, long delay, long period) {
        this.delay = delay;
        timeLeft = delay;
        this.period = period;
        this.task = new TimerTask() {
            @Override
            public void run() {
                task.run();
                timeLeft = Task.this.period;
            }
        };
    }
    
    /**
     * Cancels the task, rendering it unusable.
     * @deprecated There is <b>no test coverage</b> nor is it used in code,
     * which means there's <b>no guarantee</b> on how the task and the timer will behave.
     * <b>Use at your risk.</b>
     * @see TimerTask#cancel()
     */
    public void cancel(){
        task.cancel();
    }
    
    /**{@inheritDoc}*/
    @Override
    public String toString() {
        return "Task{" +
                " delay=" + delay +
                ", period=" + period +
                '}';
    }
}

package com.safenar.timer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is meant to be a replacement to {@link Timer}
 * which allows pausing (stopping) and resuming (restarting).
 */
public class TimerUtil {
    /**The underlying actual timer.*/
    private Timer timer;
    /**List of scheduled tasks.*/
    ArrayList<Task> tasks;
    private int size;
    /**State in which the timer currently is.*/
    private TimerStates state;
    
    /**
     * Initializes this timer.
     */
    public TimerUtil() {
        timer = new Timer();
        state = TimerStates.RUNNING;
        tasks = new ArrayList<>();
        size = 0;
    }
    
    public TimerStates getState() {
        return state;
    }
    
    /**
     * Schedules given task to be executed after a certain delay,
     * then repeats it after a certain period.
     * @param task task to be scheduled
     * @param delay initial delay after which the task is executed.
     * @param period a certain period after which next executions of the task happen.
     * @implSpec if the period is equal to 0, the task executes <b>once</b>.
     * @throws IllegalStateException if the timer was cancelled.
     * @see Timer#schedule(TimerTask, long, long)
     */
    public void schedule(TimerTask task, long delay, long period) {
        ifCancelled();
        add(task, delay, period);
        if (state != TimerStates.STOPPED) {
            if (period == 0)
                timer.schedule(tasks.get(size).task, delay);
            else
                timer.schedule(tasks.get(size).task, delay, period);
            setClock(tasks.get(size));
        }
        size++;
    }
    
    /**
     * Schedules given task to be executed once after a certain delay.
     * @param task task to be scheduled.
     * @param delay delay (in milliseconds) after which the task is executed.
     * @throws IllegalStateException if the timer was cancelled.
     * @see Timer#schedule(TimerTask, long)
     */
    public void schedule(TimerTask task, long delay) {
        schedule(task, delay, 0);
    }
    
    private void ifCancelled() {
        if (state == TimerStates.TO_DESTROY)
            throw new IllegalStateException("Timer already cancelled!");
    }

    private void add(TimerTask task, long delay, long period) {
        tasks.add(period == 0
                ? new Task(task, delay)
                : new Task(task, delay, period));
    }

    private void setClock(Task task) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.timeLeft--;
            }
        }, 0, 1);
    }
    
    /**
     * Stops the timer for indefinite period of time.
     * @throws IllegalStateException if the timer was cancelled.
     */
    public void stop() {
        ifCancelled();
        if (state == TimerStates.STOPPED) {
            System.out.println("Timer already stopped!");
            return;
        }
        timer.cancel();
        timer.purge();
        state = TimerStates.STOPPED;
        for (Task task : tasks)
            if (task.timeLeft < 0) {
                tasks.remove(task);
                size--;
            }
        System.out.println("Stopped!");
    }
    
    /**
     * Cancels the timer and frees up any resources related to it.
     * This method may be called repeatedly; the second and subsequent calls have no effect.
     * @see Timer#cancel()
     */
    public void cancel() {
        timer.cancel();
        timer.purge();
        state = TimerStates.TO_DESTROY;
        tasks = null;
        System.gc();
    }
    
    /**
     * Restarts the timer if it was stopped previously.
     * Reschedules all tasks to execute like the timer has never stopped.
     * @throws IllegalStateException if the timer was canceled previously.
     */
    public void restart() {
        ifCancelled();
        if (state == TimerStates.RUNNING) return;//or throw an Ex of some kind?
        timer = new Timer();
        for (Task task : tasks) {
            if (task.period != 0)
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        task.task.run();
                    }
                }, task.timeLeft, task.period);
            else timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    task.task.run();
                }
            }, task.timeLeft);
            setClock(task);
        }
        state = TimerStates.RUNNING;
    }
}

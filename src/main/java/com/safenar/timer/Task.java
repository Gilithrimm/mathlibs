package com.safenar.timer;

import java.util.TimerTask;

//responsibility: improved TTask: can pause & resume
class Task{
    TimerTask task;
    String id;
    long delay,period,timeLeft;

    public Task(TimerTask task, long delay,String id) {
        this.id=id;
        this.task = task;
        this.delay = delay;
        timeLeft=delay;
    }

    public Task(TimerTask task, long delay, long period, String id) {
        this(task, delay, id);
        this.period = period;
        this.task=new TimerTask() {
            @Override
            public void run() {
                task.run();
                timeLeft=Task.this.period;
            }
        };
    }

    @Override
    public String toString() {
        return "Task{" +
                " id=" + id +
                ", delay=" + delay +
                ", period=" + period +
                '}';
    }
}

package com.safenar.timer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

class TimerUtilTest {
    TimerUtil timer;
    TimerTask testTask;

    @BeforeEach
    void setUp() {
        timer = new TimerUtil();
//        timer.stop();
        testTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hi");
            }
        };
        timer.restart();
    }

    @AfterEach
    void tearDown() {
//        timer.stop();//this shit fails 3-4 tests, lack of it burns my pc
        timer.cancel();//commenting this out=+100 fails
        testTask.cancel();
//        timer.tasks.clear();
        timer = null;
//        testTask = null;
//        System.gc();
    }

    @Test
//hates me
    void schedule_forCancelledTimer() {
        timer.cancel();
        Executable actual = () -> timer.schedule(testTask, 10);
        assertThrows(IllegalStateException.class, actual);
    }

    @Test
    void schedule_forNullTask() {
        Executable actual = () -> timer.schedule(null, 1);
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertThrows(NullPointerException.class, actual);
    }

    @Test
    void schedule_forNegativeDelay() {
        Executable actual = () -> timer.schedule(testTask, -1);
        assertThrows(IllegalArgumentException.class, actual);
    }

    @Test
    void schedule_forNegativePeriod() {
        Executable actual = () -> timer.schedule(testTask, 1, -1);
        assertThrows(IllegalArgumentException.class, actual);
    }

    @Test
    void schedule_forStoppedTimer() {
        timer.stop();
        var editables = new Object() {
            int counter = 0;
        };
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                editables.counter++;
            }
        }, 5);
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, editables.counter);
        timer.restart();
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, editables.counter);
    }

    @Test
    void schedule_forOneTimeAction() {
        var editables = new Object() {
            int counter = 0;
        };
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                editables.counter++;
            }
        };
        timer.schedule(task, 5);
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, editables.counter);
    }

    @RepeatedTest(5000)
//153/500=30-31% passes if checked each exec
//346/500=~70% passes if checked at the end
//388/500>75% passes w/o cancel
//    @Test
    synchronized void schedule_forRepeatedAction() {
        var editable = new Object() {
            int counter = 0;
        };
        TimerTask task = new TimerTask() {
            @Override
            public synchronized void run() {//syncing lowers the amount of time this passes
                editable.counter++;
            }
        };
        synchronized (editable) {
            timer.schedule(task, 5, 5);
        }
        try {
            Thread.sleep(27);//smh it does 0-45ms in 27ms wtf
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(5, editable.counter);
    }

    @Test
    void pause_forCancelledTimer() {
//        TimerUtil timer = new TimerUtil();
        timer.cancel();
        Executable actual = timer::stop;
        assertThrows(IllegalStateException.class, actual);
    }

    @Test
    void resume_forCancelledTimer() {
//        TimerUtil timer = new TimerUtil();
        timer.cancel();
        Executable actual = timer::restart;
        assertThrows(IllegalStateException.class, actual);
    }
}
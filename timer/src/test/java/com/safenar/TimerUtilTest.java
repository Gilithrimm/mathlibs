package com.safenar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

public class TimerUtilTest {
    TimerUtil timer;
    TimerTask hi;
    TimerTask counter;
    static class Editable{
        int counter=0;
    }
    final Editable ref= new Editable();

    @BeforeEach
    public void setUp() {
        timer = new TimerUtil();
        counter= new TimerTask() {
            @Override
            public synchronized void run() {
                ref.counter++;
            }
        };
        hi = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hi");
            }
        };
        timer.restart();
    }

    @AfterEach
    public void tearDown() {
        timer.cancel();//commenting this out=+100 fails
        hi.cancel();
        counter.cancel();
        timer = null;
        ref.counter=0;
    }

    @Test
    public void schedule_forCancelledTimer() {
        timer.cancel();
        Executable actual = () -> timer.schedule(hi, 10);
        assertThrows(IllegalStateException.class, actual);
    }
    @Test
    public void schedule_forNullTask() {
        Executable actual = () -> timer.schedule(null, 1);
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertThrows(NullPointerException.class, actual);
    }
    @Test
    public void schedule_forNegativeDelay() {
        Executable actual = () -> timer.schedule(hi, -1);
        assertThrows(IllegalArgumentException.class, actual);
    }
    @Test
    public void schedule_forNegativePeriod() {
        Executable actual = () -> timer.schedule(hi, 1, -1);
        assertThrows(IllegalArgumentException.class, actual);
    }
    @Test
    public void schedule_forStoppedTimer() {
        timer.stop();
        timer.schedule(counter, 5);
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, ref.counter);
    }
    @Test
    public void schedule_forResumedTimer() {
        schedule_forStoppedTimer();
        timer.restart();
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, ref.counter);
    }
    @Test
    public void schedule_forOneTimeAction() {
        timer.schedule(counter, 5);
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, ref.counter);
    }
//    @RepeatedTest(5000)
    @Test//TODO >90% passes
    public synchronized void schedule_forRepeatedAction() {
        synchronized (ref) {
            timer.schedule(counter, 5, 5);
        }
        try {
            Thread.sleep(27);//smh it does 0-45ms in 27ms wtf
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(5, ref.counter);
    }

    @Test
    public void pause_forCancelledTimer() {
        timer.cancel();
        Executable actual = timer::stop;
        assertThrows(IllegalStateException.class, actual);
    }
    @Test
    public void pause_forStoppedTimer() {
        timer.stop();
        Executable actual=timer::stop;
        assertDoesNotThrow(actual);
    }
    @Test
    public void pause_forTimerWithTasks() {
        timer.schedule(counter,5);
        timer.schedule(counter,5,5);
        timer.stop();
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0,ref.counter);
    }

    @Test
    public void restart_forCancelledTimer() {
        timer.cancel();
        Executable actual = timer::restart;
        assertThrows(IllegalStateException.class, actual);
    }
    @Test
    public void restart_forWorkingTimer() {
        Executable actual=timer::restart;
        assertDoesNotThrow(actual);
    }
    @Test
    public void restart_forTimerWithTasks() {
        class CounterTask extends TimerTask{
            final Editable ref=new Editable();
            @Override
            public void run() {
                ref.counter++;
            }
        }
        var task1=new CounterTask();
        var task2=new CounterTask();
        timer.schedule(task1,5);
        timer.schedule(task2,5,5);
        timer.stop();
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0,task1.ref.counter);
        assertEquals(0,task2.ref.counter);
        timer.restart();
        try {
            Thread.sleep(12);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1,task1.ref.counter);
        assertEquals(2,task2.ref.counter);
    }

    @Test
    public void cancel_forCancelledTimer() {
        timer.cancel();
        Executable actual=timer::cancel;
        assertDoesNotThrow(actual);
    }
    @Test
    public void cancel_forTimerWithoutTasks() {
        timer.cancel();
        assertNull(timer.tasks);
        assertEquals(TimerStates.TO_DESTROY,timer.getState());
    }
    @Test
    public void cancel_forTimerWithTasks() {
        timer.schedule(counter,5,5);
        cancel_forTimerWithoutTasks();
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0,ref.counter);
    }
}
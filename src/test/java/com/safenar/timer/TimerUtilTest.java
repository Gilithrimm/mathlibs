package com.safenar.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;

class TimerUtilTest {
		TimerUtil timer;

		@BeforeEach
		void setUp() {
				timer = new TimerUtil();
		}

		@Test
		void schedule_forCancelledTimer() {
				timer.cancel();
				Executable actual = () -> timer.schedule(new TimerTask() {
						@Override
						public void run() {
								System.out.println("Hi");
						}
				}, 10);
				assertThrows(IllegalStateException.class, actual);
		}

		@Test
		void schedule_forNullTask() {
				Executable actual = () -> timer.schedule(null, 0);

		}

		@Test
		void schedule_forNegativeDelay() {
				TimerTask executed = new TimerTask() {
						@Override
						public void run() {
								System.out.println("Hi");
						}
				};
		}

		@Test
		void schedule_forNegativePeriod() {

		}

		@Test
		void schedule_forStoppedTimer() {

		}

		@Test
		void schedule_forOneTimeAction() {

		}

		@Test
		void schedule_forRepeatedAction() {

		}

		//		@Test
		@RepeatedTest(5000)
//This test is not deterministic
		//most crashes happen when I click for some odd reason wtf
		void schedule_forProducerConsumerScenario() {
				var ref = new Object() {
						int counter = 0;
						boolean hasRead = false;
				};
				TimerTask producer = new TimerTask() {
						@Override
						public void run() {
								System.out.println("Produced!");
								ref.counter++;
								ref.hasRead = false;
						}
				};
				TimerTask consumer = new TimerTask() {
						@Override
						public void run() {
								System.out.println("Consumed!");
								ref.hasRead = true;
								System.out.println("Counter is at " + ref.counter);
						}
				};
				timer.schedule(producer, 50, 50);
				timer.schedule(consumer, 55, 50);
				try {
						Thread.sleep(265);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}
				timer.pause();
				timer.cancel();
				assertEquals(5, ref.counter);
				assertTrue(ref.hasRead);
				//1st time testing: 42/5000 fails
				//2nd time testing: 156/5000 fails
				//3rd time testing: 44/1000 fails
				//4th time: 129/5000 fails
				//5th time: 103/5000
		}

		@Test
		void pause_forCancelledTimer() {
				TimerUtil timer = new TimerUtil();
				timer.cancel();
				Executable actual = timer::pause;
				assertThrows(IllegalStateException.class, actual);
		}

		@Test
		void resume_forCancelledTimer() {
				TimerUtil timer = new TimerUtil();
				timer.cancel();
				Executable actual = timer::resume;
				assertThrows(IllegalStateException.class, actual);
		}
}
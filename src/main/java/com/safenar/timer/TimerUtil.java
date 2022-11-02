package com.safenar.timer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {
		//TODO tests
		Timer timer;
		ArrayList<Task> tasks;
		int size;
		TimerState state;
		
		public TimerUtil() {
				timer = new Timer();
				state = TimerState.RUNNING;
				tasks = new ArrayList<>();
				size = 0;
		}
		
		public boolean isRunning() {
				return state == TimerState.RUNNING;
		}
		public boolean isStopped() {
				return state == TimerState.STOPPED;
		}
		
		public void schedule(TimerTask task, long delay, long period) {
				ifCancelled();
				add(task, delay, period);
				timer.schedule(tasks.get(size).task, delay, period);
				setClock(tasks.get(size));
				System.out.println("task = " + tasks.get(size));
				size++;
		}
		public void schedule(TimerTask task, long delay) {
				schedule(task, delay, 0);
		}
		private void ifCancelled() {
				if (state == TimerState.TO_DESTROY)
						throw new IllegalStateException("Timer already cancelled!");
		}
		private void add(TimerTask task, long delay, long period) {
				tasks.add(period == 0
								? new Task(task, delay, "" + size)
								: new Task(task, delay, period, "" + size));
		}
		private void setClock(Task task) {
				timer.schedule(new TimerTask() {
						@Override
						public void run() {
								task.timeLeft--;
						}
				}, 0, 1);
		}
		
		public void pause() {
				ifCancelled();
				if (isStopped()) {
						System.out.println("Timer already stopped!");
						return;
				}
				timer.cancel();
				timer.purge();
				state = TimerState.STOPPED;
				System.out.println("Stopped!");
		}
		
		public void cancel() {
				timer = null;
				state = TimerState.TO_DESTROY;
				tasks = null;
				size -= size;
		}
		
		public void resume() {
				ifCancelled();
				if (isRunning()) return;//or throw an Ex of some kind?
				timer = new Timer();
				for (Task task : tasks) {
						if (task.period != 0)
								timer.schedule(new TimerTask() {
										@Override
										public void run() {
												task.task.run();
										}
								}, task.timeLeft, task.period);
						else /*if (task.task) */timer.schedule(new TimerTask() {
								@Override
								public void run() {
										task.task.run();
								}
						}, task.timeLeft);
						setClock(task);
				}
				state = TimerState.RUNNING;
		}
}

package p2p.client.downloader;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import p2p.client.task.Task;

public abstract class Downloader implements Runnable {
	protected int bufferLength = 4096;
	protected Semaphore available;
	protected CountDownLatch counter;
	protected Task task;

	public Downloader(Task task, Semaphore available) {
		this.task = task;
		this.available = available;
	}

	public CountDownLatch getCounter() {
		return counter;
	}

	public void setCounter(CountDownLatch counter) {
		this.counter = counter;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}

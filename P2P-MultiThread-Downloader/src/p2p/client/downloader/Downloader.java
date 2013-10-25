package p2p.client.downloader;

import p2p.client.task.Task;

public abstract class Downloader implements Runnable {
	protected int bufferLength = 4096;
	protected Task task;

	public Downloader(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}

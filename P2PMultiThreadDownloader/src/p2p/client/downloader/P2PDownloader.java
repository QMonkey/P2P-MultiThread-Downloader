package p2p.client.downloader;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.task.Task;
import p2p.client.task.TaskFragment;

public class P2PDownloader extends Downloader {

	public P2PDownloader(Task task, Semaphore avaliable) {
		super(task,avaliable);
	}

	@Override
	public void run() {
		TaskFragment subTask = null;
		try {
			available.acquire();
		} catch (InterruptedException e) {
			Logger.getLogger(MultiThreadDownloader.class.getName() + "_ThreadInterrupted").log(Level.ALL,null,e);
		}
		subTask = task.getFirstFreeSubTask();
		if(subTask == null) {
			available.release();
			return;
		}
		subTask.setOccupied(true);
		available.release();
		this.counter.countDown();
	}
}

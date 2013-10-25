package p2p.client.downloader;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.task.Task;
import p2p.client.task.TaskFragment;

public class P2PDownloader extends Downloader {

	public P2PDownloader(Task task) {
		super(task);
	}

	@Override
	public void run() {
		Semaphore available = task.getAvailable();
		try {
			available.acquire();
		} catch (InterruptedException e) {
			Logger.getLogger(P2PDownloader.class.getName() + "_ThreadInterrupted").log(Level.ALL,null,e);
		}
		TaskFragment subTask = this.task.getFirstFreeSubTask();
		if(subTask == null) {
			available.release();
			return;
		}
		subTask.setOccupied(true);
		if(subTask.isFinished()) {
			available.release();
			return;
		}
		available.release();
	}
}

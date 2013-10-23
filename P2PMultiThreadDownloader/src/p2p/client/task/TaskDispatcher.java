package p2p.client.task;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.downloader.Downloader;
import p2p.client.downloader.MultiThreadDownloader;
import p2p.client.downloader.P2PDownloader;

public class TaskDispatcher implements Runnable {
	private ThreadPoolExecutor executor;
	private LinkedList<Downloader> downloadList;
	
	public TaskDispatcher(int coreThreadPoolSize, int maxThreadPoolSize) {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		executor = new ThreadPoolExecutor(coreThreadPoolSize, maxThreadPoolSize, 1, TimeUnit.SECONDS, queue);
		downloadList = new LinkedList<Downloader>();
	}
	
	public void dispatch(Task task, Semaphore available) {
		int pos = (int) (Math.random() * task.getSubTasksCount()) + 1;
		for(int i = task.getSubTasksCount(); i > 0; --i) {
			if(--pos != 0) {
				downloadList.add(new MultiThreadDownloader(task,available));
			} else {
				downloadList.add(new P2PDownloader(task,available));
			}
		}
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			CountDownLatch counter = new CountDownLatch(10);
			for(Downloader downloader : downloadList) {
				downloader.setCounter(counter);
				executor.execute(downloader);
			}
			try {
				counter.await();
			} catch (InterruptedException e) {
				Logger.getLogger(TaskDispatcher.class.getName()).log(Level.ALL, null, e);
			}
			for(Downloader downloader : downloadList) {
				
			}
		}
	}
}

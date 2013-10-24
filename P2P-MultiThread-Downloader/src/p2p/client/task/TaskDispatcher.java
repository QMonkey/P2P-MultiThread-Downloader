package p2p.client.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import p2p.client.downloader.MultiThreadDownloader;

public class TaskDispatcher extends ThreadPoolExecutor {
	private boolean finished;

	public TaskDispatcher(int coreThreadPoolSize, int maxThreadPoolSize, BlockingQueue<Runnable> queue) {
		super(coreThreadPoolSize, maxThreadPoolSize, 1, TimeUnit.SECONDS, queue);
		finished = false;
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		synchronized(this) {
			if(this.getActiveCount() == 1) {
				this.finished = true;
				this.notify();
			}
		}
	}

	public void isEndTask() {
		synchronized(this) {
			while(!this.finished) {
				try {
					this.wait();
				} catch(Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	public void dispatch(Task task) {
		while(true) {
			finished = false;
			int pos = (int) (Math.random() * task.getSubTasksCount()) + 1;
			for(int i = task.getSubTasksCount(); i > 0; --i) {
				if(--pos != 0) {
					this.execute(new MultiThreadDownloader(task));
				} else {
					this.execute(new MultiThreadDownloader(task));
				}
			}
			isEndTask();
			if(task.getProgress() == 100) {
				break;
			}
		}
	}
}

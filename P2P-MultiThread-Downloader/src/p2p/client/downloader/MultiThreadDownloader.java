package p2p.client.downloader;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.task.Task;
import p2p.client.task.TaskFragment;

public class MultiThreadDownloader extends Downloader {

	public MultiThreadDownloader(Task task) {
		super(task);
	}

	@Override
	public void run() {
		Semaphore available = task.getAvailable();
		try {
			available.acquire();
		} catch (InterruptedException e) {
			Logger.getLogger(MultiThreadDownloader.class.getName() + "_ThreadInterrupted").log(Level.ALL,null,e);
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
		
		System.out.println(Thread.currentThread().getId() + "\t" + subTask.getTaskBegin() + "\t" + subTask.getTaskEnd());
		
		try {
			HttpURLConnection huc = (HttpURLConnection)this.task.getUrl().openConnection();
			huc.setRequestMethod("GET");
			huc.setRequestProperty("Range","bytes=" + subTask.getTaskBegin() + "-" + subTask.getTaskEnd());

			InputStream input = huc.getInputStream();
			RandomAccessFile output = new RandomAccessFile(this.task.getTaskName(),"rw");
			int rd = 0;
			byte[] buffer = new byte[bufferLength];
			output.seek(subTask.getTaskBegin());
			
			while((rd = input.read(buffer)) > 0) {
				output.write(buffer,0,rd);
				subTask.promote(rd);
			}
			
			input.close();
			output.close();
		} catch(Exception e) {
			Logger.getLogger(MultiThreadDownloader.class.getName()).log(Level.ALL,null,e);
		}
		subTask.setOccupied(false);
	}
}

package p2p.client.task;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManager implements Runnable {
	private Task task;
	private TaskDispatcher dispatcher;
	
	public TaskManager(int coreThreadPoolSize, int maxThreadPoolSize) {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		dispatcher = new TaskDispatcher(coreThreadPoolSize, maxThreadPoolSize,queue);
	}
	
	public void addTask(String urlString, int subTasksCount) {
		URL url = null;
		try {
			url = new URL(urlString);
			String[] strList = URLDecoder.decode(url.getFile(),"UTF-8").split("/");
	        String fileName = strList[strList.length-1];
	        task = new Task();
	        task.setUrl(url);
	        task.setTaskName(fileName);
	        task.setSubTasksCount(subTasksCount);
	        new Thread(this).start();
		} catch (Exception e) {
			Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void run() {
		HttpURLConnection huc = null;
		try {
			huc = (HttpURLConnection)task.getUrl().openConnection();
			huc.setRequestMethod("HEAD");
			long fileSize = huc.getContentLengthLong();
			task.setTaskLength(fileSize);
			task.initSubTasks(0, fileSize);
			System.out.println(task.getSubTasksCount() + "\t" + task.getTaskLength() + "\t" + task.getTaskName());
			dispatcher.dispatch(task);
		} catch (Exception e) {
			Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, e);
		}   
	}
}

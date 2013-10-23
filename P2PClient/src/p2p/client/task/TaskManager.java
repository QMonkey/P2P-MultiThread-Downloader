package p2p.client.task;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManager implements Runnable {
	private Task task;
	private TaskDispatcher dispatcher;
	private Semaphore available;
	
	
	public TaskManager(int coreThreadPoolSize, int maxThreadPoolSize) {
		dispatcher = new TaskDispatcher(coreThreadPoolSize, maxThreadPoolSize);
		available = new Semaphore(1);
	}
	
	public void addTask(String urlString, int subTasksCount) {
		URL url;
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
			Logger.getLogger(TaskManager.class.getName()).log(Level.ALL, null, e);
		}
	}

	@Override
	public void run() {
		HttpURLConnection huc;
		try {
			huc = (HttpURLConnection)task.getUrl().openConnection();
			huc.setRequestMethod("HEAD");
			long fileSize = huc.getContentLengthLong();
			task.setTaskLength(fileSize);
			task.initSubTasks(0, fileSize);
		} catch (Exception e) {
			Logger.getLogger(TaskManager.class.getName()).log(Level.ALL, null, e);
		}   
	}
	
	public void dispatch() {
		dispatcher.dispatch(task, available);
	}
}

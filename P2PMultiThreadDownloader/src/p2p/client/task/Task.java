package p2p.client.task;

import java.net.URL;

public class Task {
	private long taskLength;
    private String taskName;
    private URL url;
    private int subTasksCount;

	private TaskFragment[] subTasks;
    
    public Task() {
    }
	
	public Task(long taskLength, String taskName, URL url, int subTasksCount) {
		this.taskLength = taskLength;
		this.taskName = taskName;
		this.url = url;
		this.subTasksCount = subTasksCount;
		this.subTasks = new TaskFragment[subTasksCount];
		initSubTasks(0,taskLength);
	}

	public void initSubTasks(long taskBegin,long taskEnd) {
		for(int i = subTasksCount - 1; i >= 0; --i) {
			this.subTasks[i] = new TaskFragment();
		}
		long difference = (taskEnd - taskBegin) / subTasksCount;
		int last = subTasksCount - 1;
		
		subTasks[last].setTaskBegin(last * difference);
		subTasks[last].setTaskEnd(taskEnd);
		subTasks[last].setOccupied(false);
		for(int i = subTasksCount-2; i >= 0; --i) {
			long subTasksBegin = i * difference;
			subTasks[i].setTaskBegin(subTasksBegin);
			subTasks[i].setTaskEnd(subTasksBegin + difference);
			subTasks[i].setOccupied(false);
		}
	}

	public long getTaskLength() {
		return taskLength;
	}
	
	public void setTaskLength(long taskLength) {
		this.taskLength = taskLength;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	
	public int getSubTasksCount() {
		return subTasksCount;
	}

	public void setSubTasksCount(int subTasksCount) {
		this.subTasksCount = subTasksCount;
	}
	
	public TaskFragment getFirstFreeSubTask() {
		TaskFragment subTask = null;
		for(int i = 0; i < subTasksCount; ++i) {
			if(!subTasks[i].isOccupied()) {
				subTask = subTasks[i];
				break;
			}
		}
		return subTask;
	}
	
	public int countFinishedSubTasks() {
		int number = 0;
		for(TaskFragment subTask : subTasks) {
			if(subTask.isFinished()) {
				++number;
			}
		}
		return number;
	}
	
	public int getProgress() {
		long remaining = 0;
		for(TaskFragment subTask : subTasks) {
			remaining += subTask.getRemaining();
		}
		return (int) ((taskLength - remaining) * 100 / taskLength);
	}
}

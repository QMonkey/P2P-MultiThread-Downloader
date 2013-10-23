package p2p.client.task;

public class TaskFragment {
    private long begin;
    private long end;
    private boolean occupied;

    public TaskFragment() {
    }
    
    public TaskFragment(long begin, long end) {
		this.begin = begin;
		this.end = end;
		this.occupied = false;
	}
    
    public void promote(long offset) {
    	begin += offset;
    }
    
    public long getRemaining() {
    	return end - begin;
    }

	public long getTaskBegin() {
		return begin;
	}

	public void setTaskBegin(long begin) {
		this.begin = begin;
	}

	public long getTaskEnd() {
		return end;
	}

	public void setTaskEnd(long end) {
		this.end = end;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isOccupied() {
    	return this.occupied;
    }
	
	public boolean isFinished() {
		return begin == end;
	}
}

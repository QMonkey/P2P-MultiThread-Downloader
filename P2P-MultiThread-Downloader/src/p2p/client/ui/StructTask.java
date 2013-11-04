package p2p.client.ui;

/**
 * @author JING
 * 
 * 单任务结构(自改)
 * 考虑以后支持多任务功能设置该结构
 */
public class StructTask {
	//	速率
	private float rate;
	//	数据量
	private float size;
	//	主机
	private String host;
	//	端口
	private String port;
	//	链接
	private String link;
	//	文件名
	private String filename;
	//	流逝时间
	private int elapseTime;
	//	剩余时间
	private int remainTime;
	//	运行时标志
	private boolean isRunning;
	//	已下载数据量
	private float downloaded;
	//	对应的UI组件
	private ComponentTask component;

	public StructTask(String host, String port, String link, String filename, float size){
		this.host = host;
		this.port = port;
		this.link = link;
		this.size = size;
		this.filename = filename;
		rate = 0.0f;
		elapseTime = 0;
		remainTime = 0;
		isRunning = true;
	}

	public void setComponent(ComponentTask component){
		this.component = component;
	}

	//	数据即时更新
	public void update(float rate, float downloaded, int elapseTime, int remainTime){
		this.rate = rate;
		this.downloaded = downloaded;
		this.elapseTime = elapseTime;
		this.remainTime = remainTime;
		//component.update(this);
	}

	public float getRate(){
		return rate;
	}

	public int getElapseTime(){
		return elapseTime;
	}

	public int getRemainTime(){
		return remainTime;
	}

	public void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}

	public boolean isRunning(){
		return isRunning;
	}

	public String getHost(){
		return host;
	}

	public String getPort(){
		return port;
	}

	public float getSize(){
		return size;
	}

	public String getFilename(){
		return filename;
	}

	public float getDownloaded(){
		return downloaded;
	}

	public float getPercentage(){
		if(size <= 0.0f)
			return 0.0f;
		//		 这里最好保留一位小数
		return downloaded / size;
	}
}

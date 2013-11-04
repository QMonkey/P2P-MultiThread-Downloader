package p2p.client.ui;

import javax.swing.*;
import java.util.*;

public class SingletonFactory {
	private static FrameCreate frameCreate = null;
	private static FrameMain frameMain = null;
	private static FrameConfig frameConfig = null;
	
//	多任务结构队列
	private static Vector<StructTask> queueStructTask = null;
	
	public static JFrame generateFrame(Constant.ID id) {
		switch(id) {
			case MAIN:
				if(frameMain == null) {
					frameMain = new FrameMain();
				}
				return frameMain;
			case CREATE:
				if(frameCreate == null && frameMain != null) {
					frameCreate = new FrameCreate(frameMain);
				}
				return frameCreate;
			case CONFIG:
				if(frameConfig == null  && frameMain != null) {
					frameConfig = new FrameConfig(frameMain);
				}
				return frameConfig;
			default:
				return null;
		}
	}
	
	public static Vector<StructTask> generateQueueStructTask() {
		if(queueStructTask == null)
			queueStructTask = new Vector<StructTask>();
		return queueStructTask;
	}
}

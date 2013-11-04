package p2p.client.ui;

import javax.swing.*;

public class SingletonFactory {
	private static FrameCreate frameCreate = null;
	private static FrameMain frameMain = null;
	private static FrameConfig frameConfig = null;
	
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
}

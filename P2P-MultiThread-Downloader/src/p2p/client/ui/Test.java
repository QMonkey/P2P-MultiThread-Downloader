package p2p.client.ui;

import javax.swing.*;

import p2p.utils.Configurator;

/**
 * @author JING
 *
 *	测试
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configurator.init();
		try {
//			风格自改
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		扩展UI组件请在单例工厂构造
		SingletonFactory.generateFrame(Constant.ID.MAIN);
	}

}

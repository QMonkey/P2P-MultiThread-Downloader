package p2p.client.ui;
import java.awt.*;

/**
 * @author JING
 *	
 * 常量
 */
public final class Constant {
//	文本
	public static String titleMain = "P2P下载工具";
	public static String titleCreate = "新建下载任务";
	public static String tipCreate = "新建";
	public static String tipContinue = "继续";
	public static String tipPause = "暂停";
	public static String tipDelete = "删除";
	public static String tipConfig = "设置";
	public static String labelCreate = "下载链接：";
	public static String textDownload = "立即下载";
	public static String textClear = "清空";
	public static String labelHost = "主机：";
	public static String labelPort = "端口：";
	public static String labelServerHost = "服务器：";
	public static String labelServerPort = "服务器端口：";
	public static String labelSubTaskSize = "任务分段：";
	public static String labelCoreThreadPoolSize = "并发线程：";
	public static String labelMaxThreadPoolSize = "最大任务分段：";
	public static String titleConfig = "配置";
	public static String textModify = "修改";
//	样式
	public static Color colorTextArea = Color.gray;
	public static int thickTextArea = 1;
	public static Color colorDefaultTaskBorder = Color.lightGray;
	public static Color colorActiveTaskBorder = Color.blue;
	public static Color colorSelectedTaskBorder = Color.red;
	public static Color colorStopTask = Color.lightGray;
	public static Color colorRunningTask = Color.black;
//	位置与尺寸
	public static int widthMain= 320;
	public static int heightMain = 240;
	public static int widthCreate = 250;
	public static int heightCreate = 200;
	
	public static int heightTool = 48;
	
	public static int posxLabelTip = 10;
	public static int posyLabelTip = 10;
	public static int widthLabelTip = 100;
	public static int heightLabelTip = 20;
	
	public static int posxTextAreaLink = 10;
	public static int posyTextAreaLink = 40;
	public static int widthTextAreaLink = 230;
	public static int heightTextAreaLink = 120;
	
	public static int posxButtonDownload = 150;
	public static int posyButtonDownload  = 170;
	public static int widthButtonDownload  = 90;
	public static int heightButtonDownload  = 25;
	
	public static int posxButtonClear = 50;
	public static int posyButtonClear   = 170;
	public static int widthButtonClear   = 90;
	public static int heightButtonClear   = 25;
	
	public static int posxTaskTitle = 10;
	public static int posyTaskTitle = 15;
	
	public static int posxProgressBarBorder = 10;
	public static int posyProgressBarBorder = 25;
	public static int widthProgressBarBorder = 150;
	public static int heightProgressBarBorder = 15;
	
	public static int posxProgressBar = 10;
	public static int posyProgressBar = 25;
	public static int widthProgressBar= 150;
	public static int heightProgressBar= 15;
	
	public static int posxPercentage = 125;
	public static int posyPercentage = 37;
	
	public static int posxRate = 190;
	public static int posyRate = 37;
	
	public static int posxSize = 10;
	public static int posySize = 60;
	
	public static int posxTime = 138;
	public static int posyTime = 60;
	
	public static int heightComponentTask = 70;
	
	public static int spacingComponent = 3;
	
	public static int posxLabelHost = 10;
	public static int posyLabelHost = 10;
	public static int widthLabelHost = 100;
	public static int heightLabelHost = 20;
	
	public static int posxLabelPort = 10;
	public static int posyLabelPort = 70;
	public static int widthLabelPort = 100;
	public static int heightLabelPort = 20;
	
	public static int posxLabelServerHost = 10;
	public static int posyLabelServerHost = 130;
	public static int widthLabelServerHost = 100;
	public static int heightLabelServerHost = 20;
	
	public static int posxLabelServerPort = 10;
	public static int posyLabelServerPort = 190;
	public static int widthLabelServerPort = 100;
	public static int heightLabelServerPort = 20;
	
	public static int posxLabelSubTaskSize = 10;
	public static int posyLabelSubTaskSize = 250;
	public static int widthLabelSubTaskSize = 100;
	public static int heightLabelSubTaskSize = 20;
	
	public static int posxLabelCoreThreadPoolSize = 10;
	public static int posyLabelCoreThreadPoolSize = 310;
	public static int widthLabelCoreThreadPoolSize = 100;
	public static int heightLabelCoreThreadPoolSize = 20;
	
	public static int posxLabelMaxThreadPoolSize = 10;
	public static int posyLabelMaxThreadPoolSize = 370;
	public static int widthLabelMaxThreadPoolSize = 100;
	public static int heightLabelMaxThreadPoolSize = 20;
	
	public static int posxTextFieldHost = 10;
	public static int posyTextFieldHost = 40;
	public static int widthTextFieldHost = 180;
	public static int heightTextFieldHost = 20;
	
	public static int posxTextFieldPort = 10;
	public static int posyTextFieldPort = 100;
	public static int widthTextFieldPort = 180;
	public static int heightTextFieldPort = 20;
	
	public static int posxTextFieldServerHost = 10;
	public static int posyTextFieldServerHost = 160;
	public static int widthTextFieldServerHost = 180;
	public static int heightTextFieldServerHost = 20;
	
	public static int posxTextFieldServerPort = 10;
	public static int posyTextFieldServerPort = 220;
	public static int widthTextFieldServerPort = 180;
	public static int heightTextFieldServerPort = 20;
	
	public static int posxTextFieldSubTaskSize = 10;
	public static int posyTextFieldSubTaskSize = 280;
	public static int widthTextFieldSubTaskSize = 180;
	public static int heightTextFieldSubTaskSize = 20;
	
	public static int posxTextFieldCoreThreadPoolSize = 10;
	public static int posyTextFieldCoreThreadPoolSize = 340;
	public static int widthTextFieldCoreThreadPoolSize = 180;
	public static int heightTextFieldCoreThreadPoolSize = 20;
	
	public static int posxTextFieldMaxThreadPoolSize = 10;
	public static int posyTextFieldMaxThreadPoolSize = 400;
	public static int widthTextFieldMaxThreadPoolSize = 180;
	public static int heightTextFieldMaxThreadPoolSize = 20;
	
	public static int posxButtonModify = 120;
	public static int posyButtonModify = 450;
	public static int widthButtonModify = 70;
	public static int heightButtonModify = 30;
	
	public static int widthFrameConfig = 200;
	public static int heightFrameConfig = 500;
//	组件ID
	public static enum ID {
		MAIN,
		CREATE,
		CONFIG,
		QUEUE,
	}
//	杂项
	public static int componentDelta = 2;
	public static int componentSize = 5;
}

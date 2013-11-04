package p2p.client.ui;
import javax.swing.*;

import p2p.client.task.Task;
import p2p.client.task.TaskManager;
import p2p.client.ui.environment.UIEnvironment;
import p2p.utils.Configurator;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.Timer;
/**
 * @author JING
 * 
 * 主面板
 */
public class FrameMain extends JFrame implements ActionListener{
	private JToolBar toolBar;
	private JButton buttonCreate;
	private JButton buttonContinue;
	private JButton buttonPause;
	private JButton buttonDelete;
	private JButton buttonConfig;

	private JPanel panelMain;
	private JScrollPane scrollPane;
	private JPanel panelTaskContainer;

	private Vector<ComponentTask> taskComponents;

	private int size = Constant.componentSize;
	
	private TaskManager taskManager;

	public FrameMain(){
		initComponent();
	}
	

	public TaskManager getTaskManager() {
		return taskManager;
	}


	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}


	private void initComponent(){
		setTitle(Constant.titleMain);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);

		buttonCreate = new JButton();
		buttonCreate.setIcon(new ImageIcon(getClass().getResource(UIEnvironment.imgPathButtonCreate)));
		buttonCreate.setToolTipText(Constant.tipCreate);
		buttonCreate.addActionListener(this);
		toolBar.add(buttonCreate);

		buttonContinue = new JButton();
		buttonContinue.setIcon(new ImageIcon(getClass().getResource(UIEnvironment.imgPathButtonContinue)));
		buttonContinue.setToolTipText(Constant.tipContinue);
		buttonContinue.addActionListener(this);
		toolBar.add(buttonContinue);

		buttonPause = new JButton();
		buttonPause.setIcon(new ImageIcon(getClass().getResource(UIEnvironment.imgPathButtonPause)));
		buttonPause.setToolTipText(Constant.tipPause);
		buttonPause.addActionListener(this);
		toolBar.add(buttonPause);

		buttonDelete = new JButton();
		buttonDelete.setIcon(new ImageIcon(getClass().getResource(UIEnvironment.imgPathButtonDelete)));
		buttonDelete.setToolTipText(Constant.tipDelete);
		buttonDelete.addActionListener(this);
		toolBar.add(buttonDelete);

		buttonConfig = new JButton();
		buttonConfig.setIcon(new ImageIcon(getClass().getResource(UIEnvironment.imgPathButtonConfig)));
		buttonConfig.setToolTipText(Constant.tipConfig);
		buttonConfig.addActionListener(this);
		toolBar.add(buttonConfig);

		panelTaskContainer = new JPanel();
		panelTaskContainer.setLayout(new GridLayout(size, 1, 0, Constant.spacingComponent));
		scrollPane = new JScrollPane(panelTaskContainer);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, Constant.heightTool, Constant.widthMain, Constant.heightMain - Constant.heightTool);

		panelMain = new JPanel();
		panelMain.setLayout(null);
		panelMain.setPreferredSize(new Dimension(Constant.widthMain, Constant.heightMain));
		toolBar.setBounds(0, 0, Constant.widthMain, Constant.heightTool);

		panelMain.add(toolBar);
		panelMain.add(scrollPane);

		add(panelMain);
		pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth())/ 2, (screenSize.height - getHeight()) / 2);

		taskComponents = new Vector<ComponentTask>();
	}

	public void addTask(){
		if(taskComponents.size() >= size){
			size += Constant.componentDelta;
			panelTaskContainer.removeAll();
			panelTaskContainer.validate();
			panelTaskContainer.setLayout(new GridLayout(size, 1, 0, Constant.spacingComponent));
			for(int i = 0; i < taskComponents.size(); i++)
				panelTaskContainer.add(taskComponents.get(i));
			ComponentTask componentTask = new ComponentTask(SingletonFactory.generateQueueStructTask().lastElement());
			taskComponents.add(componentTask);
			validate();
			return;
		}
		ComponentTask componentTask = new ComponentTask(SingletonFactory.generateQueueStructTask().lastElement());
		panelTaskContainer.add(componentTask);
		taskComponents.add(componentTask);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		//		工具按钮事件处理
		if(action == buttonCreate){
			FrameCreate frame = (FrameCreate)SingletonFactory.generateFrame(Constant.ID.CREATE);
			frame.relocate();
			frame.setVisible(true);
			new Timer().schedule(new ComponentTaskUpdater(taskManager.getTask(),taskComponents), 0, 500);
		}
		else if(action == buttonContinue){
			//			任务继续
			//			UI更新仿照删除操作写法
			//			TODO
		}
		else if(action == buttonPause){
			//			任务暂停
			//			UI更新仿照删除操作写法
			//			TODO
		}
		else if(action == buttonDelete){
			Iterator<ComponentTask> iter = taskComponents.iterator();
			int i = 0;
			while(iter.hasNext()){
				ComponentTask component = iter.next();
				if(component.isSelected()){
					//					从备份UI队列里删除一个
					taskComponents.remove(component);
					iter = taskComponents.iterator();
					//					从面板中删除一个
					panelTaskContainer.remove(component);
					panelTaskContainer.validate();
					//					从任务结构队列里删除一个
					SingletonFactory.generateQueueStructTask().remove(i++);
					//					从线程队列里删除一个
					//					TODO
					validate();
				}
			}
		}
		else if(action == buttonConfig){
			FrameConfig frame = (FrameConfig)SingletonFactory.generateFrame(Constant.ID.CONFIG);
			frame.relocate();
			frame.setVisible(true);
		}
	}

	public static void main(String[] args) {
		Configurator.init();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FrameMain frameMain = (FrameMain) SingletonFactory.generateFrame(Constant.ID.MAIN);
		TaskManager manager = new TaskManager(Integer.valueOf(Configurator.getCoreThreadPoolSize()),
				Integer.valueOf(Configurator.getMaxThreadPoolSize()));
		frameMain.setTaskManager(manager);
	}
}


final class ComponentTaskUpdater extends TimerTask {
	private Task task;
	private Vector<ComponentTask> taskComponents;
	
	public ComponentTaskUpdater(Task task,Vector<ComponentTask> taskComponents) {
		super();
		this.task = task;
		this.taskComponents = taskComponents;
	}

	@Override
	public void run() {
		StructTask structTask = SingletonFactory.generateQueueStructTask().firstElement();
		structTask.update(0, task.getProgress(), 0, 0);
		taskComponents.firstElement().update(structTask);
		this.cancel();
	}

}

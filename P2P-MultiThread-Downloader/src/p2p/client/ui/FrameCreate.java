package p2p.client.ui;
import javax.swing.*;

import p2p.client.task.Task;
import p2p.client.task.TaskManager;
import p2p.utils.Configurator;

import java.awt.*;
import java.awt.event.*;

/**
 * @author JING
 * 
 *	新建任务面板
 */
public class FrameCreate extends JFrame implements ActionListener{
	private JLabel labelTip;
	private JTextArea textAreaLink;
	private JButton buttonDownload;
	private JButton buttonClear;
	private JScrollPane scrollPane;

	private JPanel panelCreate;
	private JFrame frameParent;

	public FrameCreate(JFrame frameParent){
		this.frameParent = frameParent;
		setTitle(Constant.titleCreate);
		setResizable(false);
		initComponent();
	}

	private void initComponent(){

		labelTip = new JLabel(Constant.labelCreate);
		labelTip.setBounds(Constant.posxLabelTip, Constant.posyLabelTip, Constant.widthLabelTip, Constant.heightLabelTip);

		textAreaLink = new JTextArea();
		textAreaLink.setLineWrap(true);
		textAreaLink.setBorder(BorderFactory.createLineBorder(Constant.colorTextArea, Constant.thickTextArea));

		scrollPane = new JScrollPane(textAreaLink);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(Constant.posxTextAreaLink, Constant.posyTextAreaLink, Constant.widthTextAreaLink, Constant.heightTextAreaLink);

		buttonDownload = new JButton(Constant.textDownload);
		buttonDownload.setBounds(Constant.posxButtonDownload, Constant.posyButtonDownload, Constant.widthButtonDownload, Constant.heightButtonDownload);
		buttonDownload.addActionListener(this);

		buttonClear = new JButton(Constant.textClear);
		buttonClear.setBounds(Constant.posxButtonClear, Constant.posyButtonClear, Constant.widthButtonClear, Constant.heightButtonClear);
		buttonClear.addActionListener(this);

		panelCreate = new JPanel();
		panelCreate.setLayout(null);
		panelCreate.setPreferredSize(new Dimension(Constant.widthCreate, Constant.heightCreate));

		panelCreate.add(labelTip);
		panelCreate.add(scrollPane);
		panelCreate.add(buttonClear);
		panelCreate.add(buttonDownload);

		add(panelCreate);
		pack();
	}

	public void relocate(){
		Dimension parentSize = frameParent.getSize();
		Point parentPos = frameParent.getLocation();
		setLocation(parentPos.x + (parentSize.width - getWidth()) / 2, parentPos.y + (parentSize.height - getHeight()) / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object action = e.getSource();
		if(action == buttonDownload){
			//			文件大小
			float size = 0.0f;
			//			文件链接
			String link = textAreaLink.getText();
			//				主机
			String host = "";
			//				端口
			String port = "";
			//				文件名
			String filename = "";

			if(link.equals("")){
				JOptionPane.showMessageDialog(this, "请填写链接", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//			考虑以后会增加多任务下载的可能性
			//				尝试获取资源信息
			//				TODO
			//			填充任务结构
			TaskManager manager = ((FrameMain)SingletonFactory.generateFrame(Constant.ID.MAIN)).getTaskManager();
			manager.addTask(link, Integer.valueOf(Configurator.getSubTaskSize()));
			Task task = manager.getTask();
			filename = task.getTaskName();
			size = (float) (1.0 * task.getTaskLength() / 1024 / 1024);
			/*
			StructTask structTask = new StructTask(host, port, link, filename, size);
			//			加入容器，下载开始时不断更新元素的内容，用于重绘UI
			SingletonFactory.generateQueueStructTask().add(structTask);
			*/
			((FrameMain)(SingletonFactory.generateFrame(Constant.ID.MAIN))).addTask();
			setVisible(false);
		}
		else if(action == buttonClear){
			textAreaLink.setText("");
		}
	}
}

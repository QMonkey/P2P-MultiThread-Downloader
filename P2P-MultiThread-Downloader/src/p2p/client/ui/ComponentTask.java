package p2p.client.ui;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import p2p.client.task.Task;

public class ComponentTask extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorTaskBorder = Constant.colorDefaultTaskBorder;
	private Color colorTaskText = Constant.colorRunningTask;
	private Color colorProgressBar = Color.red;
	private boolean isSelected = false;
	private Task task = null;
	private long lastFinishedSize;
	private long currentFinishedSize;
	private int interval;
	private int counter;
	
	public ComponentTask() {
		this.setPreferredSize(new Dimension(getWidth(), Constant.heightComponentTask));
		setContentAreaFilled(false);  
		addMouseListener(this);
		lastFinishedSize = currentFinishedSize = counter = 0;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setTask(Task task,long currentFinishedSize,int interval) {
		this.task = task;
		if(++counter == 5) {
		//	System.out.println(this.lastFinishedSize + "\t" + this.currentFinishedSize);
			this.lastFinishedSize = this.currentFinishedSize;
			this.currentFinishedSize = currentFinishedSize;
			this.interval = interval;
			counter = 0;
		}
	}
	
	public void update(Task task) {
//		UI更新
		this.task = task;
		if(this.task != null) {
			repaint();
		}
	}
	
	private float route(float number) {
		return (float) Math.round(number * 10) / 10;
	}
	
	private String format(long timestamp) {
		return timestamp / (60 * 60 * 1000) % 24 + ":" + timestamp / (60 * 1000) % 60 + ":" + timestamp / 1000 % 60;
	}
	
	public void paintComponent(Graphics g) {
		if(task == null) {
			return;
		}
		float progress = (float) (task.getProgress() * 1.0 / 100);
		float taskLength = (float) (task.getTaskLength() * 1.0 / 1024 / 1024);
		taskLength = this.route(taskLength);
		float rate = (float) ((currentFinishedSize - lastFinishedSize)) / 1024;
		rate = this.route(rate);
		//System.out.println(currentFinishedSize + "\t" + lastFinishedSize);
		long remaining = System.currentTimeMillis();
		//long remaining = (100 - currentProgress) * task.getTaskLength() / 100 / rateLong * interval;
		String elapseTime = format(System.currentTimeMillis() - task.getStartTime());
		//String remainTime = new SimpleDateFormat("HH:mm:ss").format(new Date(remaining));
		
//		单任务边框
		g.setColor(colorTaskBorder);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		
//		文件名
		g.setColor(colorTaskText);
		g.drawString(task.getTaskName(), Constant.posxTaskTitle, Constant.posyTaskTitle);
		
//		进度条
		g.setColor(colorProgressBar);
		g.drawRect(Constant.posxProgressBarBorder, Constant.posyProgressBarBorder, Constant.widthProgressBarBorder, Constant.heightProgressBarBorder);
		g.fillRect(Constant.posxProgressBar, Constant.posyProgressBar, (int)(this.route(progress) * Constant.widthProgressBar), Constant.heightProgressBar);
		
//		进度百分比
		g.setColor(Color.blue);
		g.drawString(String.valueOf(task.getProgress()) + "%", Constant.posxPercentage, Constant.posyPercentage);
		
//		速率
		g.setColor(Color.green);
		g.drawString(rate + "k / s", Constant.posxRate, Constant.posyRate);
		
//		数据量
		g.setColor(Color.gray);
		g.drawString(this.route(progress) * taskLength + "MB / " + taskLength + "MB", Constant.posxSize, Constant.posySize);
		
//		时间
//		这个最好格式化成 hh:mm:ss
		g.drawString(elapseTime + " / " + elapseTime , Constant.posxTime, Constant.posyTime);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		isSelected = ! isSelected;
		if(isSelected)
			colorTaskBorder = Constant.colorSelectedTaskBorder;
		else
			colorTaskBorder = Constant.colorDefaultTaskBorder;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isSelected){
			colorTaskBorder = Constant.colorActiveTaskBorder;
			repaint();
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isSelected){
			colorTaskBorder = Constant.colorDefaultTaskBorder;
			repaint();
		}
	}
}

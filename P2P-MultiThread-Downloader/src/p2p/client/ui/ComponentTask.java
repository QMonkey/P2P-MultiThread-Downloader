package p2p.client.ui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ComponentTask extends JButton implements MouseListener{
	private Color colorTaskBorder = Constant.colorDefaultTaskBorder;
	private Color colorTaskText = Constant.colorRunningTask;
	private Color colorProgressBar = Color.red;
	private boolean isSelected = false;
	private StructTask structTask = null;
	
	public ComponentTask(StructTask structTask){
		this.setPreferredSize(new Dimension(getWidth(), Constant.heightComponentTask));
		setContentAreaFilled(false);  
		addMouseListener(this);
		this.structTask = structTask;
		structTask.setComponent(this);
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	public void update(StructTask structTask){
//		UI更新
		this.structTask = structTask;
		repaint();
	}
	public void paintComponent(Graphics g){
//		单任务边框
		g.setColor(colorTaskBorder);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		
//		文件名
		g.setColor(colorTaskText);
		g.drawString(structTask.getFilename(), Constant.posxTaskTitle, Constant.posyTaskTitle);
		
//		进度条
		g.setColor(colorProgressBar);
		g.drawRect(Constant.posxProgressBarBorder, Constant.posyProgressBarBorder, Constant.widthProgressBarBorder, Constant.heightProgressBarBorder);
		g.fillRect(Constant.posxProgressBar, Constant.posyProgressBar, (int)(structTask.getPercentage() * Constant.widthProgressBar), Constant.heightProgressBar);
		
//		进度百分比
		g.setColor(Color.blue);
		g.drawString(String.valueOf(structTask.getPercentage()) + "%", Constant.posxPercentage, Constant.posyPercentage);
		
//		速率
		g.setColor(Color.green);
		g.drawString(structTask.getRate() + "k / s", Constant.posxRate, Constant.posyRate);
		
//		数据量
		g.setColor(Color.gray);
		g.drawString(structTask.getDownloaded() + "MB / " + structTask.getSize() + "MB", Constant.posxSize, Constant.posySize);
		
//		时间
//		这个最好格式化成 hh:mm:ss
		g.drawString(String.valueOf(structTask.getElapseTime()) + " / " + String.valueOf(structTask.getRemainTime()) , Constant.posxTime, Constant.posyTime);
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
		if(! isSelected){
			colorTaskBorder = Constant.colorActiveTaskBorder;
			repaint();
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(! isSelected){
			colorTaskBorder = Constant.colorDefaultTaskBorder;
			repaint();
		}
	}
}

package p2p.client.ui;

import javax.swing.*;

import p2p.utils.Configurator;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrameConfig extends JFrame implements ActionListener{

	private JLabel labelHost;
	private JLabel labelPort;
	private JLabel labelServerHost;
	private JLabel labelServerPort;
	private JLabel labelSubTaskSize;
	private JLabel labelCoreThreadPoolSize;
	private JLabel labelMaxThreadPoolSize;
	private JTextField textFieldHost;
	private JTextField textFieldPort;
	private JTextField textFieldServerHost;
	private JTextField textFieldServerPort;
	private JTextField textFieldSubTaskSize;
	private JTextField textFieldCoreThreadPoolSize;
	private JTextField textFieldMaxThreadPoolSize;
	private JButton buttonModify;

	private JFrame frameParent;

	private JPanel panelConfig;

	private String host;
	private String port;
	private String serverHost;
	private String serverPort;
	private String subTaskSize;
	private String coreThreadPoolSize;
	private String maxThreadPoolSize;

	public FrameConfig(JFrame frameParent){
		this.frameParent = frameParent;
		setTitle(Constant.titleConfig);
		initComponent();
		setResizable(false);
	}

	public String getHost(){
		return host;
	}

	public String getPort(){
		return port;
	}

	public void initComponent(){
		labelHost = new JLabel(Constant.labelHost);
		labelHost.setBounds(Constant.posxLabelHost, Constant.posyLabelHost, Constant.widthLabelHost, Constant.heightLabelHost);

		labelPort = new JLabel(Constant.labelPort);
		labelPort.setBounds(Constant.posxLabelPort, Constant.posyLabelPort, Constant.widthLabelPort, Constant.heightLabelPort);
		
		labelServerHost = new JLabel(Constant.labelServerHost);
		labelServerHost.setBounds(Constant.posxLabelServerHost, Constant.posyLabelServerHost, Constant.widthLabelServerHost, Constant.heightLabelServerHost);
		
		labelServerPort = new JLabel(Constant.labelServerPort);
		labelServerPort.setBounds(Constant.posxLabelServerPort, Constant.posyLabelServerPort, Constant.widthLabelServerPort, Constant.heightLabelServerPort);
		
		labelSubTaskSize = new JLabel(Constant.labelSubTaskSize);
		labelSubTaskSize.setBounds(Constant.posxLabelSubTaskSize, Constant.posyLabelSubTaskSize, Constant.widthLabelSubTaskSize, Constant.heightLabelSubTaskSize);
		
		labelCoreThreadPoolSize = new JLabel(Constant.labelCoreThreadPoolSize);
		labelCoreThreadPoolSize.setBounds(Constant.posxLabelCoreThreadPoolSize, Constant.posyLabelCoreThreadPoolSize, Constant.widthLabelCoreThreadPoolSize, Constant.heightLabelCoreThreadPoolSize);
		
		labelMaxThreadPoolSize = new JLabel(Constant.labelMaxThreadPoolSize);
		labelMaxThreadPoolSize.setBounds(Constant.posxLabelMaxThreadPoolSize, Constant.posyLabelMaxThreadPoolSize, Constant.widthLabelMaxThreadPoolSize, Constant.heightLabelMaxThreadPoolSize);

		Configurator.init();
		host = Configurator.getHost();
		port = Configurator.getPort();
		serverHost = Configurator.getServerHost();
		serverPort = Configurator.getServerPort();
		subTaskSize = Configurator.getSubTaskSize();
		coreThreadPoolSize = Configurator.getCoreThreadPoolSize();
		maxThreadPoolSize = Configurator.getMaxThreadPoolSize();
		
		textFieldHost = new JTextField(host);
		textFieldHost.setBounds(Constant.posxTextFieldHost, Constant.posyTextFieldHost, Constant.widthTextFieldHost, Constant.heightTextFieldHost);

		textFieldPort = new JTextField(port);
		textFieldPort.setBounds(Constant.posxTextFieldPort, Constant.posyTextFieldPort, Constant.widthTextFieldPort, Constant.heightTextFieldPort);
		
		textFieldServerHost = new JTextField(serverHost);
		textFieldServerHost.setBounds(Constant.posxTextFieldServerHost, Constant.posyTextFieldServerHost, Constant.widthTextFieldServerHost, Constant.heightTextFieldServerHost);
		
		textFieldServerPort = new JTextField(serverPort);
		textFieldServerPort.setBounds(Constant.posxTextFieldServerPort, Constant.posyTextFieldServerPort, Constant.widthTextFieldServerPort, Constant.heightTextFieldServerPort);
		
		textFieldSubTaskSize = new JTextField(subTaskSize);
		textFieldSubTaskSize.setBounds(Constant.posxTextFieldSubTaskSize, Constant.posyTextFieldSubTaskSize, Constant.widthTextFieldSubTaskSize, Constant.heightTextFieldSubTaskSize);
		
		textFieldCoreThreadPoolSize = new JTextField(coreThreadPoolSize);
		textFieldCoreThreadPoolSize.setBounds(Constant.posxTextFieldCoreThreadPoolSize, Constant.posyTextFieldCoreThreadPoolSize, Constant.widthTextFieldCoreThreadPoolSize, Constant.heightTextFieldCoreThreadPoolSize);
		
		textFieldMaxThreadPoolSize = new JTextField(maxThreadPoolSize);
		textFieldMaxThreadPoolSize.setBounds(Constant.posxTextFieldMaxThreadPoolSize, Constant.posyTextFieldMaxThreadPoolSize, Constant.widthTextFieldMaxThreadPoolSize, Constant.heightTextFieldMaxThreadPoolSize);

		buttonModify = new JButton(Constant.textModify);
		buttonModify.setBounds(Constant.posxButtonModify, Constant.posyButtonModify, Constant.widthButtonModify, Constant.heightButtonModify);
		buttonModify.addActionListener(this);

		panelConfig = new JPanel();
		panelConfig.setPreferredSize(new Dimension(Constant.widthFrameConfig, Constant.heightFrameConfig));
		panelConfig.setLayout(null);
		panelConfig.add(labelHost);
		panelConfig.add(textFieldHost);
		panelConfig.add(labelPort);
		panelConfig.add(textFieldPort);
		panelConfig.add(labelServerHost);
		panelConfig.add(textFieldServerHost);
		panelConfig.add(labelServerPort);
		panelConfig.add(textFieldServerPort);
		panelConfig.add(labelSubTaskSize);
		panelConfig.add(textFieldSubTaskSize);
		panelConfig.add(labelCoreThreadPoolSize);
		panelConfig.add(textFieldCoreThreadPoolSize);
		panelConfig.add(labelMaxThreadPoolSize);
		panelConfig.add(textFieldMaxThreadPoolSize);
		panelConfig.add(buttonModify);

		add(panelConfig);
		pack();
	}

	public boolean modify(){
		Pattern patternHost = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
		Pattern patternPort = Pattern.compile("^\\d+$");
		Pattern patternServerHost = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
		Pattern patternServerPort = Pattern.compile("^\\d+$");
		Pattern patternSubTaskSize = Pattern.compile("^\\d+$");
		Pattern patternCoreThreadPoolSize = Pattern.compile("^\\d+$");
		Pattern patternMaxThreadPoolSize = Pattern.compile("^\\d+$");
		Matcher matcherHost;
		Matcher matcherPort;
		Matcher matcherServerHost;
		Matcher matcherServerPort;
		Matcher matcherSubTaskSize;
		Matcher matcherCoreThreadPoolSize;
		Matcher matcherMaxThreadPoolSize;

		matcherHost = patternHost.matcher(textFieldHost.getText());
		matcherPort = patternPort.matcher(textFieldPort.getText());
		matcherServerHost = patternServerHost.matcher(textFieldServerHost.getText());
		matcherServerPort = patternServerPort.matcher(textFieldServerPort.getText());
		matcherSubTaskSize = patternSubTaskSize.matcher(textFieldSubTaskSize.getText());
		matcherCoreThreadPoolSize = patternCoreThreadPoolSize.matcher(textFieldCoreThreadPoolSize.getText());
		matcherMaxThreadPoolSize = patternMaxThreadPoolSize.matcher(textFieldMaxThreadPoolSize.getText());

		if(! matcherHost.matches() || ! matcherPort.matches() || 
				!matcherServerHost.matches() || !matcherServerPort.matches() || 
				!matcherSubTaskSize.matches() || !matcherCoreThreadPoolSize.matches() || 
				!matcherMaxThreadPoolSize.matches()){
			JOptionPane.showMessageDialog(this, "非法数据", "提示", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		host = textFieldHost.getText();
		port = textFieldPort.getText();
		serverHost = textFieldServerHost.getText();
		serverPort = textFieldServerPort.getText();
		subTaskSize = textFieldSubTaskSize.getText();
		coreThreadPoolSize = textFieldCoreThreadPoolSize.getText();
		maxThreadPoolSize = textFieldMaxThreadPoolSize.getText();
		Configurator.modify(host, port, serverHost, serverPort, subTaskSize, coreThreadPoolSize, maxThreadPoolSize);

		return true;
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
		if(action == buttonModify){
			if(modify())
				setVisible(false);
		}
	}
}

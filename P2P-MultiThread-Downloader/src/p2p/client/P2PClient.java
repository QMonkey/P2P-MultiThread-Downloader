package p2p.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.handler.RequestHandler;
import p2p.client.protocol.P2PProtocolHeader;
import p2p.client.task.TaskManager;
import p2p.utils.Configurator;

public class P2PClient implements Runnable {

	private void listen() {
		ServerSocket ssocket = null;
		try {
			ssocket = new ServerSocket(Integer.valueOf(Configurator.getPort()));
			while(true) {
				Socket socket = ssocket.accept();
				new Thread(new RequestHandler(socket)).start();
			}
			//ssocket.close();
		} catch (Exception e) {
			Logger.getLogger(P2PClient.class.getName()).log(Level.ALL, null, e);
		}
	}
	
	private void online() {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(Configurator.getServerHost(),
					Integer.valueOf(Configurator.getServerPort())), 10000);
			socket.getOutputStream().write(P2PProtocolHeader.ONLINE.getValue());
			socket.close();
		} catch (Exception e) {
			Logger.getLogger(P2PClient.class.getName()).log(Level.ALL, null, e);
		}
	}
	
	private void outline() {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(Configurator.getServerHost(),
					Integer.valueOf(Configurator.getServerPort())), 10000);
			socket.getOutputStream().write(P2PProtocolHeader.OUTLINE.getValue());
			socket.close();
		} catch (Exception e) {
			Logger.getLogger(P2PClient.class.getName()).log(Level.ALL, null, e);
		}
	}

	@Override
	public void run() {
		online();
		listen();
		outline();
	}

	public static void main(String[] args) {
		Configurator.init();
		P2PClient client = new P2PClient();
		new Thread(client).start();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			TaskManager manager = new TaskManager(Integer.valueOf(Configurator.getCoreThreadPoolSize()),
					Integer.valueOf(Configurator.getMaxThreadPoolSize()));
			int subTaskSize = Integer.valueOf(Configurator.getSubTaskSize());
			while((input = br.readLine()) != null) {
				manager.addTask(input,subTaskSize);
			}
		} catch (Exception e) {
			Logger.getLogger(P2PClient.class.getName()).log(Level.ALL, null, e);
		}
	}
}

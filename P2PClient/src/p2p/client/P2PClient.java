package p2p.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.task.TaskManager;

public class P2PClient implements Runnable {

	private void listen() {
		ServerSocket ssocket = null;
		try {
			ssocket = new ServerSocket(9999);
			while(true) {
				Socket socket = ssocket.accept();
				//new Thread(new TaskChooser(socket)).start();
			}
		} catch (Exception e) {
			Logger.getLogger(P2PClient.class.getName()).log(Level.ALL, null, e);
		}
	}
	
	private void online() {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(),9999);
			socket.getOutputStream().write(0);
			socket.close();
		} catch (Exception e) {
			Logger.getLogger(P2PClient.class.getName()).log(Level.ALL, null, e);
		}
	}

	@Override
	public void run() {
		online();
		listen();
	}

	public static void main(String[] args) {
		P2PClient client = new P2PClient();
		new Thread(client).start();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			while((input = br.readLine()) != null) {
				TaskManager manager = new TaskManager(5,10);
				manager.addTask(input,10);
				manager.dispatch();
			}
		} catch (Exception e) {
			Logger.getLogger(P2PClient.class.getName()).log(Level.ALL, null, e);
		}
	}
}

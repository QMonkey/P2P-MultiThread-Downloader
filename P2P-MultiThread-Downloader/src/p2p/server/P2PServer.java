package p2p.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.server.handler.RequestHandler;

public class P2PServer {

	public void listen() {
		LinkedList<String> iptable = new LinkedList<String>(); 
		ServerSocket ssocket = null;
		try {
			ssocket = new ServerSocket(10000);
			while(true) {
				Socket socket = ssocket.accept();
				new Thread(new RequestHandler(socket,iptable)).start();
			}
		} catch (Exception e) {
			Logger.getLogger(P2PServer.class.getName()).log(Level.ALL, null, e);
		}
	}

	public static void main(String[] args) {
		P2PServer server = new P2PServer();
		server.listen();
	}
}

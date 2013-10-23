package p2p.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.server.handler.RequestHandler;

public class P2PServer {

	public void listen() {
		ServerSocket ssocket = null;
		try {
			ssocket = new ServerSocket(9999);
			while(true) {
				Socket socket = ssocket.accept();
				new Thread(new RequestHandler(socket)).start();
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

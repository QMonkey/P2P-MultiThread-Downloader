package p2p.server.handler;

import java.io.InputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.protocol.P2PProtocolHeader;

public class RequestHandler implements Runnable {
	private Socket socket;
	private LinkedList<String> iptable;

	public RequestHandler(Socket socket,LinkedList<String> iptable) {
		this.socket = socket;
		this.iptable = iptable;
	}

	@Override
	public void run() {
		try {
			InputStream ins = socket.getInputStream();

			switch(P2PProtocolHeader.values()[ins.read()]) {
			case ONLINE:
				IPTableManager.add(iptable,socket.getInetAddress().toString());
				break;
			case OUTLINE:
				IPTableManager.del(iptable, socket.getInetAddress().toString());
				break;
			case UPLOAD_RESOURCE_INFO:
				//TODO
				break;
			case REQUEST_IP:
				//TODO
				break;
			case REQUEST_RESOURCE:
				//TODO
				break;
			default:
				break;
			}
			socket.close();
		} catch (Exception e) {
			Logger.getLogger(RequestHandler.class.getName()).log(Level.ALL, null, e);
		}
	}
}

package p2p.client.handler;

import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.protocol.P2PProtocolHeader;

public class RequestHandler implements Runnable {
	private Socket socket;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream ins = socket.getInputStream();

			switch(P2PProtocolHeader.values()[ins.read()]) {
			case REQUEST_RESOURCE:
				DataJumper.transmit(socket);
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
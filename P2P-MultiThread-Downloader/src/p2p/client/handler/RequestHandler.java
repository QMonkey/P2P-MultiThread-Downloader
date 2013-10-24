package p2p.client.handler;

import java.io.InputStream;
import java.io.OutputStream;
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
			OutputStream outs = socket.getOutputStream();

			switch(P2PProtocolHeader.values()[ins.read()]) {
			case SEARCH_RESOURCE:
				ResourceSeacher.search(ins, outs);
				break;
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
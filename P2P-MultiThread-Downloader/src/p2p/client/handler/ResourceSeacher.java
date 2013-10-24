package p2p.client.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.protocol.P2PProtocolHeader;

public class ResourceSeacher {
	public static void search(InputStream ins,OutputStream outs) {
		DataInputStream dis = new DataInputStream(ins);
		try {
			int fileNameSize = dis.readInt();
			byte[] buffer = new byte[fileNameSize];
			dis.read(buffer);
			String fileName = new String(buffer);
			File file = new File(fileName);
			if(file.exists()) {
				int ipSize = dis.readInt();
				byte[] ipBits = new byte[ipSize];
				dis.read(ipBits);
				Socket socket = new Socket(new String(ipBits),9998);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.write(P2PProtocolHeader.RESPONSE_SEARCH.getValue());
				dos.flush();
				socket.close();
			}
		} catch (IOException e) {
			Logger.getLogger(ResourceSeacher.class.getName()).log(Level.ALL, null, e);
		}
	}
}

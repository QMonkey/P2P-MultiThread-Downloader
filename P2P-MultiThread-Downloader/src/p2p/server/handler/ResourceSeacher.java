package p2p.server.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import p2p.client.protocol.P2PProtocolHeader;

public class ResourceSeacher {
	public static void search(InputStream ins,OutputStream outs, LinkedList<String> iptable, String ip) {
		DataInputStream dis = new DataInputStream(ins);
		try {
			int fileNameSize = dis.readInt();
			byte[] buffer = new byte[fileNameSize];
			dis.read(buffer);
			
			for(String ipItem : iptable) {
				if(ip.equals(ipItem)) {
					continue;
				}
				Socket socket = new Socket(ipItem, 9999);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.write(P2PProtocolHeader.SEARCH_RESOURCE.getValue());
				dos.writeInt(fileNameSize);
				dos.write(buffer);
				dos.writeInt(ip.length());
				dos.write(ip.getBytes());
				dos.flush();
				socket.close();
			}
		} catch (IOException e) {
			Logger.getLogger(ResourceSeacher.class.getName()).log(Level.ALL, null, e);
		}
	}
}

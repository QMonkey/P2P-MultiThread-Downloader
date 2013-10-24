package p2p.client.handler;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataJumper {
	private static final int BUFFER_LENGTH = 4096;

	public static void transmit(Socket socket) {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			int fileNameSize = dis.readInt();
			byte[] buffer = new byte[fileNameSize];
			dis.read(buffer);
			String fileName = new String(buffer);
			File file = new File(fileName);
			if(file.exists()) {
				buffer = new byte[BUFFER_LENGTH];
				InputStream ins = new BufferedInputStream(new FileInputStream(file));
				OutputStream outs = socket.getOutputStream();
				int rd = 0;
				while((rd = ins.read(buffer)) > 0) {
					outs.write(buffer, 0, rd);
				}
				ins.close();
			}
			socket.close();
		} catch(Exception e) {
			Logger.getLogger(DataJumper.class.getName()).log(Level.ALL, null, e);
		}
	}

}

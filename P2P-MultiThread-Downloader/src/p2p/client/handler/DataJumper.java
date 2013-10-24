package p2p.client.handler;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
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
			RandomAccessFile in = new RandomAccessFile(fileName,"r");
			long taskBegin = dis.readLong();
			long taskEnd = dis.readLong();
			buffer = new byte[BUFFER_LENGTH];
			int rd = 0;
			in.seek(taskBegin);
			OutputStream outs = socket.getOutputStream();
			while((rd = in.read(buffer,0,(int) (taskEnd-taskBegin < BUFFER_LENGTH ? taskEnd-taskBegin : BUFFER_LENGTH))) != -1) {
				outs.write(buffer,0,rd);
				taskBegin += rd;
			}
			in.close();
			socket.close();
		} catch(Exception e) {
			Logger.getLogger(DataJumper.class.getName()).log(Level.ALL, null, e);
		}
	}

}

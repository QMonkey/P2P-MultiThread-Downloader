package p2p.client.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler implements Runnable {
	private Socket client;

	public RequestHandler(Socket socket) {
		this.client = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String input = br.readLine();
			System.out.println(input);
			PrintWriter pw = new PrintWriter(client.getOutputStream());
			pw.write("Hello world!");
			pw.flush();
			client.close();
		} catch (Exception e) {
			Logger.getLogger(RequestHandler.class.getName()).log(Level.ALL, null, e);
		}
	}
}
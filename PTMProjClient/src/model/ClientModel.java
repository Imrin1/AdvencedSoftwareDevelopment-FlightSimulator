package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientModel {
	public static volatile boolean stop=false;
	private static PrintWriter out;
	private static Socket socket;
	
	public void Connect(String ip, int port) {
		try {
			socket = new Socket(ip,port);
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void Send(String[] toSend) {
		for(String str : toSend) {
			out.println(str);
			out.flush();
		}
	}
	
	public void Stop() {
		if(out!=null) {
			out.close();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

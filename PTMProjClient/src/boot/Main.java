package boot;

import server_side.MySerialServer;
import server_side.MyClientHandler;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = Integer.parseInt(args[0]); 
		MySerialServer server=new MySerialServer();
		//MyTestClientHandler ch=new MyTestClientHandler();
		//server.start(port, ch);
	}

}

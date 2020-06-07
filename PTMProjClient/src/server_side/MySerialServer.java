package server_side;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server {
//////////////data////////////////////////////////	
	
	private volatile boolean stop;
///////////////////////////////////////////////// 
	
	
	public MySerialServer() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void open(int port, ClientHandler c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		stop=true;
	}
	
	
	public void start(int port,ClientHandler ch){
		System.out.println("hello world");
		new Thread(()->{
			try {
				runserver(port,ch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start(); 
		}
		
	private void runserver(int port, ClientHandler ch)throws Exception {
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000);
		while(!stop){
			try{
				Socket aClient=server.accept(); // blocking call
				try {
						ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
						//aClient.getInputStream().close();
						//aClient.getOutputStream().close();
						aClient.close();
					} catch (IOException e) {/*...*/}
				}catch(SocketTimeoutException e) {/*...*/}
			}
			server.close();
		}

}

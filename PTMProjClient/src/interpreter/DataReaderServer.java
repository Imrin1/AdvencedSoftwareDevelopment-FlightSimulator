package interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.System;

public class DataReaderServer   {

	
	private volatile boolean stop;
///////////////////////////////////////////////// 
	
	public DataReaderServer() {
		// TODO Auto-generated constructor stub
	}


	public void stop() {
	stop=true;
	}
	
	public void start(int port,int frequency){
		System.out.println("Starting Server");
		new Thread(()->{
			try {
				runServer(port,frequency);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}).start(); 
	}
	
	private void runServer(int port,int frequency)throws Exception {
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000);
	
		while(!stop){
			try{
				Socket aClient=server.accept(); // blocking call
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(aClient.getInputStream()));
					String line=null;
					while(true) 
					{
						try {line = in.readLine();} 
							catch (IOException e) {e.printStackTrace();}

						if(line==null)
						{
							break;
						}
						
						//System.out.println(line);
						String[] s = line.split(",");
						if(Interperter.PathTable!=null) {
							if(Interperter.PathTable.containsKey("simX"))
								Interperter.PathTable.get("simX").setValue(Double.parseDouble(s[0]));
							if(Interperter.PathTable.containsKey("simY"))
								Interperter.PathTable.get("simY").setValue(Double.parseDouble(s[1]));
							if(Interperter.PathTable.containsKey("simZ"))
								Interperter.PathTable.get("simZ").setValue(Double.parseDouble(s[2]));
						}
						Thread.sleep(1000/frequency);
					}
					aClient.close();
					} catch (IOException e) {/*...*/}
				}catch(SocketTimeoutException e) {/*...*/}
			}
			server.close();
		}
}

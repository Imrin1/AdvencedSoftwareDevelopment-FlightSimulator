package model;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import interpreter.*;
public class Model extends Observable implements Observer{
	 
	
	public Model(){
		// TODO Auto-generated constructor stub
	}
	
	public void connect(String ip, int port) {
		try {
			//System.out.println(port);
			//System.out.println(Integer.parseInt(port));
			Socket socket=new Socket(ip, port);
			System.out.println("connected to the server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//try
	public int Interpert(String[] arr) {
		Interperter interpeter = new Interperter();
		return interpeter.interpret(arr);
	}

@Override
public void update(Observable o, Object arg) {}


		
	
}

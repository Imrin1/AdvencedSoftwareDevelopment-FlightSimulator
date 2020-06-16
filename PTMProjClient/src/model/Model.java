package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import interpreter.*;
import javafx.beans.property.DoubleProperty;
public class Model extends Observable implements Observer{
	 
	public double startX;
	public double startY;
	public double cellSize;
	public double airplaneX;
	public double airplaneY;
	public double destX;
	public double destY;
	int[][]mapData;
	public static BufferedWriter out;
	public static BufferedReader in;
	private String[] solution;
	public static HashMap<String, Integer>directions = new HashMap<String, Integer>() ;
	public Model(){
		directions.put("up", 0);
		directions.put("right", 90);
		directions.put("down", 180);
		directions.put("left", 270);
	}
	
	public void connectCalculatePath(String ip, int port) {
		try {
			Socket socket=new Socket(ip, port);
			System.out.println("connected to the server");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void connectSimulator(String ip, int port) {
		try {
			Socket socket=new Socket(ip, port);
			System.out.println("connected to the server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void CalculatePath() {
		new Thread(()-> {
			try {
				for (int i = 0; i < mapData.length; i++) {
					for (int j = 0; j < mapData[i].length; j++) {
							out.append(mapData[i][j] + ",");
					}
						out.append("/n");
						out.flush();
				}
					out.append("end");
					out.append(this.airplaneX+","+this.airplaneY);
					out.append(this.destX+","+this.destY);
					out.flush();
					
					solution = in.readLine().split(",");
			}catch(IOException e) {
				e.printStackTrace();
				}
		}).start();
	}
//try
	public int Interpert(String[] arr) {
		Interperter interpeter = new Interperter();
		return interpeter.interpret(arr);
	}

@Override
public void update(Observable o, Object arg) {}


		
	
}

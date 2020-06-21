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
	public double angle;
	int[][]mapData;
	public static BufferedWriter out;
	public static BufferedReader in;
	private String[] solution;
	private Interperter interperter;
	ClientModel clientModel;
	PathCalculator pathCalc;
	
	public static HashMap<String, Integer>directions = new HashMap<String, Integer>() ;
	public Model(){
		this.clientModel= new ClientModel();
		this.pathCalc = new PathCalculator();
		directions.put("up", 0);
		directions.put("right", 90);
		directions.put("down", 180);
		directions.put("left", 270);
	}
	
	
	public void connectSimulator(String ip, int port) {
		clientModel.Connect(ip, port);
	}
	public void sendSimulator(String[] toSend) {
		clientModel.Send(toSend);
	}
	
	public void connectCalculatePath(String ip, int port) {
		pathCalc.connectCalculatePath(ip, port);
	}
	public void CalculatePath(int data[][] ,double airplaneX, double airplaneY, double destX, double destY) {
		this.airplaneX =airplaneX;
		this.airplaneY = airplaneY;
		this.destX = destX;
		this.destY = destY;
		this.mapData = data;
		new Thread(()-> {
				solution = pathCalc.CalculatePath(data, airplaneX, airplaneY, destX, destY);
				this.setChanged();
				this.notifyObservers();
		}).start();
	}
	public void airplanePosition(double StartX, double StartY) {
		this.startX = StartX;
		this.startY = StartY;
		new Thread(()->{
			
			
		}).start();
	}
//try
	public int Interpert(String[] arr) {
		 this.interperter = new Interperter();
		return this.interperter.interpret(arr);
	}
	
@Override
public void update(Observable o, Object arg) {}

}

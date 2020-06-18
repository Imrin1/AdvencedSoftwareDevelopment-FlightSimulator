package view_model;
 import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ViewModel  extends Observable implements Observer{

	
	private Model m;
	
	public StringProperty ToInterpert;
	public StringProperty ip;
	public StringProperty port;
	public DoubleProperty startX;
	public DoubleProperty startY;
	public DoubleProperty airplaneX;
	public DoubleProperty airplaneY;
	public DoubleProperty destX;
	public DoubleProperty destY;
	public DoubleProperty angle;
	public DoubleProperty cellSize;
	
	
	public ViewModel() {
		this.ToInterpert = new SimpleStringProperty();
		this.ip = new SimpleStringProperty();
		this.port = new SimpleStringProperty();
		startX= new SimpleDoubleProperty();
		startY = new SimpleDoubleProperty();
		cellSize = new SimpleDoubleProperty();
		airplaneX = new SimpleDoubleProperty();
		airplaneY = new SimpleDoubleProperty();
		destX = new SimpleDoubleProperty();
		destY = new SimpleDoubleProperty();
		angle = new SimpleDoubleProperty();
		this.cellSize = new SimpleDoubleProperty();
	}
	 public void setModel(Model model) {
		 this.m = model;
	 }
	
	public int Interpert() {
		String[] arr = ToInterpert.get().split("\n");
		return m.Interpert(arr);
	}
	public void connectCalculatePath() {
		m.connectCalculatePath(ip.getValue(), Integer.parseInt(port.getValue()));
	}
	public void connectSimulator() {
		m.connectSimulator(ip.getValue(), Integer.parseInt(port.getValue()));
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

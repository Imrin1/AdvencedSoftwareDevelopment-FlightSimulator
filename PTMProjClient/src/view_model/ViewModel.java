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
	public DoubleProperty cellSize;
	
	
	public ViewModel() {
		// TODO Auto-generated constructor stub
		this.ToInterpert = new SimpleStringProperty();
		this.ip = new SimpleStringProperty();
		this.port = new SimpleStringProperty();
		this.startX = new SimpleDoubleProperty();
		this.startY = new SimpleDoubleProperty();
		this.cellSize = new SimpleDoubleProperty();
		this.m = new Model();
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

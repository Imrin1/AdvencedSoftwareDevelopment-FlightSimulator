package view_model;
 import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ViewModel  extends Observable implements Observer{

	
	private Model m;
	
	public StringProperty ToInterpert;
	public StringProperty ip;
	public StringProperty port;
	
	
	public ViewModel() {
		// TODO Auto-generated constructor stub
		this.ToInterpert = new SimpleStringProperty();
		this.ip = new SimpleStringProperty();
		this.port = new SimpleStringProperty();
		this.m = new Model();
	}
	
	public int Interpert() {
		String[] arr = ToInterpert.get().split("\n");
		return m.Interpert(arr);
	}
	public void connect() {
		System.out.println(ip.get()+(port.get()));
		m.connect(ip.getValue(), port.getValue());
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

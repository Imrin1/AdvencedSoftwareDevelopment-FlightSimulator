package Interperter;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Observer;

public class PathBind extends Observable implements Observer {

	
	protected String path;
	private Double value;
	
	public PathBind(String Path) {
		this.path=Path;
		
	}
	public PathBind(String Path,Double val) {
		this.path=Path;
		this.value=val;
	}
	
	@Override
	public void update(Observable o, Object arg) {
			
			if(this.value!=(Double)arg) {
				this.setValue((Double)arg);
				try {
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ConnectCommand.socket.getOutputStream()));
					out.write("set"+path+" "+value.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
	}
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setValue(Double value) {
		this.value = value;
		this.setChanged();
		this.notifyObservers(value);
		
	
	}
	
	public Double getValue() {
		return value;
	}
	
	
	
	
	
	
	
	
}

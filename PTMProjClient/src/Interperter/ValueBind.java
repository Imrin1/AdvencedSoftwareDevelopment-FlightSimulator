package Interperter;

import java.util.Observable;
import java.util.Observer;

public class ValueBind extends Observable implements Observer {

	
	
	protected Double value;
	
	public ValueBind(Double val) {
		this.value=val;
	}

	public void setVal(Double value) {
		this.value=value;
		setChanged();
		notifyObservers(value);
	}
	
	@Override
	public void update(Observable o, Object arg) {
			if(this.value!=(Double)arg) {
				this.value=(Double)arg;
				this.setChanged();	
			}
	}
	
}

	
	


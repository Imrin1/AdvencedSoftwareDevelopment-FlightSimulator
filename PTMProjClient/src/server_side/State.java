package server_side;

public class State<T>implements Comparable<State<T>> {
	T state;
	private State<T> cameFrom;
	private double cost;
	private double originalCost;
	
	public State(T state,double Cost,double origin) {
	this.state=state;
	this.cost=Cost;
	this.setOriginalCost(origin);
	}
	
	@Override
	/*public boolean equals(Object obj) {
		
		return state.equals(obj);
	}
	*/
public boolean equals(Object st) {
		return state.equals(((State)st).state);
	}
	public State<T> getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean compare(State<T> s) {
		return this.getCost()>s.getCost();
	}

	public double getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(double originalCost) {
		this.originalCost = originalCost;
	}

	@Override 
	public int compareTo(State<T> state) {
		return (int)(this.getCost()-state.getCost());
			
	}
}
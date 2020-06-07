package server_side;


import java.util.ArrayList;

public interface Searchable<T> {
	State<T> getInitialState();
	ArrayList<State<T>> getAllPossibleStates(State<T> s);
	boolean isGoalState(State<T> s);
	
	
}

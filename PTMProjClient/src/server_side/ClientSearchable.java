package server_side;

import java.util.ArrayList;

public class ClientSearchable implements Searchable<position> {

	private double[][] mat;
	private position GoalState;
	private position Init_state;
	ArrayList<State<position>>arr;
	public void setGoalState(int i, int j) {
	this.GoalState=new position(i,j);	
	}
	
	public void setMat(double[][] matr) {
		this.mat=matr;
	}
	
	


	
	
	public ClientSearchable(double[][] mat2, position goalState2, position init_state2) {
		this.mat=mat2;
		this.GoalState=goalState2;
		this.Init_state=init_state2;
	}
	

	
	@Override
	public State getInitialState() {
		return new State<>(this.Init_state,mat[this.Init_state.getX()][this.Init_state.getY()],mat[this.Init_state.getX()][this.Init_state.getY()]);
	}
	



	
	public ArrayList<State<position>> getAllPossibleStates(State<position> s) {
        ArrayList<State<position>> arr = new ArrayList<State<position>>();

        if(s.state.getX()>0)
            arr.add(new State(new position(s.state.getX()-1,s.state.getY()),mat[s.state.getX()-1][s.state.getY()]+s.getCost(),mat[s.state.getX()-1][s.state.getY()]));
        if(s.state.getY()>0)
            arr.add(new State(new position(s.state.getX(),s.state.getY()-1),mat[s.state.getX()][s.state.getY()-1]+s.getCost(),mat[s.state.getX()][s.state.getY()-1]));
        if(mat.length>s.state.getX()+1)
            arr.add(new State(new position(s.state.getX()+1,s.state.getY()),mat[s.state.getX()+1][s.state.getY()]+s.getCost(),mat[s.state.getX()+1][s.state.getY()]));
        if(mat[0].length>s.state.getY()+1)
            arr.add(new State(new position(s.state.getX(),s.state.getY()+1),mat[s.state.getX()][s.state.getY()+1]+s.getCost(),mat[s.state.getX()][s.state.getY()+1]));

        return arr;
    }

	@Override
	public boolean isGoalState(State<position> s) {
		
			return this.GoalState.equals((position)s.state);
		
	}

	

	

}

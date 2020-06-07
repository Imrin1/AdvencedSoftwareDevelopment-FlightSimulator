package server_side;

import java.util.Set;
import java.util.*;
public class BestFirstSearch extends CommonSearcher {

	
	@Override
	public ArrayList<State> search(Searchable s) {	
			openList.clear();
		openList.add(s.getInitialState());
			ArrayList<State> closedSet=new ArrayList<State>();
			while(openList.size()>0){
				State n=popOpenList();// dequeue
				closedSet.add(n);
				
				
				
				if(s.isGoalState(n))
				{	
					// return path
					return BackTrace(n,s);
				}
				// private method, back traces through the parents
				
				ArrayList<State> successors=s.getAllPossibleStates(n); //however it is implemented
				for(State state : successors){
						if(!closedSet.contains(state) && ! openList.contains(state))
						{
							
							state.setCameFrom(n);
							state.setCost(n.getCost()+state.getOriginalCost());
							openList.add(state);
						} 
						else{
							if(state.getCost()>n.getCost()+state.getOriginalCost()) {
								if(!openList.contains(state)) {
									state.setCameFrom(n);
									state.setCost(n.getCost()+state.getOriginalCost());
									openList.add(state);
									
								}
								else {
									state.setCameFrom(n);
									openList.remove(state);
									state.setCost(n.getCost()+state.getOriginalCost());
									openList.add(state);
							
								}
							}
						}
						
				}
			}
			
			return null;
	}



	private ArrayList<State<position>> BackTrace(State<position> state,Searchable s){
		ArrayList<State<position>> retList=new ArrayList<State<position>>();
		State<position> temp=state;
		while(temp!=null)
		{
			
			retList.add(0,temp);
			temp=temp.getCameFrom();
		}
		return retList;
	}
}






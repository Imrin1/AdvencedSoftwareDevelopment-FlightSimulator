package server_side;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher{
	
		protected PriorityQueue<State<T>> openList;
		private int evaluatedNodes;
		
		public CommonSearcher() {
		openList=new PriorityQueue<State<T>>();
		evaluatedNodes=0;
		}
		final protected State popOpenList() {
		evaluatedNodes++;
		return openList.poll();
		}
		final protected void addOpenList(State s) {
			evaluatedNodes--;
			 openList.add(s);
			}
		@Override
		public abstract  ArrayList<State<T>> search(Searchable s);
		
		public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
		}
		
	

}

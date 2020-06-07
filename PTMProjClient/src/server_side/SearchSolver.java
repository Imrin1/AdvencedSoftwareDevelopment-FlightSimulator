package server_side;

import java.util.ArrayList;

public class SearchSolver implements Solver<Searchable> {
    private Searcher s;
    
    public SearchSolver() {
    
    }
    public SearchSolver(Searcher s2) {
        this.s=s2;
    }
    public Searcher getS() {
        return s;
    }

    public void setS(Searcher s) {
        this.s = s;
    }

    @Override
    public Solution solve(Searchable sa) {
        ArrayList<State<position>> arr= s.search(sa);
        State<position>pos;
        StringBuilder sb = new StringBuilder();
        pos=arr.remove(0);
        for(State<position> st : arr) {
        	if(st.state.getX()+1==pos.state.getX())
        	{
        		sb.append("Up,");
        	}
        	if(st.state.getX()==pos.state.getX()+1)
        	{
        		sb.append("Down,");
        	}
        	if(st.state.getY()+1==pos.state.getY())
        	{
        		sb.append("Left,");
        	}
        	if(st.state.getY()==pos.state.getY()+1)
        	{
        		sb.append("Right,");
        	}
        	pos=st;
        }
        sb.deleteCharAt(sb.length()-1);

        Solution sol= new Solution();
         sol.setS(sb.toString());
        return sol;
    }
    

}
package server_side;

public interface CacheManager<problem,solution> {

	public solution FindSolution(problem p);
	public void SaveSolution(problem p, solution s);
	
	
}

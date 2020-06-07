package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyClientHandler implements ClientHandler{

	
	private CacheManager<Searchable<position>,String> cm;
    private    Solver<Searchable>  solver;
    
    //***********************************************//
    public MyClientHandler() {
        
    cm= new FileCacheManager();
    solver=new SearchSolver();
    
    }
    public MyClientHandler(CacheManager<Searchable<position>,String> cm2, Solver<Searchable>  solver) {
        
        this.cm=((CacheManager<Searchable<position>,String>) cm2);
        this.solver=solver;
    }
	@Override
	public void handleClient(InputStream inputStream, OutputStream outputStream) {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		PrintWriter out = new PrintWriter(outputStream);
		String line=null;
		double[][] mat = null;
		int i=0;
		ArrayList<Double> inRow=new ArrayList<Double>();
		int j=0;
		
		while(true) 
		{
			
			try {line = in.readLine();} 
			
			catch (IOException e) {e.printStackTrace();}
			
			if(line.contentEquals("end"))
			{
				break;
			}
			i++;
			StringBuilder s=new StringBuilder(line);
			String[] stArray=s.toString().split(",");
			j=stArray.length;
			for(String str: stArray) {
					inRow.add(Double.parseDouble(str));
			}
			
		}
		mat=new double[i][j];
		for (int k = 0; k < i; k++) {
			for (int k2 = 0; k2 < j; k2++) {
				mat[k][k2]=(double)inRow.remove(0);
			}
		}
		try {line = in.readLine();} 
		catch (IOException e) {e.printStackTrace();}
		StringBuilder s=new StringBuilder(line);
		String[] stArray=s.toString().split(",");
		
		try {line = in.readLine();} 
		catch (IOException e) {e.printStackTrace();}
		StringBuilder s2=new StringBuilder(line);
		String[] stArray2=s2.toString().split(",");
		ClientSearchable searchable=new ClientSearchable(mat, new position(Integer.parseInt(stArray2[0]), Integer.parseInt(stArray2[1])), new position(Integer.parseInt(stArray[0]),Integer.parseInt(stArray[1]) ));
		SearchSolver ss=new SearchSolver(new BestFirstSearch());
		Solution solution=ss.solve(searchable);
		out.println(solution.getS());
		out.flush();

		
		
		}
}

	


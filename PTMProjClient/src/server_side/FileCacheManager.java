package server_side;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileCacheManager implements CacheManager<Searchable<position>,String>{

	public FileCacheManager() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String FindSolution(Searchable<position> p) {
		File f=new File(p+".sol");
		if(!f.exists())
			return null;
		
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(p+".sol"));
			String line=in.readLine();
			in.close();
			return line;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void SaveSolution(Searchable<position> p, String s) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(p+".sol"));
			out.println(s);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

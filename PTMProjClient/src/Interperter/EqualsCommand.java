package Interperter;

import java.util.HashMap;

public class EqualsCommand implements Command{

	
	public EqualsCommand() {

	}
	

	
	@Override
	public int doCommand(String[] s, int index) throws Exception {
		if(s[index+1].equals("bind"))
		{
			if(Interperter.PathTable.containsKey(s[index+2])) {
				Interperter.BindTable.put(s[index-1], Interperter.PathTable.get(s[index+2]));
			}
			else {
				Interperter.BindTable.put(s[index-1], new PathBind(s[index+2]));
				Interperter.PathTable.put(s[index+2],Interperter.BindTable.get(s[index-1]));
			}
			Interperter.VarTable.put(s[index-1],new ValueBind(0.0));
			Interperter.BindTable.get(s[index-1]).addObserver(Interperter.VarTable.get(s[index-1]));
			Interperter.VarTable.get(s[index-1]).addObserver(Interperter.BindTable.get(s[index-1]));
			
			return 3;
		}
		else {
			if(!Interperter.VarTable.containsKey(s[index-1]))
				Interperter.VarTable.put(s[index-1],new ValueBind(null));
			Interperter.VarTable.get(s[index-1]).setVal(ShuntingYard.Calculate(s, index+1));
			
			return ShuntingYard.WhenEnd(s, index+1)+1;
		}
		
	}
}

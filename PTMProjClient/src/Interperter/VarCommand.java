package Interperter;

import java.util.HashMap;

public class VarCommand  implements Command {

	
	
	

	
	public VarCommand() {
	
	}
	
	
	
			
	@Override
	public int doCommand(String[] s, int index) throws Exception {

		if(s[index+2].equals("=")) {
			if(Interperter.CommandsTable.containsKey(s[index+1]))
				throw new Exception("you can't define  a variable name as function name ");
			return 2+Interperter.CommandsTable.get("=").doCommand(s, index+2);
		}
		else {							
				Interperter.VarTable.put(s[index+1],new ValueBind(0.0)); // new variable without value
				return 2; 
		}
	}
}

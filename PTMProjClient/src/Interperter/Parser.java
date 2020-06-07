package Interperter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Parser {

	

	private int index;
	
	public Parser() {
		this.index=0;
	}
	
	public void parse(String[] tokens) throws Exception {
//		Interperter.returnValue=null;
		while(index< tokens.length)
		{
			if(Interperter.returnValue!=null)
				return;
			Command c= Interperter.CommandsTable.get(tokens[index]);
			if(c!=null) {
				index += c.doCommand(tokens, index);
				TimeUnit.MILLISECONDS.sleep(100);
			}
			else if(Interperter.VarTable.containsKey(tokens[index]))
				index++;
			
//			if(c.getClass().getSimpleName()=="ReturnCommand")
//				return;
//

		}
	}

}

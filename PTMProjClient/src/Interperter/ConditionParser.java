package Interperter;

import java.util.ArrayList;



public class ConditionParser implements Command {

	
	
	public ConditionParser() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int doCommand(String[] s, int index) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean Condition(String[] tokens,int index) throws Exception {
		
		for(int i = index ; ;i++) {
			switch(tokens[i]) {
			case "<":
				if(tokens[i+1]=="=")
					return ShuntingYard.Calculate(tokens,index)<=ShuntingYard.Calculate(tokens,i+2);
				else	{
					return ShuntingYard.Calculate(tokens,index)<ShuntingYard.Calculate(tokens,i+1);
				}
			case ">":
				if(tokens[i+1]=="=")
					return ShuntingYard.Calculate(tokens,index)>=ShuntingYard.Calculate(tokens,i+2);
				else	{
					return ShuntingYard.Calculate(tokens,index)>ShuntingYard.Calculate(tokens,i+1);
				}
			case "=" :
				return ShuntingYard.Calculate(tokens,index)==ShuntingYard.Calculate(tokens,i+2);
			case "!":
				return ShuntingYard.Calculate(tokens,index)!=ShuntingYard.Calculate(tokens,i+2);
				
			default : continue;
		}
		
	}
}

	public int whenConditionEnds(String[] tokens,int index) {
		int i =0;
		while(!tokens[index+i].equals("{")) {
		i++;			
		}
		
		return i;
	}
	
	public int whenBlockEnds(String[] tokens,int index) {
		int i =index+whenConditionEnds(tokens,index)+1;
		int counter=1;
		while(counter>0) {
		
			if(tokens[i].equals("}"))
				counter--;
			if(tokens[i].equals("{"))  
				counter++;
			i++;	
		}
	
		return i-index-1;
	}
}

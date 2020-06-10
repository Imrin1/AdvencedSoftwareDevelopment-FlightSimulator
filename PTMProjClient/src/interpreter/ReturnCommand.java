package interpreter;

public class ReturnCommand implements Command{
	
	public ReturnCommand() {}

	@Override
	public int doCommand(String[] s, int index) throws Exception {
		for(ValueBind val:Interperter.VarTable.values()) {
			System.out.println(val.value);
		}
		
		int TokenToReturn=(int)ShuntingYard.Calculate(s, index+1);
		Interperter.returnValue=(Integer) TokenToReturn;
		return TokenToReturn;
		//need to return this value in the interpert command
	}
	
}
 

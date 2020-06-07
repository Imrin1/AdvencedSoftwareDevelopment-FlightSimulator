package Interperter;

public class PrintCommand implements Command {

	
	public PrintCommand() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public int doCommand(String[] s, int index) throws Exception {
		int i=2;
		if(s[index+1].startsWith("\""))
		{
			while(!s[index+i].endsWith("\"")) {
				System.out.print(s[index+i]+" ");
				i++;
			}
			i++;
			return i; 
		}
		
		System.out.print(ShuntingYard.Calculate(s, index+1));
		return ShuntingYard.WhenEnd(s, index+1);
	}
}

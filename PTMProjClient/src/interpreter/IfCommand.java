package interpreter;

public class IfCommand extends ConditionParser{

	public IfCommand() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public int doCommand(String[] s, int index) throws Exception {
	int BlockIndex=this.whenConditionEnds(s, index+1)+1;
		
		
		if(this.Condition(s, index+1)) {
			while(BlockIndex>this.whenBlockEnds(s, index)) {
			Command c= Interperter.CommandsTable.get(s[BlockIndex]);
			if(c!=null)
				BlockIndex+=c.doCommand(s,BlockIndex);
			}
		}
		
		return this.whenBlockEnds(s, index);
	}
}


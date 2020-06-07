package Interperter;

public class WhileCommand extends ConditionParser{

	public WhileCommand() {
	}
	@Override
	public int doCommand(String[] s, int index) throws Exception {
		int BlockStart=index+this.whenConditionEnds(s, index+1)+2;
		int BlockEnds=index+this.whenBlockEnds(s, index);
		int BlockIndex;
		while(this.Condition(s, index+1)) {
			BlockIndex=BlockStart;
			while(BlockIndex<BlockEnds) {
			Command c= Interperter.CommandsTable.get(s[BlockIndex]);
			if(c!=null)
				BlockIndex+=c.doCommand(s,BlockIndex);
			else if(Interperter.VarTable.containsKey(s[BlockIndex]))
				BlockIndex++;
			}
			
		}
		
		return this.whenBlockEnds(s, index)+1;
	}
}

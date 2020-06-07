package Interperter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Interperter {
	
	protected static HashMap<String, ValueBind> VarTable;
	protected static HashMap<String,Command> CommandsTable;
	protected static HashMap<String, PathBind> BindTable;
	protected static ConcurrentHashMap<String, PathBind> PathTable;
	protected static Integer returnValue;
	private Parser parser;
	
	 public Interperter() {
			Interperter.CommandsTable=new  HashMap<String, Command>();
			Interperter.CommandsTable.put("if", new IfCommand());
			Interperter.CommandsTable.put("openDataServer", new OpenServerCommand());
			Interperter.CommandsTable.put("connect", new ConnectCommand());
			Interperter.CommandsTable.put("disconnect", new DisconnectCommand());
			Interperter.CommandsTable.put("var", new VarCommand());
			Interperter.CommandsTable.put("=", new EqualsCommand());
			Interperter.CommandsTable.put("while", new WhileCommand());
			Interperter.CommandsTable.put("return", new ReturnCommand());
			Interperter.CommandsTable.put("print", new PrintCommand());
						
			
		
			
			
			
	}
	
	public int interpret(String[] toInterpet) {
		
		Interperter.VarTable=new HashMap<String, ValueBind>();
		Interperter.BindTable=new HashMap<String, PathBind>();
		Interperter.PathTable=new ConcurrentHashMap<String, PathBind>();
		
		Interperter.returnValue=null;
		 
		String Final = String.join(" ", toInterpet);
		this.parser=new Parser();
		try {
			this.parser.parse(Lexer.lexer(Final));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Interperter.BindTable=null;
		Interperter.VarTable=null;
		Interperter.PathTable=null;
	

		return Interperter.returnValue;
	}

}
	

	
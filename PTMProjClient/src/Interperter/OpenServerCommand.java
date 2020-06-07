package Interperter;
public class OpenServerCommand implements Command {
	
	public OpenServerCommand() {
		
	}
	
	@Override
	public int doCommand(String[] s, int index) throws Exception {
		int valToReturn=1;
		int port=(int)ShuntingYard.Calculate(s, index+1);
		valToReturn+=ShuntingYard.WhenEnd(s, index+1);
		++valToReturn;
		int frequency=(int)ShuntingYard.Calculate(s, index+valToReturn);
		valToReturn+=ShuntingYard.WhenEnd(s, index+valToReturn);
		if(port<1||port >65535)
			throw new Exception("invalid port number");

		DataReaderServer server =new DataReaderServer();
		server.start(port, frequency);
		
		return ++valToReturn;
	}

}

package Interperter;

import java.net.Socket;

public class ConnectCommand implements Command{
	 public static Socket socket;
	public ConnectCommand() {}
	@Override
	public int doCommand(String[] s, int index) throws Exception {
		int valToReturn=1;
		String ip=s[index+1];
		valToReturn++;
		int port=(int)ShuntingYard.Calculate(s, index+valToReturn);
		valToReturn+=ShuntingYard.WhenEnd(s, index+valToReturn);
		socket=new Socket(ip, port);
		return ++valToReturn;
	}

}

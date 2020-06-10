package interpreter;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class DisconnectCommand implements Command{
	
	public DisconnectCommand() {
		
	}

	@Override
	public int doCommand(String[] s, int index) throws Exception {
		 
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(ConnectCommand.socket.getOutputStream()));
		bw.write("bye");
		bw.flush();
		bw.close();
		ConnectCommand.socket.close();
		return 1;
	}
	
	
}

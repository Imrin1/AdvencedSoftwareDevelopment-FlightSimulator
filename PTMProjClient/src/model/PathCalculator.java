package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class PathCalculator {
	public static PrintWriter out;
	public static  BufferedReader in;
	public static Socket socket;
	public void connectCalculatePath(String ip, int port) {
		try {
			socket=new Socket(ip, port);
			System.out.println("connected to the server- path calculator");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String[] CalculatePath(int mapData[][] ,double airplaneX, double airplaneY, double destX, double destY) {
		String[] solution = null;
			try {
				for (int i = 0; i < mapData.length; i++) {
					for (int j = 0; j < mapData[i].length; j++) {
							out.append(mapData[i][j] + ",");
					}
						out.append("/n");
						out.flush();
				}
					out.append("end");
					out.append(airplaneX+","+airplaneY);
					out.append(destX+","+destY);
					out.flush();
					
					solution = in.readLine().split(",");
					
			}catch(IOException e) {
				e.printStackTrace();
				}
			return solution;
			
	}
}

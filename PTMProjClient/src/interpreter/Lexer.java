package interpreter;

import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {

	  public static String[] lexer(String toInterpet) {
		Scanner s= new Scanner(spaces(toInterpet,"+-*/()=><!\""));
		ArrayList<String> arr =new ArrayList<String>();
		while(s.hasNext()) {
			arr.add(s.next());
		}
		String[] temp=new String[0];
		return ((String[])arr.toArray(temp));
	}
	  
	 private static String spaces(String s, String toSpace)
	 {
	   String space = " ";
	   for(Character c:toSpace.toCharArray()) {
	     String from= c.toString();
	     String to = space+c+space;
	     s=s.replace(from,to);
	   }
	   return s;
	 }
}



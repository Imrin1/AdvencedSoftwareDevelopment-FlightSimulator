package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Utilities {

	
	 public static int getMaxValue(int[][] numbers) {
	        int maxValue = numbers[0][0];
	        for (int j = 0; j < numbers.length; j++) {
	            for (int i = 0; i < numbers[j].length; i++) {
	                if (numbers[j][i] > maxValue) {
	                    maxValue = numbers[j][i];
	                }
	            }
	        }
	        return maxValue;
	    }
	 
	 public static boolean validIP(String ip) {
		    if (ip == null || ip.isEmpty()) return false;
		    ip = ip.trim();
		    if ((ip.length() < 6) & (ip.length() > 15)) return false;

		    try {
		        Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		        Matcher matcher = pattern.matcher(ip);
		        return matcher.matches();
		    } catch (PatternSyntaxException ex) {
		        return false;
		    }
		}
}

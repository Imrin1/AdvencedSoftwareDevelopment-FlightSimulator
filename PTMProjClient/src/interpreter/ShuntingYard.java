package interpreter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYard {
	
	private static boolean isDouble(String val){
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

	public static int WhenEnd(String[] tokens,int index) {
		String operators="+-/=*()";
		for(int i=index; ; i++) {
			if(tokens.length-1==i) {
				return i-index;
			}
			if(Interperter.CommandsTable.containsKey(tokens[i])) {
				return i-index;
			}
			
			if(!operators.contains(tokens[i-1])&&!operators.contains(tokens[i])) {
				return i-index;
			}	
		}
	}
	

	
	public static double Calculate(String[] tokens,int index)throws Exception{
		String operators="+-/*()";
		ArrayList<String> newArr=new ArrayList<String>();
		String[] temp = new String[0];
		for(int i=index; ; i++) {
			if(tokens.length-1==i) {
				newArr.add(tokens[i]);
				return calc((String[])newArr.toArray(temp));
			}
			if(Interperter.CommandsTable.containsKey(tokens[i])) {
				return calc((String[])newArr.toArray(temp));
			}
			if(i==index) {
				newArr.add(tokens[i]);	
				continue;
			}
				
			if(!operators.contains(tokens[i-1])&&!operators.contains(tokens[i])) {
				return calc((String[])newArr.toArray(temp));
			}
			newArr.add(tokens[i]);	
		}
	}
	
	
	public static double calc(String[] exp)throws Exception{
		Queue<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<String>();
		Stack<Expression> stackExp = new Stack<Expression>();
		String finalExp =String.join("", exp);
		
		String[] split = finalExp.split("(?<=[-+*/()])|(?=[-+*/()])");
		for (String s : split){
			if (isDouble(s)){
				queue.add(s);
			}
			else{
				if(Interperter.VarTable.containsKey(s)) {
					
					queue.add(String.valueOf(Interperter.VarTable.get(s).value));
					if(split.length==1)
					{
						return Interperter.VarTable.get(s).value;
					}
				}
				else {
					switch(s) {
				    case "/":
				    case "*":
				    case "(":
				        stack.push(s);
				        break;
				    case "+":
				    case "-":
				    	while (!stack.empty() && (!stack.peek().equals("("))){
				    		queue.add(stack.pop());
				    	}
				        stack.push(s);
				        break;
				    case ")":
				    	while (!stack.peek().equals("(")){
				    		queue.add(stack.pop());
				    	}
				    	stack.pop();
				        break;
				        
				    default :
				    	throw new Exception("invalid Expression");
					}
				}
			}
		}
		while(!stack.isEmpty()){
			queue.add(stack.pop());
		}
		
		for(String str : queue) {
			if (isDouble(str)){
				stackExp.push(new Number(Double.parseDouble(str)));
			}
			else{
				Expression right = stackExp.pop();
				Expression left = stackExp.pop();
				
				switch(str) {
			    case "/":
			    	stackExp.push(new DivideExpression(left, right));
			        break;
			    case "*":
			    	stackExp.push(new MultiplicationExpression(left, right));
			        break;
			    case "+":
			    	stackExp.push(new PlusExpression(left, right));
			        break;
			    case "-":
			    	stackExp.push(new MinusExpression(left, right));
			        break;
				}
			}
		}
	
		return Math.floor((stackExp.pop().calculate() * 1000)) /1000;
	}
}

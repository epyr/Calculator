import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Calculatorv2 {
	
	String operators[] = {"+", "-", "*", "/",};
	HashMap<String, Integer> precedence;
	ArrayList<String> postfix;
	
	public boolean isOp(String token) {
		
		
		for (String str : operators) {
			
			if (str.equals(token)) {
				
				return true;
			}
		}
		
		return false;
		
	}
			
	public boolean hasPrecedence(String op1, String op2) {
		
		if(precedence.get(op1) >= precedence.get(op2)) {
			
			return true;
			
		}
		else return false;
		
	}
	
	public Integer evaluate() {
		
		Stack<Integer> stack = new Stack<Integer>();
		
		Integer temp;
		Integer result;
		for (String token : postfix) {
			
			if (! isOp(token)) {
				
				temp = Integer.parseInt(token);
				stack.push(temp);
			}
			
			if (isOp(token)) {
				
				Integer operandA = stack.pop();
				Integer operandB = stack.pop();
				
				switch (token) {
				
				
				case "+":
					result = operandB + operandA;
					break;
				
				case "-":
					result =  operandB - operandA;
					break;
					
				case "*":
					result = operandB * operandA;
					break;
					
				case "/":
					result =  operandB / operandA;
					break;
		
					
				default:
					result =  null;
				}
				
				stack.push(result);
			}
		}
		return(stack.pop());
	}
		
	
//		
//		
//		
//		Integer value = null;
//		
		
	
	public void getPostfix(String expr) {
		
		Stack<String> stack = new Stack<String>();
		
		expr = expr.trim();
		
		String[] tokens = expr.split(" ");
		
	
		
		for (String token : tokens) {
			
			if ( ! isOp(token) && ! token.equals("(") && ! token.equals(")")) {
				
				postfix.add(token);
			}
			
			if (token.equals("(")) {
				
				stack.push(token);
			}
									
			if (token.equals(")")) {
				
				while( ! stack.empty() && ! stack.peek().equals("(")) {
					
					postfix.add(stack.pop());
					
				}
				
				if (stack.peek().equals("(")){
					
					stack.pop();
				}
				
			}
			
			if (isOp(token)){
				
				if( stack.empty() || stack.peek().equals("(")) {
				
					stack.push(token);
			}
			
				else {
				
					while(! stack.empty() &&  ! stack.peek().equals("(") 
						 && hasPrecedence(stack.peek(), token)) {
					
					postfix.add(stack.pop());
					
					}
					stack.push(token);
			  }
		}
			
	}
				
	while(!stack.isEmpty()){
		
		postfix.add(stack.pop());
			
		}
		return;
	}
	
	public Integer calculate(String expr) {
		
		getPostfix(expr);
		return evaluate();
	}
	
	public Calculatorv2(){
		
		
		postfix = new ArrayList<String>();
		precedence = new HashMap<String, Integer>();
		precedence.put("+", 1);
		precedence.put("-", 1);
		precedence.put("/", 2);
		precedence.put("*", 2);
		
		 
	}

	public static void main(String[] args) {
		
		String sample =  "( 2 * 6 / 3 * 2 ) * 5 * ( 10 - 12 ) + 169 - 4 * 5 - 9";
		
		Calculatorv2 calc = new Calculatorv2();
		System.out.println(calc.calculate(sample));
	}
	
}



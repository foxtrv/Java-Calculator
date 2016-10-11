/******************* 
Author: Trevor Fox
Date: 10/10/16
Assignment: Lab 5
********************/

import java.util.Stack;

public class CalculatorEngine {

	// private variables
	public String _mem = "";

	// Button: M+, store current value into memory
	public void MPLUS(String x){ _mem = x; }

	// Button: MR, return stored value from memory
	public String MR(){ return _mem; }

	// Button: MC, return stored value from memory
	public void MC(){ _mem = ""; }

	public static double compute(String expression){
		// Convert expression into a character array
		char[] tokens = expression.toCharArray();

		// Stack for numbers: 'values'
		Stack<Double> values = new Stack<Double>();

		// Stack for Operators: 'ops'
		Stack<Character> ops = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++){
			// Current token is a number, push it into the stack
			if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'){
				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in the number 
				// and it may include a decimal point
				while ((i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') 
					|| (i < tokens.length && tokens[i] == '.'))
					sbuf.append(tokens[i++]);
				values.push(Double.parseDouble(sbuf.toString()));
				i--;
			}
			// Current token is an opening brace, push it to 'ops'
			else if (tokens[i] == '(')
				ops.push(tokens[i]);

			// Closing brace encountered, solve entire brace
			else if (tokens[i] == ')'){
				while (ops.peek() != '(')
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
			}

			// Current token is an operator.
			else if (tokens[i] == '+' || tokens[i] == '-' || 
				 tokens[i] == '*' || tokens[i] == '/') {
				// While top of 'ops' has same or greater precedence to current
				// token (which is an operator), apply operator on top of 'ops'
				// to top two elements in values stack
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
						values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				// Push current token to 'ops'
				ops.push(tokens[i]);
			}
	
			// if digit follows directly after a ')' or right before a '(', 
			// there is a syntax error
			if (tokens[i] >= '0' && tokens[i] <= '9' && i > 0 && tokens[i-1] == ')')
				throw new UnsupportedOperationException("SYNTAX ERROR");
			if (tokens[i] >= '0' && tokens[i] <= '9' && i < tokens.length-1 && 
				tokens[i+1] == '(')
				throw new UnsupportedOperationException("SYNTAX ERROR");
		}

		// Entire expression has been parsed at this point, apply remaining ops to remaining values
		while (!ops.empty())
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));

		// Top of 'values' contains result, return it
		return values.pop();
	}

	// Returns true if 'op2' has higher or same precedence as 'op1', otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2){
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		return true;
	}

	// A utility method to apply an operator 'op' on operands 'a' and 'b'. Return the result.
	public static double applyOp(char op, double b, double a){
		switch (op){
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				if (b == 0){
					throw new
						UnsupportedOperationException("DIVISION BY ZERO");
				}
				else return a / b;
		}
		return 0;
	}

	// Driver method to test above methods
	public static void main(String[] args){
		CalculatorEngine c = new CalculatorEngine();
		System.out.println(); 
		System.out.println("23.45+31.4*2"); 
		System.out.println(c.compute("23.45+31.4*2"));
	}
};
















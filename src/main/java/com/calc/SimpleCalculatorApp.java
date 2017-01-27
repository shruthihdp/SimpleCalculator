package com.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SimpleCalculatorApp {

	public static void main(String a[]) {
		new SimpleCalculatorApp().start();
	}

	/**
	 * This is the starting point of the program. It receives input from the
	 * command line and process them further and sends to calculate function. At
	 * the end this method displays the calculated result.
	 */
	public void start() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("==========================================");
		System.out.println("   Please input expression to calculate :");
		System.out.println("==========================================");

		String line = scanner.nextLine();

		System.out.println("==========================");
		System.out.println("   Entered Value : " + line);
		System.out.println("==========================");

		List<String> postfixString = SimpleCalculatorApp.infixToPostfixConvert(line);
		Calculator calculator = Calculator.getInstance();
		calculator.setCurrent(new BigDecimal(0));

		calculate(calculator, postfixString);

		System.out.println("==========================");
		System.out.println("   Result is " + calculator.getCurrent());
		System.out.println("==========================");
		scanner.close();
	}

	/**
	 * This method keeps a stack to process postfix version of the input and
	 * execute the right command implementation. Currently this method supports
	 * for arithmetic command calculations only.
	 * 
	 * @param Cal
	 * 
	 * @param postFix
	 */
	public void calculate(Calculator cal, List<String> postFix) {

		Stack<BigDecimal> stack = new Stack<BigDecimal>();

		for (int i = 0; i < postFix.size(); i++) {

			String next = postFix.get(i);

			if (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/")) {
				ArithmeticCalculator cmd = new ArithmeticCalculator(next.charAt(0), stack.pop(), stack.pop(), cal);
				Invoker invoker = new Invoker();
				invoker.compute(cmd);
				stack.push(cal.getCurrent());
			} else {
				stack.push(new BigDecimal(next.trim()));
			}
		}
	}

	/**
	 * This method converts the infix into postfix in order to proceed with the
	 * calculation.
	 * 
	 * @param input
	 */
	public static List<String> infixToPostfixConvert(String input) {

		int priority = 0;
		String postfixBuffer = "";
		Stack<Character> stack = new Stack<Character>();
		List<String> postfixArray = new ArrayList<String>();

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

				if (postfixBuffer.length() > 0) {
					postfixArray.add(postfixBuffer);
				}
				postfixBuffer = "";
				// check the precedence
				if (stack.size() <= 0)
					stack.push(ch);
				else {
					Character chTop = (Character) stack.peek();
					if (chTop == '*' || chTop == '/')
						priority = 1;
					else
						priority = 0;
					if (priority == 1) {
						if (ch == '+' || ch == '-') {
							postfixArray.add(String.valueOf(stack.pop()));
							i--;
						} else { // Same
							postfixArray.add(String.valueOf(stack.pop()));
							i--;
						}
					} else {
						if (ch == '+' || ch == '-') {
							postfixArray.add(String.valueOf(stack.pop()));
							stack.push(ch);
						} else
							stack.push(ch);
					}
				}
			} else {
				postfixBuffer += ch;
			}
		}

		postfixArray.add(postfixBuffer);
		int len = stack.size();
		for (int j = 0; j < len; j++)
			postfixArray.add(stack.pop().toString());
		System.out.println("   PostfixArray" + postfixArray);
		return postfixArray;
	}
}
package com.calc;

import java.math.BigDecimal;

public class Calculator {

	private static Calculator calculator;
	private BigDecimal current = new BigDecimal(0);

	private Calculator() {
	}

	public static Calculator getInstance() {
		if (calculator == null) {
			calculator = new Calculator();
		}
		return calculator;
	}

	/**
	 * This method calculate current value for any number of calculation
	 * operations. Currently following operations are supported +,-,*,/
	 * 
	 * @param operator
	 * 
	 * @param leftOperand
	 * 
	 * @param rightOperand
	 * 
	 */
	public void operation(char operator, BigDecimal leftOperand, BigDecimal rightOperand) {
		switch (operator) {
		case '+':
			current = leftOperand.add(rightOperand);
			break;
		case '-':
			current = rightOperand.subtract(leftOperand);
			break;
		case '/':
			current = rightOperand.divide(leftOperand);
			break;
		case '*':
			current = leftOperand.multiply(rightOperand);
			break;
		default:
			break;
		}
	}

	public BigDecimal getCurrent() {
		return current;
	}

	public void setCurrent(BigDecimal current) {
		this.current = current;
	}
}
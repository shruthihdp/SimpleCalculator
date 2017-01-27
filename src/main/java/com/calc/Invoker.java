package com.calc;

public class Invoker {

	public void compute(Command command) {
		command.calculate();
	}
}
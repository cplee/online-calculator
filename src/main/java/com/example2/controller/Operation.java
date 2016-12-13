package com.example2.controller;

public class Operation {
	private final double rvalue;
	private final Operator operator;

	Operation(Operator operator, double rvalue) {
		this.operator = operator;
		this.rvalue = rvalue;
	}

	public double getRvalue() {
		return rvalue;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public double apply(double lvalue) {
		return operator.apply(lvalue,rvalue);
	}
}
package com.example2.controller;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum Operator implements BinaryOperator<Double>{
	Addition("+", (l,r) -> l+r);
	
	private String operator;
	private BinaryOperator<Double> computation;

	Operator(String operator, BinaryOperator<Double> computation) {
		this.operator = operator;
		this.computation = computation;
	}
	
	@Override
	public Double apply(Double lvalue, Double rvalue) {
		return this.computation.apply(lvalue,rvalue);
	}
	
	public Operation operation(Double rvalue) {
		return new Operation(this, rvalue);
	}

	public static Operator valueOfOperator(String operator) {
		return Arrays
				.stream(values())
				.filter(o -> o.operator.equals(operator))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown operator"));
	}
}

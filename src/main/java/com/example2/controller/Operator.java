package com.example2.controller;

import java.util.function.BinaryOperator;

public enum Operator implements BinaryOperator<Double>{
	Addition((l,r) -> l+r);
	
	private BinaryOperator<Double> op;

	Operator(BinaryOperator<Double> op) {
		this.op = op;
	}
	
	@Override
	public Double apply(Double lvalue, Double rvalue) {
		return this.op.apply(lvalue,rvalue);
	}
	
	public Operation operation(Double rvalue) {
		return new Operation(this, rvalue);
	}
}

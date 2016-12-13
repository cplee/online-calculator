package com.example2.controller;

public class OperationAddition implements Operation {

	private double rvalue;

	public OperationAddition(double rvalue) {
		this.rvalue = rvalue;
	}

	@Override
	public double apply(double lvalue) {
		return lvalue + rvalue;
	}

}

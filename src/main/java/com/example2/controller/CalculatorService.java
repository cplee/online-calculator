package com.example2.controller;

import java.util.Arrays;

public class CalculatorService {

	public double calculate(Operation... operations) {
		return Arrays.stream(operations)
				.reduce(0d, 
						(lvalue, operation) -> operation.apply(lvalue), 
						(lvalue,rvalue) -> rvalue);
	}

}

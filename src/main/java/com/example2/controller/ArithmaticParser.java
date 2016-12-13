package com.example2.controller;

import java.util.ArrayList;
import java.util.List;

public class ArithmaticParser {
	private static final String REGEX = "(?<=op)|(?=op)".replace("op", "[-+*/^]");

	public Operation[] parse(String input) {
		String[] inputParts = input.split(REGEX);
		
		List<Operation> operations = new ArrayList<>();
		
		operations.add(Operator.valueOfOperator("+").operation(parseOperand(inputParts[0])));
		
		for(int i=1; i<inputParts.length; i++) {
			operations.add(Operator.valueOfOperator(inputParts[i++]).operation(parseOperand(inputParts[i])));
		}
		

		return operations.toArray(new Operation[operations.size()]);
	}
	
	private Double parseOperand(String operand) {
		if(operand.trim().isEmpty()) {
			throw new IllegalArgumentException("Invalid operand: "+operand);
		}
		return Double.parseDouble(operand);
	}

}

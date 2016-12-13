package com.example.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorService {

	public String calculate(String input, int scale) {
		// Parse input string
		String regex = "(?<=op)|(?=op)".replace("op", "[-+*/^]");
		String[] inputParts = input.split(regex);

		// Validate parts of input
		if (inputParts.length % 2 == 0) {
			throw new IllegalArgumentException("Invalid input");
		}

		// Parse first value
		double result;
		try {
			result = Double.parseDouble(inputParts[0]);
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid input");
		}

		// Perform calculation
		for (int i = 1; i < inputParts.length; i++) {
			String operator = inputParts[i];
			double operand;
			try {
				operand = Double.parseDouble(inputParts[++i]);
			} catch (NumberFormatException ex) {
				throw new IllegalArgumentException("Invalid input");
			}

			if ("+".equals(operator)) {
				result += operand;
			} else if ("-".equals(operator)) {
				result -= operand;
			} else if ("*".equals(operator)) {
				result *= operand;
			} else if ("/".equals(operator)) {
				if (operand == 0) {
					throw new IllegalArgumentException("Invalid input");
				}
				result /= operand;
			} else if ("^".equals(operator)) {
				result = Math.pow(result, operand);
			} else {
				throw new IllegalArgumentException("Invalid input");
			}
		}
		
		// return result
		RoundingMode roundingMode = RoundingMode.HALF_UP;
		BigDecimal scaledResult = BigDecimal.valueOf(result).setScale(scale, roundingMode);
		
		return scaledResult.toString();

	}
}
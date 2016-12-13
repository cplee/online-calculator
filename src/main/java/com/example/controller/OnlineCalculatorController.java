package com.example.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineCalculatorController {

	@RequestMapping(value = "calculation", method = RequestMethod.POST)
	public ResponseEntity<String> evaluate(
			@RequestParam(defaultValue = "0") int scale, 
			@RequestBody String input) {
		// Parse input string
		String regex = "(?<=op)|(?=op)".replace("op", "[-+*/^]");
		String[] inputParts = input.split(regex);

		// Validate parts of input
		if (inputParts.length % 2 == 0) {
			return ResponseEntity.badRequest().body("");
		}

		// Parse first value
		double result;
		try {
			result = Double.parseDouble(inputParts[0]);
		} catch (NumberFormatException ex) {
			return ResponseEntity.badRequest().body("");
		}

		// Perform calculation
		for (int i = 1; i < inputParts.length; i++) {
			String operator = inputParts[i];
			double operand;
			try {
				operand = Double.parseDouble(inputParts[++i]);
			} catch (NumberFormatException ex) {
				return ResponseEntity.badRequest().body("");
			}

			if ("+".equals(operator)) {
				result += operand;
			} else if ("-".equals(operator)) {
				result -= operand;
			} else if ("*".equals(operator)) {
				result *= operand;
			} else if ("/".equals(operator)) {
				if (operand == 0) {
					return ResponseEntity.badRequest().body("");
				}
				result /= operand;
			} else if ("^".equals(operator)) {
				result = Math.pow(result, operand);
			} else {
				return ResponseEntity.badRequest().body("");
			}
		}

		// return result
		RoundingMode roundingMode = RoundingMode.HALF_UP;
		return ResponseEntity.ok(BigDecimal.valueOf(result).setScale(scale, roundingMode).toString());
	}
}
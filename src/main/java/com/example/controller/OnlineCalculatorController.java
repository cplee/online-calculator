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
	private RoundingMode roundingMode = RoundingMode.HALF_UP;
	private CalculatorService calculatorService = new CalculatorService();

	@RequestMapping(value = "calculation", method = RequestMethod.POST)
	public ResponseEntity<String> evaluate(
			@RequestParam(defaultValue = "0") int scale, 
			@RequestBody String input) {
		
		try {
			double result = calculatorService.calculate(input);
			BigDecimal scaledResult = BigDecimal.valueOf(result).setScale(scale, roundingMode);
			return ResponseEntity.ok(scaledResult.toString());
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("");
		}
		
	}
}
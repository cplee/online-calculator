package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineCalculatorController {
	private CalculatorService calculatorService = new CalculatorService();

	@RequestMapping(value = "calculation", method = RequestMethod.POST)
	public ResponseEntity<String> evaluate(
			@RequestParam(defaultValue = "0") int scale, 
			@RequestBody String input) {
		
		try {
			String result = calculatorService.calculate(input, scale);
			return ResponseEntity.ok(result);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("");
		}
		
	}
}
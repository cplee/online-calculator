package com.example2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineCalculatorController {

	@Autowired
	private ResponseFormatter responseFormatter;

	@Autowired
	private CalculatorService calculatorService;

	@Autowired
	private ArithmaticParser arithmaticParser;

	@RequestMapping(value = "calculation", method = RequestMethod.POST)
	public ResponseEntity<String> evaluate(@RequestBody String input) {
		// parse input
		Operation[] operations = arithmaticParser.parse(input);

		// calculate result
		int result = calculatorService.calculate(operations);

		// format response
		String response = responseFormatter.format(result);

		return ResponseEntity.ok(response);
	}
}

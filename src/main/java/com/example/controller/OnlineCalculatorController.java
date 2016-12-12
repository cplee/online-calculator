package com.example.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineCalculatorController {

    @RequestMapping(value = "calculation", method = RequestMethod.POST)
    public ResponseEntity<Number> evaluate(@RequestBody String input) {
    	// Parse input string
        String regex = "(?<=op)|(?=op)".replace("op", "[-+*/]");
        String[] inputParts = input.replaceAll("\\s", "").split(regex);
        
        // Validate parts of input
        if(inputParts.length % 2 == 0) {
        	return ResponseEntity.badRequest().body(Double.NaN);
        }
        
        // Validate first value
        double result;
        try {
        	result = Double.parseDouble(inputParts[0]);
        } catch (NumberFormatException ex) {
        	return ResponseEntity.badRequest().body(Double.NaN);
        }
    	
    	// Perform calculation
    	for(int i=1; i<inputParts.length; i++) {
    		String operator = inputParts[i];
    		double operand;
    		try {
    			operand = Double.parseDouble(inputParts[++i]);
	        } catch (NumberFormatException ex) {
	        	return ResponseEntity.badRequest().body(Double.NaN);
	        }
    		
    		if("+".equals(operator)) {
    			result += operand;
    		} else if("-".equals(operator)) {
    			result -= operand;
    		} else if("*".equals(operator)) {
    			result *= operand;
    		} else if("/".equals(operator)) {
    			if(operand == 0) {
    				return ResponseEntity.badRequest().body(Double.NaN);
    			}
    			result /= operand;
    		}
    	}
    	
    	// return result
    	return ResponseEntity.ok(result);
    }
}
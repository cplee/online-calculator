package com.example.controller;

import javax.validation.ValidationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineCalculatorController {

    @RequestMapping(value = "calculation", method = RequestMethod.POST)
    public ResponseEntity<Number> evaluate(@RequestBody String input) {
    	// Parse string
        String regex = "(?<=op)|(?=op)".replace("op", "[-+*/]");
        String[] inputParts = input.replaceAll("\\s", "").split(regex);
        
        // Assert valid number of parts
        if(inputParts.length % 2 == 0) {
        	return ResponseEntity.badRequest().body(0);
        }
    	
    	// Perform calculation
    	double result = Double.parseDouble(inputParts[0]);
    	
    	for(int i=1; i<inputParts.length; i++) {
    		String operator = inputParts[i];
    		double operand = Double.parseDouble(inputParts[++i]);
    		
    		if("+".equals(operator)) {
    			result += operand;
    		} else if("-".equals(operator)) {
    			result -= operand;
    		} else if("*".equals(operator)) {
    			result *= operand;
    		} else if("/".equals(operator)) {
    			if(operand == 0) {
    				return ResponseEntity.badRequest().body(0);
    			}
    			result /= operand;
    		}
    	}
    	
    	// return result
    	return ResponseEntity.ok(result);
    }
}
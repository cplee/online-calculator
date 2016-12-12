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

	@RequestMapping(value = "calculation", method = RequestMethod.POST)
	  public ResponseEntity<String> evaluate(@RequestBody String input) {
		String response = responseFormatter.format(0);
	    return ResponseEntity.ok(response);
	  }
}

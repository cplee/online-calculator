package com.example2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineCalculatorController {
	  @RequestMapping(value = "calculation", method = RequestMethod.POST)
	  public ResponseEntity<String> evaluate() {
	    return ResponseEntity.ok("0");
	  }
}

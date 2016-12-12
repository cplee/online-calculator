package com.example2.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorServiceTest {
	
	private CalculatorService calculatorService;

	@Before
	public void setup() {
		this.calculatorService = new CalculatorService();
	}

	@Test
	public void testSimpleAddition() {
		int result = calculatorService.calculate();
		Assert.assertThat(result, Matchers.is(0));
	}

}

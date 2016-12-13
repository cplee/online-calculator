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
	public void testNoOp() {
		double result = calculatorService.calculate();
		Assert.assertThat(result, Matchers.is(0.0));
	}
	
	@Test
	public void testSingleOperation() {
		double result = calculatorService.calculate(Operator.Addition.operation(5.0));
		Assert.assertThat(result, Matchers.is(5.0));
	}
	
	@Test
	public void testMultipleOperation() {
		double result = calculatorService.calculate(Operator.Addition.operation(5.0), Operator.Addition.operation(6.5));
		Assert.assertThat(result, Matchers.is(11.5));
	}

}

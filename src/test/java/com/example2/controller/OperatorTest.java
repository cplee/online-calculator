package com.example2.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class OperatorTest {
	@Test
	public void testAddition() {
		Operation op = Operator.Addition.operation(10.0);
		
		Assert.assertThat(op.apply(0), Matchers.is(10.0));
		Assert.assertThat(op.apply(5), Matchers.is(15.0));
		Assert.assertThat(op.apply(-5), Matchers.is(5.0));
	}
	
	@Test
	public void testValueOfOperand() {
		Operator o = Operator.valueOfOperator("+");
		Assert.assertThat(o,Matchers.is(Operator.Addition));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testValueOfInvalidOperand() {
		Operator o = Operator.valueOfOperator("?");
	}

}

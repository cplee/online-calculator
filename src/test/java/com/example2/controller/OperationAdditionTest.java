package com.example2.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class OperationAdditionTest {
	@Test
	public void testSimple() {
		OperationAddition op = new OperationAddition(10);
		
		Assert.assertThat(op.apply(0), Matchers.is(10.0));
		Assert.assertThat(op.apply(5), Matchers.is(15.0));
		Assert.assertThat(op.apply(-5), Matchers.is(5.0));
	}

}

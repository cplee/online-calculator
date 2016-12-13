package com.example2.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArithmaticParserTest {
	private ArithmaticParser arithmaticParser;

	@Before
	public void setup() {
		this.arithmaticParser = new ArithmaticParser();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testParseEmptyInput() {
		this.arithmaticParser.parse("");
	}
	
	@Test
	public void testParseSingleInput() {
		Operation[] operations = this.arithmaticParser.parse("1");
		Assert.assertThat(operations.length, Matchers.is(1));
		Assert.assertThat(operations[0], Matchers.notNullValue());
		Assert.assertThat(operations[0].getOperator(), Matchers.is(Operator.Addition));
		Assert.assertThat(operations[0].getRvalue(), Matchers.is(1.0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseInvalidInput() {
		this.arithmaticParser.parse("1 + ");
	}
	
	@Test
	public void testParseSimpleInput() {
		Operation[] operations = this.arithmaticParser.parse("1 + 2");
		Assert.assertThat(operations.length, Matchers.is(2));
		Assert.assertThat(operations[0], Matchers.notNullValue());
		Assert.assertThat(operations[0].getOperator(), Matchers.is(Operator.Addition));
		Assert.assertThat(operations[0].getRvalue(), Matchers.is(1.0));
		Assert.assertThat(operations[1], Matchers.notNullValue());
		Assert.assertThat(operations[1].getOperator(), Matchers.is(Operator.Addition));
		Assert.assertThat(operations[1].getRvalue(), Matchers.is(2.0));
	}
	
	@Test
	public void testParseComplexInput() {
		Operation[] operations = this.arithmaticParser.parse("1 + 2 + 6");
		Assert.assertThat(operations.length, Matchers.is(3));
		Assert.assertThat(operations[0], Matchers.notNullValue());
		Assert.assertThat(operations[0].getOperator(), Matchers.is(Operator.Addition));
		Assert.assertThat(operations[0].getRvalue(), Matchers.is(1.0));
		Assert.assertThat(operations[1], Matchers.notNullValue());
		Assert.assertThat(operations[1].getOperator(), Matchers.is(Operator.Addition));
		Assert.assertThat(operations[1].getRvalue(), Matchers.is(2.0));
		Assert.assertThat(operations[2], Matchers.notNullValue());
		Assert.assertThat(operations[2].getOperator(), Matchers.is(Operator.Addition));
		Assert.assertThat(operations[2].getRvalue(), Matchers.is(6.0));
	}

}

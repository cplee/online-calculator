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

	@Test
	public void testParseSimpleInput() {
		Operation[] operations = this.arithmaticParser.parse("1 + 1");
		Assert.assertThat(operations.length, Matchers.is(2));
	}

}

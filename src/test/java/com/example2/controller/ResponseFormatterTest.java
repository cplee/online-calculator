package com.example2.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseFormatterTest {
	ResponseFormatter formatter;
	
	@Before
	public void setup() {
		formatter = new ResponseFormatter();
	}

	@Test
	public void testSimpleFormat() {
		String response = formatter.format(0);
		Assert.assertThat(response, Matchers.is("0"));
	}

}

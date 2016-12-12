package com.example2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(OnlineCalculatorController.class)
public class OnlineCalculatorControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	ResponseFormatter formatter;
	
	@MockBean
	CalculatorService calculator;
	
	@MockBean
	ArithmaticParser parser;
	
	@Before
	public void setup() {
		Mockito.when(parser.parse(Mockito.any())).thenReturn(null);
		Mockito.when(calculator.calculate(Mockito.any())).thenReturn(0);
		Mockito.when(formatter.format(0)).thenReturn("0");
	}
	
	@Test
	public void testComputeSimpleAddition() throws Exception {
		this.mvc
		.perform(post("/calculation").content("0 + 0"))
		.andExpect(status().isOk())
		.andExpect(content().string("0"));
	}
	
	@Test
	public void testRequireInput() throws Exception {
		this.mvc
		.perform(post("/calculation"))
		.andExpect(status().isBadRequest());
	}
}

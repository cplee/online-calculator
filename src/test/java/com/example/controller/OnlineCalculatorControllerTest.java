package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(OnlineCalculatorController.class)
public class OnlineCalculatorControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void testServiceRequiresBody() throws Exception {
		this.mvc
		.perform(post("/calculation"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testServiceRequiresValidInput() throws Exception {
		this.mvc
		.perform(post("/calculation").content("1 + "))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testSimpleAddition() throws Exception {
		this.mvc
		.perform(post("/calculation").content("0 + 0"))
		.andExpect(status().isOk())
		.andExpect(content().string("0"));
	}
	
	@Test
	public void testSimpleSubtraction() throws Exception {
		this.mvc
		.perform(post("/calculation").content("3 - 2"))
		.andExpect(status().isOk())
		.andExpect(content().string("1"));
	}
	
	@Test
	public void testSimpleMultiplication() throws Exception {
		this.mvc
		.perform(post("/calculation").content("2 * 4"))
		.andExpect(status().isOk())
		.andExpect(content().string("8"));
	}
	
	@Test
	public void testSimpleDivision() throws Exception {
		this.mvc
		.perform(post("/calculation").content("9 / 3"))
		.andExpect(status().isOk())
		.andExpect(content().string("3"));
	}

	@Test
	public void testDivideByZero() throws Exception {
		this.mvc
		.perform(post("/calculation").content("9 / 0"))
		.andExpect(status().isBadRequest());
	}
	

	@Test
	public void testComplexCalculation() throws Exception {
		this.mvc
		.perform(post("/calculation").content("3 + 5 - 2 * 4 / 8"))
		.andExpect(status().isOk())
		.andExpect(content().string("3"));
	}
	
	@Test
	public void testDoubleCalculationWithoutRounding() throws Exception {
		this.mvc
		.perform(post("/calculation").param("scale", "2").content("9.5 / 2"))
		.andExpect(status().isOk())
		.andExpect(content().string("4.75"));
	}
	
	@Test
	public void testDoubleCalculationWithRounding() throws Exception {
		this.mvc
		.perform(post("/calculation").content("9.5 / 2"))
		.andExpect(status().isOk())
		.andExpect(content().string("5"));
	}

}

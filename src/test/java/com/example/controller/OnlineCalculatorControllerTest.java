package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
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
	public void testParseRequiresBody() throws Exception {
		this.mvc
		.perform(post("/calculation"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testParseRequiresValidInput() throws Exception {
		this.mvc
		.perform(post("/calculation").content("1 + "))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testComputeSimpleAddition() throws Exception {
		this.mvc
		.perform(post("/calculation").content("0 + 0"))
		.andExpect(status().isOk())
		.andExpect(content().string("0"));
	}
	
	@Test
	public void testComputeSimpleSubtraction() throws Exception {
		this.mvc
		.perform(post("/calculation").content("3 - 2"))
		.andExpect(status().isOk())
		.andExpect(content().string("1"));
	}
	
	@Test
	public void testComputeSimpleMultiplication() throws Exception {
		this.mvc
		.perform(post("/calculation").content("2 * 4"))
		.andExpect(status().isOk())
		.andExpect(content().string("8"));
	}
	
	@Test
	public void testComputeSimpleDivision() throws Exception {
		this.mvc
		.perform(post("/calculation").content("9 / 3"))
		.andExpect(status().isOk())
		.andExpect(content().string("3"));
	}

	@Test
	public void testComputeDivideByZero() throws Exception {
		this.mvc
		.perform(post("/calculation").content("9 / 0"))
		.andExpect(status().isBadRequest());
	}
	

	@Test
	public void testComputeComplexCalculation() throws Exception {
		this.mvc
		.perform(post("/calculation").content("3 + 5 - 2 * 4 / 8"))
		.andExpect(status().isOk())
		.andExpect(content().string("3"));
	}
	
	@Test
	public void testRespondWithRoundingScale2() throws Exception {
		this.mvc
		.perform(post("/calculation").param("scale", "2").content("9.5 / 2"))
		.andExpect(status().isOk())
		.andExpect(content().string("4.75"));
	}
	
	@Test
	public void testRespondWithRoundingDefaultScale() throws Exception {
		this.mvc
		.perform(post("/calculation").content("9.5 / 2"))
		.andExpect(status().isOk())
		.andExpect(content().string("5"));
	}
	
	@Test
	@Ignore
	public void testComputePowerIrrational() throws Exception {
		this.mvc
		.perform(post("/calculation").param("scale","30").content("2 ^ .5"))
		.andExpect(status().isOk())
		.andExpect(content().string("1.414213562373095048801688724209"));
	}

}

package com.example2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	public void testComputeSimpleAddition() throws Exception {
		this.mvc
		.perform(post("/calculation").content("0 + 0"))
		.andExpect(status().isOk())
		.andExpect(content().string("0"));
	}
}

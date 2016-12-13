package com.example2.controller;

public class ArithmaticParser {
	private static final String REGEX = "(?<=op)|(?=op)".replace("op", "[-+*/^]");

	public Operation[] parse(String input) {
		String[] inputPargs = input.split(REGEX);

		return new Operation[2];
	}

}

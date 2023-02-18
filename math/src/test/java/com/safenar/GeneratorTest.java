package com.safenar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {
	
	@Test
	public void hash_forPreMadeGenerator() {
		var gen = Generators.POW;
		int actual = gen.hash();
		assertEquals(-640718498, actual);
	}
	@Test
	public void hash_forCustomGenerator() {
		Generator gen = x -> 2 * x;
		int hash = gen.hash();
		assertEquals(167235456, hash);
	}
	
	@Test
	public void string_forPreMadeGenerator() {
		var gen = Generators.POW;
		String actual = gen.string();
		assertEquals("""
				-5 -> 25;
				-4 -> 16;
				-3 -> 9;
				-2 -> 4;
				-1 -> 1;
				0 -> 0;
				1 -> 1;
				2 -> 4;
				3 -> 9;
				4 -> 16;
				5 -> 25;
				""", actual);
	}
	@Test
	public void string_forCustomGenerator() {
		Generator gen = x -> x * 2;
		String expected = """
				-5 -> -10;
				-4 -> -8;
				-3 -> -6;
				-2 -> -4;
				-1 -> -2;
				0 -> 0;
				1 -> 2;
				2 -> 4;
				3 -> 6;
				4 -> 8;
				5 -> 10;
				""";
		String actual = gen.string();
		assertEquals(expected, actual);
	}
}
package com.safenar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorsTest {
	
	@Test
	public void fromGenerator_forNotInGenerators() {
		Generator gen = x -> x + x;
		Generators actual = Generators.fromGenerator(gen);
		assertEquals(Generators.NULL, actual);
	}
	@Test
	public void fromGenerator_forInGenerator() {
		Generator gen = x -> x * x;
		var actual = Generators.fromGenerator(gen);
		assertEquals(Generators.POW, actual);
	}
	@Test
	public void fromGenerator_forNull() {
		Generator gen = null;
		var actual = Generators.fromGenerator(gen);
		assertEquals(Generators.NULL, actual);
	}
}
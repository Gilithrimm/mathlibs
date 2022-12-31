package com.safenar.math;

import org.junit.jupiter.api.Test;

import static com.safenar.math.Generators.*;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratorsTest {
	
	@Test
	public void fromGenerator_forNotInGenerators() {
		Generator gen = x -> x + x;
		Generators actual = fromGenerator(gen);
		assertEquals(NULL, actual);
	}
	@Test
	public void fromGenerator_forInGenerator() {
		Generator gen = x -> x * x;
		var actual = fromGenerator(gen);
		assertEquals(POW, actual);
	}
	@Test
	public void fromGenerator_forNull() {
		Generator gen = null;
		var actual = fromGenerator(gen);
		assertEquals(NULL, actual);
	}
}
package com.safenar;

import org.junit.jupiter.api.Test;


import static com.safenar.MathUtil.isNatural;
import static com.safenar.MathUtil.isPrime;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathUtilTest {
	//test naming convention as of 2X2022: {testedMethod}_for{TestCase}()

	@Test
	public void isNatural_forNaturalNumber() {
		int number = 1;
		boolean actual = isNatural(number);
		assertTrue(actual);
	}
	@Test
	public void isNatural_forNonNaturalNumber() {
		int number = -1;
		boolean actual = isNatural(number);
		assertFalse(actual);
	}
	@Test
	public void isNatural_forZero() {
		int number = 0;
		boolean actual = isNatural(number);
		assertTrue(actual);
	}

	@Test
	public void isPrime_forPrime() {
		int prime = 11;
		boolean actual = isPrime(prime);
		assertTrue(actual);
	}
	@Test
	public void isPrime_forNonPrime() {
		int nonPrime = 327;
		boolean actual = isPrime(nonPrime);
		assertFalse(actual);
	}
	@Test
	public void isPrime_forOne() {
		int one = 1;
		boolean actual = isPrime(one);
		assertFalse(actual);
	}
	@Test
	public void isPrime_forZero() {
		int zero = 0;
		boolean actual = isPrime(zero);
		assertFalse(actual);
	}
	@Test
	public void isPrime_forNegative() {
		//we can either 1) treat negative values with abs()
		//or 2) not pass them through
		int negative = -5;
		boolean actual = isPrime(negative);
		assertFalse(actual);//I'm doing 2)
	}
}
package com.safenar.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static com.safenar.math.Generators.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.safenar.math.MathUtil.*;

class MathUtilTest {
	//test naming convention as of 2X2022: {testedMethod}_for{TestCase}()

	@Test
	void isNatural_forNaturalNumber() {
		int number = 1;
		boolean actual = isNatural(number);
		assertTrue(actual);
	}
	@Test
	void isNatural_forNonNaturalNumber() {
		int number = -1;
		boolean actual = isNatural(number);
		assertFalse(actual);
	}
	@Test
	void isNatural_forZero() {
		int number = 0;
		boolean actual = isNatural(number);
		assertTrue(actual);
	}

	@Test
	void isPrime_forPrime() {
		int prime = 11;
		boolean actual = isPrime(prime);
		assertTrue(actual);
	}
	@Test
	void isPrime_forNonPrime() {
		int nonPrime = 327;
		boolean actual = isPrime(nonPrime);
		assertFalse(actual);
	}
	@Test
	void isPrime_forOne() {
		int one = 1;
		boolean actual = isPrime(one);
		assertFalse(actual);
	}
	@Test
	void isPrime_forZero() {
		int zero = 0;
		boolean actual = isPrime(zero);
		assertFalse(actual);
	}
	@Test
	void isPrime_forNegative() {
		//we can either 1) treat negative values with abs()
		//or 2) not pass them through
		int negative = -5;
		boolean actual = isPrime(negative);
		assertFalse(actual);//I'm doing 2)
	}

	@Test
	void isInSequence_forNullSequence() {
		Sequence seq = null;
		int needle = 0;
		boolean actual = isInSequence(seq, needle);
		assertFalse(actual);
	}
	@Test
	void isInSequence_forNullGenerator() {
		Sequence seq = new Sequence(0, 10, null);
		int zero = 0;
		boolean actualZero = isInSequence(seq, zero);
		assertTrue(actualZero);
		int nonZero = 1;
		boolean actual = isInSequence(seq, nonZero);
		assertFalse(actual);
	}
	//what if seq is empty eg. (generator=ANY)
	// &(getLength()=0
	// <=>(min=-1^max=-1)
	// &(min=0^max=0))
	//a^b<=>(a|b) & !(a&b) eg ^ is XOR
	@Test
	void isInSequence_forEmptySequence() {
		Sequence seq = new Sequence(0, -1, SELF);
		boolean actual = isInSequence(seq, 1);
		assertFalse(actual);
	}
	@Test
	void isInSequence_forInSequence() {
		Sequence sequence = new Sequence(0, 10, POW);
		int needle = 25;
		boolean actual = isInSequence(sequence, needle);
		assertTrue(actual);

	}
	@Test
	void isInSequence_forNotInSequence() {
		Sequence sequence = new Sequence(0, 10, POW);
		int needle = 500;
		boolean actual = isInSequence(sequence, needle);
		assertFalse(actual);
	}

	@Test
	void sumOf_forNullSequence() {
		Sequence seq = null;
		int actual = sumOf(seq);
		assertEquals(-1, actual);
	}
	@Test
	void sumOf_forEmptySequence() {
		Sequence seq = new Sequence(0, -1, ABS);
		int actual = sumOf(seq);
		assertEquals(0, actual);
	}
	@Test
	void sumOf_forNullGenerator() {
		Sequence seq = new Sequence(0, 10, null);
		int actual = sumOf(seq);
		assertEquals(0, actual);
	}
	@Test
	void sumOf_forPowersFrom1To10() {
		Sequence seq = new Sequence(1, 10, POW);
		int actual = sumOf(seq);
		assertEquals(385, actual);
	}
	@Test
	void sumOf_for11FibonacciFrom1To10() {
		Sequence seq = new Sequence(1, 10, FIBONACCI);
		int actual = sumOf(seq);
		//sumOf(seq(1,10,fibGen(1,1))==fibGen(1,1).gen(12)-1
		//sumOf(seq(1,n,fibGen(1,1))==fibGen(1,1).gen(n+2)-1
		assertEquals(143, actual);
	}

	@Test
	void productOf_forNullSequence() {
		Sequence seq = null;
		long actual = productOf(seq);
		assertEquals(-1, actual);
	}
	@Test
	void productOf_forNullGenerator() {
		Sequence seq = new Sequence(0, 10, null);
		long actual = productOf(seq);
		assertEquals(0, actual);
	}
	@Test
	void productOf_forEmptySequence() {
		Sequence seq = new Sequence(0, -1, NEG);
		int actual = (int) productOf(seq);
		assertEquals(1, actual);
	}
	@Test
	void productOf_forPowersFrom1To10(){
		Sequence seq=new Sequence(1,10,POW);
		long actual= productOf(seq);
		assertEquals(13168189440000L,actual);
	}

	@Test
	void averageOf_forNullSequence() {
		Sequence seq = null;
		int actual = averageOf(seq);
		assertEquals(-1, actual);
	}
	@Test
	void averageOf_forNullGenerator() {
		Sequence seq = new Sequence(0, 10, null);
		int actual = averageOf(seq);
		assertEquals(0, actual);
	}
	@Test
	void averageOf_forEmptySequence() {
		Sequence seq = new Sequence(0, -1, FIBONACCI);
		Executable actual = () -> averageOf(seq);
		assertThrows(ArithmeticException.class, actual);
	}
	@Test
	void averageOf_forPowersFrom1To10() {
		Sequence sequence=new Sequence(1,10,POW);
		long actual= averageOf(sequence);
		assertEquals(38L,actual);
	}

	@Test
	void medianOf_forNullSequence() {
		Sequence seq = null;
		long actual = medianOf(seq);
		assertEquals(-1, actual);
	}
	@Test
	void medianOf_forNullGenerator() {
		Sequence seq = new Sequence(0, 10, null);
		long actual = medianOf(seq);
		assertEquals(0, actual);
	}
	@Test
	void medianOf_forEmptySequence() {
		Sequence seq = new Sequence(0, -1, POW);
		Executable actual = () -> medianOf(seq);
		assertThrows(ArrayIndexOutOfBoundsException.class, actual);
	}
	@Test
	void medianOf_forEvenLength(){
		Sequence seq=new Sequence(1,10,POW);
		long actual= medianOf(seq);
		assertEquals(30L,actual);
	}
	@Test
	void medianOf_forOddLength(){
		Sequence seq=new Sequence(0,10,POW);
		long median = medianOf(seq);
		assertEquals(25L,median);
	}

	@Test
	void modeOf_forNullSequence() {
		Sequence seq = null;
		int actual = modeOf(seq);
		assertEquals(-1, actual);
	}
	@Test
	void modeOf_forNullGenerator() {
		Sequence seq = new Sequence(0, 10, null);
		int actual = modeOf(seq);
		assertEquals(0, actual);
	}
	@Test
	void modeOf_forEmptySequence() {
		Sequence seq = new Sequence(0, -1, NULL);
		int actual = modeOf(seq);
		assertEquals(0, actual);
	}
	@Test
	void modeOf_forEvenlyCommonNumbers(){
		Sequence seq=new Sequence(1,10,POW);
		int mode = modeOf(seq);
		assertEquals(1,mode);//1st element of a seq
	}
	@Test
	void modeOf_forMostCommonNumber(){
		Sequence seq=new Sequence(1,10,x->x%2==0?-284:x);
		int mode= modeOf(seq);
		assertEquals(-284,mode);
	}
}
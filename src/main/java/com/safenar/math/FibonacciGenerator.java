package com.safenar.math;

import java.math.BigInteger;

/**
 * <p>A Fibonacci sequence is a sequence of numbers, starting at either 0 and 1, 1 and 1 or
 * 1 and 2, with each next element being a sum of two previous elements.</p>
 *
 * <p>This implementation allows for changing first two elements, allowing for manipulating
 * the sequence without changing the rule.</p>
 * @see <a href="https://en.wikipedia.org/wiki/Fibonacci_number">
      Wikipedia article about Fibonacci sequence</a>
 * @see Generator
 */
public class FibonacciGenerator implements Generator {
	/**
	 * both first and second element determine how the sequence goes
	 */
	int first, second;
	
	/**
	 * Creates this generator with first 2 elements,
	 * @param first first element for this generator, equal to {@code this.generate(0)}
	 * @param second second element for this generator, equal to {@code this.generate(1)}
	 */
	public FibonacciGenerator(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int generate(int x) {
		return generate((long) x).intValue();
	}
	
	//todo type extension
	/**
	 * tried extending type of this generator
	 * @param x index or the position of the element in sequence
	 * @return generated element based on index
	 * @deprecated uses most common (and very slow) recursive algorithm
	 */
	public BigInteger generate(long x) {
		if (x == 0) return BigInteger.valueOf(first);
		if (x == 1) return BigInteger.valueOf(second);
		if (x>1) return generate(x - 2).add(generate(x - 1));
		return generate(x+1).add(generate(x+2));
	}
}

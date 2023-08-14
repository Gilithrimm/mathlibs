package com.safenar;

import static java.lang.Math.sqrt;

/**
 * What (in my opinion) should be in {@link java.lang.Math} but is not.
 * @author Gili
 */
public class MathUtil {
	/**
	 * The golden ratio.
	 * @see <a href="https://en.wikipedia.org/wiki/Golden_ratio">
	 *     A Wikipedia article about golden ratio</a>
	 */
	public static final double PHI=(1+sqrt(5))/2;
	
	/**
	 * This class is not intended to be instantiated.
	 */
	private MathUtil() {
	}
	
	/**
	 * Checks if the number is natural, i.e. is an integer higher than or equal 0.
	 * @param number checked number
	 * @return true if the given number is natural, false otherwise
	 */
	public static boolean isNatural(int number) {
		return number >= 0;
	}
	
	/**
	 * Checks whether a number is prime or not
	 * @param number checked number
	 * @return true if the number is prime, false otherwise
	 * @implNote this method uses Eratosthenes' sieve to determine if the number is prime
	 */
	public static boolean isPrime(int number) {
		if (number < 2) return false;
		if (number == 2) return true;
		if (number % 2 == 0) return false;
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0) return false;
		}
		return true;
	}

}

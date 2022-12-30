package com.safenar.math;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.LongStream;

import static java.lang.Math.sqrt;

/**
 * What (in my opinion) should be in {@link java.lang.Math} but is not.
 * @author Gili
 */
// todo methods for calculating sequences shouldn't be here, but separated
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
	/**
	 * Multiplies elements of sequence together
	 * @param sequence sequence we calculate product of
	 * @return product of given sequence
	 */
	public static long productOf(Sequence sequence) {
		return nullCheck(sequence)
				? -1
				: LongStream.of((sequence.toArray())).reduce(1, (a, b) -> a * b);
	}
	
	private static boolean nullCheck(Sequence seq) {
		return seq==null;
	}
	
	/**
	 * Calculates the average of all elements of given sequence
	 * @param seq given sequence
	 * @return average of given sequence or -1 if the sequence is null
	 */
	public static double averageOf(Sequence seq) {
		return nullCheck(seq)
				? -1
				: 1.0 * sumOf(seq) / seq.getLength();
	}
	
	/**
	 * Calculates the median, or the element in the middle of the sequence
	 * @param seq sequence that we search median in
	 * @return middle element (or average of both middle elements if there are
	 */
	public static double medianOf(Sequence seq) {
		if (nullCheck(seq)) return -1;
		return seq.getLength()%2==0
				? (seq.value(seq.getLength()/2)+ seq.value(seq.getLength()/2+1))/2.0
				: seq.value(seq.getLength()/2);
	}
	
	/**
	 * Calculates the mode, or the most common element of the sequence
	 * @param sequence sequence we calculate mode of
	 * @return element that appears most often in sequence or -1 if sequence is null
	 */
	public static int modeOf(Sequence sequence) {
		if (nullCheck(sequence)) return -1;
		Map<Integer, Integer> map = new TreeMap<>();
		
		for (Integer el : sequence)
			if (map.containsKey(el))
				map.put(el, map.get(el) + 1);
			else
				map.put(el, 1);
		
		var ref = new Object() {//temp to final vars for lambda
			int highestValue = 0;
			int mode = 0;
		};
		map.forEach((key, value) -> {
			if (value > ref.highestValue) {//why vars in lambda must be final?
				ref.highestValue = value;
				ref.mode = key;
			}
		});
		return ref.mode; //java why
	}
	
	/**
	 * Checks whether a given number is in the sequence
	 * @param haystack a {@link Sequence} to search through
	 * @param needle a searched number
	 * @return true if needle is equal to any number in the sequence, false otherwise
	 */
	public static boolean isInSequence(Sequence haystack, int needle) {
		if (nullCheck(haystack)) return false;
		for (int i = haystack.getMin(); i < haystack.getMax(); i++)
			if (needle == haystack.value(i))
				return true;
		return false;
	}
	
	/**
	 * Sums all values in the sequence
	 * @param seq a {@link Sequence}
	 * @return sum of all elements of a sequence or -1 if sequence is null
	 */
	public static int sumOf(Sequence seq) {
		if (nullCheck(seq)) return -1;
		int result = 0;
		for (int el : seq) {
			result += el;
		}
		return result;
	}
}

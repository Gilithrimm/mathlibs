package com.safenar;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.LongStream;

/**
 * Different operations on sequences, without mutating them.
 * @author Gili
 */
public class SequenceOperations {
	/**
	 * Multiplies elements of sequence together
	 * @param sequence sequence we calculate product of
	 * @return product of given sequence
	 */
	public static long productOf(Sequence sequence) {
		return sequence==null
				? -1
				: LongStream.of((sequence.toArray())).reduce(1, (a, b) -> a * b);
	}
	
	/**
	 * Calculates the average of all elements of given sequence
	 * @param seq given sequence
	 * @return average of given sequence or -1 if the sequence is null
	 */
	public static double averageOf(Sequence seq) {
		return seq==null
				? -1
				: 1.0 * sumOf(seq) / seq.getLength();
	}
	
	/**
	 * Calculates the median, or the element in the middle of the sequence
	 * @param seq sequence that we search median in
	 * @return middle element (or average of both middle elements if there are
	 */
	public static double medianOf(Sequence seq) {
		return seq==null
				?-1
				:seq.getLength()%2==0
				? (seq.value(seq.getLength()/2)+ seq.value(seq.getLength()/2+1))/2.0
				: seq.value(seq.getLength()/2);
	}
	
	/**
	 * Calculates the mode, or the most common element of the sequence
	 * @param sequence sequence we calculate mode of
	 * @return element that appears most often in sequence or -1 if sequence is null
	 */
	public static int modeOf(Sequence sequence) {
		if (sequence==null) return -1;
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
		if (haystack==null) return false;
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
		if (seq==null) return -1;
		int result = 0;
		for (int el : seq) {
			result += el;
		}
		return result;
	}
}

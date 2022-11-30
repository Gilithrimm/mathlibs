package com.safenar.math;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.LongStream;

public class MathUtil {
	@Contract(pure = true)
	public static boolean isNatural(int number) {
		return number >= 0;
	}
	
	@Contract(pure = true)
	public static boolean isPrime(int number) {
		if (number < 2) return false;
		if (number == 2) return true;
		if (number % 2 == 0) return false;
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0) return false;
		}
		return true;
	}
	
	//multiplies elements of sequence together
	//shouldn't it be productOf(Seq), averageOf(Seq) etc.?
	public static long productOf(Sequence sequence) {
		return nullCheck(sequence) == -1
				? -1
				: LongStream.of((sequence.toArray())).reduce(1, (a, b) -> a * b);
	}
	
	private static int nullCheck(Sequence seq) {
		if (seq == null) return -1;
		return 0;
	}
	
	public static int averageOf(Sequence seq) {
		return nullCheck(seq) == -1
				? -1
				: sumOf(seq) / seq.getLength();
	}
	
	//median - middle number (or avg of two) in sorted seq/set
	public static long medianOf(Sequence sequence) {
		if (nullCheck(sequence) == -1) return -1;
		long[] sorted = Arrays.copyOf(sequence.toArray(), sequence.getLength());
		Arrays.sort(sorted);
		int middle = sorted.length / 2;
		return sorted.length % 2 == 0 ? (sorted[middle - 1] + sorted[middle]) / 2 : sorted[middle];
	}
	
	//mode - number that appears most often in set/sequence/whatever you call it
	public static int modeOf(Sequence sequence) {
		if (nullCheck(sequence) == -1) return -1;
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
		return ref.mode;
	}
	
	@Contract(value = "null, _ -> false")
	public static boolean isInSequence(Sequence haystack, int needle) {
		if (nullCheck(haystack) == -1) return false;
		for (int i = haystack.getMin(); i < haystack.getMax(); i++)
			if (needle == haystack.value(i))
				return true;
		return false;
	}
	
	public static int sumOf(Sequence seq) {
		if (nullCheck(seq) == -1) return -1;
		int result = 0;
		for (int el : seq) {
			result += el;
		}
		return result;
	}
	
	//    public static int randomInRange(Range range){
	//        return new Random().nextInt(range.getLength())+range.getMin();
	//    }//not that useful atm and not deterministic, thus not testable for my knowledge
}

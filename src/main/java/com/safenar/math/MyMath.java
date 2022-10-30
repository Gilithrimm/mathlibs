package com.safenar.math;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyMath {
		public static boolean isNatural(int number) {
				return number == Math.abs(number);
		}
		
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
		public static int getProduct(Sequence sequence) {
				return nullCheck(sequence) == -1
								? -1
								: IntStream.of(sequence.toArray()).reduce(1, (a, b) -> a * b);
		}
		
		private static int nullCheck(Sequence seq) {
				if (seq == null) return -1;
				return 0;
		}
		
		public static int getAverage(Sequence seq) {
				return nullCheck(seq) == -1
								? -1
								: sum(seq) / seq.getLength();
		}
		
		public static int getMedian(Sequence sequence) {
				if (nullCheck(sequence) == -1) return -1;
				int[] sorted = Arrays.copyOf(sequence.toArray(), sequence.getLength());
				Arrays.sort(sorted);
				int middle = sorted.length / 2;
				return sorted.length % 2 == 0 ? (sorted[middle - 1] + sorted[middle]) / 2 : sorted[middle];
		}
		
		public static int getMode(Sequence sequence) {
				if (nullCheck(sequence) == -1) return -1;
				int[] sorted = Arrays.copyOf(sequence.toArray(), sequence.getLength());
				Arrays.sort(sorted);
				int maxCount = 1;
				int currentCount = 1;
				int mode = sorted[0];
				for (int i = 1; i < sorted.length; i++) {
						if (sorted[i] == sorted[i - 1]) {
								currentCount++;
						} else {
								if (currentCount > maxCount) {
										maxCount = currentCount;
										mode = sorted[i - 1];
								}
								currentCount = 1;
						}
				}
				if (currentCount > maxCount) {
						maxCount = currentCount;
						mode = sorted[sorted.length - 1];
				}
				return mode;
		}
		
		public static boolean isInSequence(Sequence haystack, int needle) {
				if (nullCheck(haystack) == -1) return false;
				for (int i = haystack.getMin(); i < haystack.getMax(); i++)
						if (needle == haystack.value(i))
								return true;
				return false;
		}
		
		public static int sum(Sequence seq) {
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

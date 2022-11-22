package com.safenar.math;

public class Range extends ArithmeticSequence {
		//TODO tests
		
		public Range(int min, int max) {
				super(min, max, 1);
		}
		
		@Override
		public int getStep() {
				return 1;
		}
		
		@Override
		public Generator getGenerator() {
				return x -> x + getMin();
		}
}

package com.safenar.math;

import java.math.BigInteger;

public class FibonacciGenerator implements Generator {
	int first, second;
	
	public FibonacciGenerator(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int generate(int x) {
		return generate((long) x).intValue();
	}
	
	public BigInteger generate(long x) {
		if (x == 0) return BigInteger.valueOf(first);
		if (x == 1) return BigInteger.valueOf(second);
		if (x>1) return generate(x - 2).add(generate(x - 1));
		return generate(x+1).add(generate(x+2));
	}
}

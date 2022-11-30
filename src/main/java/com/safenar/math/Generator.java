package com.safenar.math;

@FunctionalInterface
// TODO: 30.11.2022 documentation shit
public interface Generator {
	int generate(int x);
	
	default boolean equals(Generator other) {
		return other != null
				&& hash() == other.hash();
	}
	
	default int hash() {
		int hash = 0;
		for (int i = -5; i <= 5; i++)
			hash = hash * 31 + generate(i);
		return hash;
	}
	
	default String string() {
		StringBuilder builder = new StringBuilder();
		for (int i = -5; i <= 5; i++)
			builder.append(i)
					.append(" -> ")
					.append(generate(i))
					.append(";\n");
		return builder.toString();
	}
}

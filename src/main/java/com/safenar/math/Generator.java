package com.safenar.math;

/**
 * A Generator is a function by which {@link Sequence sequences} are generated.
 * */
@FunctionalInterface
public interface Generator {
	/**
	 * Generates an element of the sequence based on index
	 * @param x index or the position of the element in sequence
	 * @return generated element based on index*/
	int generate(int x);
	
	/**
	 * Use this instead of {@link Object#equals(Object)}
	 * @param other the generator to be compared
	 * @return true if the {@link #hash() hashes} of the generators are equal
	 * @see #hash()
	 */
	default boolean equals(Generator other) {
		return other != null
				&& hash() == other.hash();
	}
	
	/**
	 * Use this instead of {@link Object#hashCode()}
	 * @deprecated hash code from this implementation remains the same for the same Generator across
	 * different executions, which collides with first point of the {@link Object#hashCode()}
	 * @implNote indexes of the values are between -5 and 5, both inclusive
	 * @return hash of this Generator
	 */
	default int hash() {
		int hash = 0;
		for (int i = -5; i <= 5; i++)
			hash = hash * 31 + generate(i);
		return hash;
	}
	
	/**
	 * Use this instead of {@link Object#toString()}
	 * @return string representation of this Generator
	 * @implSpec since there's no simple way to get the equation this Generator represents,
	 * we just generate values for indexes between -5 and 5
	 */
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

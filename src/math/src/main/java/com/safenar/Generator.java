package com.safenar;

/**
 * A com.safenar.Generator is a function by which {@link Sequence sequences} are generated.
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
	 * @deprecated hash code from this implementation remains the same for the same com.safenar.Generator across
	 * different executions, which collides with first point of the {@link Object#hashCode()}
	 * @implNote indexes of the values are between -5 and 5, both inclusive
	 * @return hash of this com.safenar.Generator
	 */
	@Deprecated
	default int hash() {
		int hash = 0;
		for (int i = -5; i <= 5; i++)//todo explain
			hash = hash * 31 + generate(i);//todo these lines in docs
		return hash;
	}
	
	/**
	 * Use this instead of {@link Object#toString()}
	 * @return string representation of this com.safenar.Generator
	 * @implSpec since there's no simple way to get the equation this com.safenar.Generator represents,
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

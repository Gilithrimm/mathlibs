package com.safenar;

/**
 * Set of simple generators pre-coded for quick use.
 * That shouldn't discourage you from making your own {@linkplain Generator generators} though.
 * */
public enum Generators implements Generator {
	/**{@summary Default com.safenar.Generator. Returns series of 0s (f(x)=0).}*/
	NULL(x -> 0),
	/**{@summary Negative com.safenar.Generator. Returns given values, inverted (f(x)=-x).}*/
	NEG(x -> -x),
	/**{@summary Basic com.safenar.Generator. Returns values passed as arguments (f(x)=x).}*/
	SELF(x -> x),
	/**Returns absolute values of passed arguments (f(x)=|x|).
	 * @see Math#abs(int)*/
	ABS(Math::abs),
	/**Returns passed arguments to the power of two (f(x)=x<sup>2</sup>).*/
	POW(x -> x * x),
	/**Returns consecutive elements of the Fibonacci sequence (f(x)=f(x-1)+f(x-2)).
	 * @implNote element at index 0 is 0, and at index 1 there is 1; rest is generated like normal
	 * */
	FIB(new FibonacciGenerator(0, 1)),
	;
	/**
	 * The underlying generator.
	 */
	private final Generator gen;
	
	Generators(Generator gen) {
		this.gen = gen;
	}
	
	/**
	 * Returns the generator under this enum
	 * @deprecated it's pretty much useless as each of the enums <b>is</b> {@linkplain Generator a generator}.
	 * @return the underlying generator
	 */
	@Deprecated
	public Generator getGen() {
		return gen;
	}
	
	/**{@inheritDoc}*/
	@Override
	public int generate(int x) {
		return gen.generate(x);
	}
	
	/**
	 * Matches the generator to one of pre-coded ones.
	 * @param gen generator we try matching
	 * @return an enum that is {@link Generator#equals(Generator) equal}
	 * to the given com.safenar.Generator or {@link Generators#NULL} if gen is null or there's no match
	 * */
	public static Generators fromGenerator(Generator gen) {
		if (gen == null)
			return NULL;
		for (Generators value : values())
			if (gen.equals(value.gen))
				return value;
		return NULL;
	}
}

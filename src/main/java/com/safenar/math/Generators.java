package com.safenar.math;

public enum Generators implements Generator {
		//TODO test generators
		NULL(x -> 0),
		NEG(x -> -x),
		SELF(x -> x),
		ABS(Math::abs),
		HASH(new HashGenerator(SELF)),//does it work?
		POW(x -> x * x),
		//fibGen(0,1)==fibGen(1,1)==fibGen(1,2)!==fibGen(1,1)!==fibGen(0,1)
		FIBONACCI(new FibonacciGenerator(0, 1)),
		;
		
		private final Generator gen;
		
		Generators(Generator gen) {
				this.gen = gen;
		}
		
		public Generator getGen() {
				return gen;
		}
		
		@Override
		public int generate(int x) {
				return gen.generate(x);
		}
		
		public static Generators fromGenerator(Generator gen) {
				for (Generators value : values())
						if (gen.equals(value.getGen()))
								return value;
				return NULL;
		}
}

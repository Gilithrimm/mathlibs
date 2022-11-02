package com.safenar.math;

@FunctionalInterface
public interface Generator {
		//TODO test defaults
		int generate(int x);
		
		default boolean equals(Generator other) {
				if (other == null)
						return false;
				for (int i = -10; i < 10; i++)
						if (generate(i) != other.generate(i))
								return false;
				return true;
		}
		
		default int hash() {
				int hash = 0;
				for (int i = -10; i < 10; i++)
						hash = hash * 31 + generate(i);
				return hash;
		}
		
		default int hash2() {
				int hash = 0;
				((HashGenerator) Generators.HASH.getGen()).setInner(this);
				for (int i = -10; i < 10; i++)
						hash = Generators.HASH.generate(i);
				return hash;
		}
		
		default int hashValue(int x) {
				int hash = 0;
				for (int i = -10; i < 10 && i < x; i++)
						hash = hash * 31 + generate(i);
				return hash;
		}
		
		default String string() {
				StringBuilder builder = new StringBuilder();
				for (int i = -10; i <= 10; i++)
						builder.append(i).append(" -> ").append(generate(i)).append("; ");
				return builder.toString();
		}
}

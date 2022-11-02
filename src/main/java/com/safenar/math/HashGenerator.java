package com.safenar.math;

public class HashGenerator implements Generator {
		//TODO tests smh
		//TODO does this even work?
		private Generator inner;
		
		public HashGenerator(Generator inner) {
				this.inner = inner;
		}
		
		public Generator getInner() {
				return inner;
		}
		
		public void setInner(Generator inner) {
				this.inner = inner;
		}
		
		@Override
		public int generate(int x) {
				return inner.hash();
		}
}
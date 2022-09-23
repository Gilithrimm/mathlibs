package com.safenar.math;

public enum Generators implements Generator{
    NULL(x->0),
    NEG(x->-x),
    SELF(x->x),
    ABS(Math::abs),
    HASH(new HashGenerator(SELF)),
    POW(x -> x*x),
    FIBONACCI(new FibonacciGenerator(1,1)),
    ;

    private final Generator gen;

    Generators(Generator gen) {
        this.gen = gen;
    }

    public Generator getGen() {
        return gen;
    }

    @Override
    public int generate(int x){
        return gen.generate(x);
    }

    public static Generators fromGenerator(Generator gen){
        for (Generators value : values())
            if (gen.equals(value.getGen()))
                return value;
        return NULL;
    }
}

package com.safenar.math;

public class FibonacciGenerator implements Generator{
    int first,second;

    public FibonacciGenerator(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int generate(int x) {
        if (x==0) return first;
        if (x==1) return second;
        return generate(x-2)+generate(x-1);
    }
}

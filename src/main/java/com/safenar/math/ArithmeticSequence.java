package com.safenar.math;

public class ArithmeticSequence extends Sequence {
    //a(n)=a(n-1)+step
    //a(0)=min
    //a(1)=min+step
    //...
    //a(n)=min+n*step
    private int step;

    public ArithmeticSequence(int min, int max, int step) {
        super(min, max, x -> x*step+min);
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public Generator getGenerator() {
        return x -> x*step+getMin();
    }

}

package com.safenar.math;

import com.safenar.Main;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class Sequence {
    private int min, max;
    private final Generator generator;

    public Sequence(int min, int max, Generator generator) {
        this.min = min;
        this.max = max;
        this.generator = generator;
    }

    public int[] toArray(){
        return IntStream.range(0, getMax()-getMin())
                .map(i -> value(min+i))
                .distinct()
                .toArray();
    }

    public boolean isInSequence(int num){
        boolean result = num > getMin() && num < getMax();
        for (int i = getMin(); i < getMax(); i++) {
            if (num == value(i)){
                result = true;
                break;
            }
        }
        return result;
    }

    public int indexOf(int y){
        for (int i = getMin(); i < getMax(); i++) {
            if (y == value(i)){
                return i;
            }
        }
        return 0;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return Main.toString(Arrays.stream(toArray()).boxed().toArray());//why stream?
    }

    public int value(int i) {
        return Math.min(generator.generate(i), getMax());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sequence sequence)) return false;
        return getMin() == sequence.getMin()
                && getMax() == sequence.getMax()
                && getGenerator().equals(sequence.getGenerator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMin(), getMax(), getGenerator().hash());
    }
}

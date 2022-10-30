package com.safenar.math;

import com.safenar.Main;

import java.util.*;
import java.util.stream.IntStream;

public class Sequence implements Iterable<Integer>{//TODO add sorting(Comparable)
    private int min, max;
    private final Generator generator;
    private final Iter iter;//one inst per seq
    
    public Sequence(int min, int max, Generator generator) {
        this.min = min;
        this.max = max;
        this.generator = generator==null
            ?Generators.NULL
            :generator;
        iter=new Iter(this);
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
    public int getLength(){
        return getMax()-getMin()+1;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }

    public int[] toArray(){
        return IntStream.range(0, getLength())
                .map(i -> value(min+i))
                .toArray();
    }

    public int value(int i) {
        int result=getGenerator().generate(i);
        if (result>generator.generate(getMax()))
            throw new IndexOutOfBoundsException();
        return result;
    }

    public int indexOf(int y){
        for (int i = getMin(); i < getMax(); i++) {
            if (y == value(i)){
                return i;
            }
        }
        throw new IndexOutOfBoundsException(y+"is out of bound for this sequence: "+ this);
    }

    @Override
    public String toString() {
        return Main.toString(Arrays.stream(toArray()).boxed().toArray());//why stream?
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
    
    @Override
    public Iterator<Integer> iterator() {
        return iter;
    }
    
    private static class Iter implements Iterator<Integer>{
        int current;
        int max;
        private final Generator gen;//breakdown to avoid recursion
        
    
        public Iter(Sequence seq) {
            gen=seq.getGenerator();
            max= seq.getMax();
            current= seq.getMin();
        }
    
        @Override
        public boolean hasNext() {
            return current<=max;
        }
    
        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            current++;
            return gen.generate(current-1);
        }
    }
}

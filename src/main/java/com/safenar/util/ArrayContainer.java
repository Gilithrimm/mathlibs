package com.safenar.util;

import java.util.Arrays;

public class ArrayContainer<T> implements Container<T>{
    private final T[] array;

    public ArrayContainer(T[] array){
        this.array = array;
    }

    @Override
    public boolean contains(T t) {
        return Arrays.asList(array).contains(t);
    }
}

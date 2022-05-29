package com.safenar;

public class Main {
    public static <T>String toString(T[] arr){
        StringBuilder builder=new StringBuilder();
        for (T o : arr) {
            builder.append(o).append(' ');
        }
        return builder.toString();
    }

    public static void main(String[] args) {

    }
}

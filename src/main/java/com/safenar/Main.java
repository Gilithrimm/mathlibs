package com.safenar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static <T>String toString(T[] arr){
        StringBuilder builder=new StringBuilder();
        for (T o : arr) {
            builder.append(o).append(' ');
        }
        return builder.toString();
    }

    public static String formatTime(long millis){
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        Date date=new Date(millis);
        return format.format(date);
    }

    public static void main(String[] args) {

    }

}

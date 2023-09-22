package com.safenar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    //TODO time measuring module
    public static String formatTime(long millis) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(millis);
        return format.format(date);
    }

    public static void main(String[] args) {
    }
}

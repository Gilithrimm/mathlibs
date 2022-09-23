package com.safenar;

import com.safenar.math.*;
import com.safenar.util.Code;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.safenar.math.MyMath.*;

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

    public static void measureTime(Code code) throws MathException {
        long start=System.currentTimeMillis();
        System.out.println("Started at "+formatTime(start));
        code.execute();
        System.out.println("ended at "+formatTime(System.currentTimeMillis()));
        System.out.println("Took "+(System.currentTimeMillis()-start)+"ms to finish");
    }

    public static void main(String[] args) throws MathException {
        Code c1=()->{
            Generator fib= Generators.FIBONACCI;
            Generator plusOne=x->x+1;
            Generator lucas=new FibonacciGenerator(1,3);
            System.out.println("fib High = " + divAvgHigh(fib));
            System.out.println("fib Low = " + divAvgLow(fib));
            System.out.println("plusOne High = " + divAvgHigh(plusOne));
            System.out.println("plusOne Low = " + divAvgLow(plusOne));
            System.out.println("lucas High = " + divAvgHigh(lucas));
            System.out.println("lucas Low = " + divAvgLow(lucas));
            //this goes for 6.5h wtf
        };
        Code c2=()-> System.out.println(Generators.FIBONACCI.generate(50));//over 10h & still no result
        //i think i messed up pretty badly
        //it's just recursion fibonacci algorithm wtf
        //if there would be no results till 21 ix 22 00:00 i halt it and run for 50 instead
        //15h later still no results wtf
        //i left it for a night and it's still going
        //36h of no result
        //halting

        measureTime(c2);
    }

}

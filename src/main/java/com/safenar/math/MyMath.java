package com.safenar.math;

import java.util.Arrays;
import java.util.OptionalDouble;

public class MyMath {
//    public static boolean inRangeOptimized(int needle, Range haystack){
//
//    }

    //    public static int randomInRange(Range range){
//        return new Random().nextInt(range.getLength())+range.getMin();
//    }
    public static boolean isNatural(int number) {
        return number == Math.abs(number);
    }
//
//    public int getSum() {
//        return IntStream.of(sequence).sum();
//    }
//
//    public int getProduct() {
//        return IntStream.of(sequence).reduce(1, (a, b) -> a * b);
//    }
//
//    public int getMax() {
//        return Arrays.stream(sequence).max().orElse(0);
//    }
//
//    public int getMin() {
//        return Arrays.stream(sequence).min().orElse(0);
//    }
//
//    public int getAverage() {
//        return getSum() / sequence.length;
//    }
//
//    public int getMedian() {
//        int[] sorted = Arrays.copyOf(sequence, sequence.length);
//        Arrays.sort(sorted);
//        int middle = sorted.length / 2;
//        return sorted.length % 2 == 0 ? (sorted[middle - 1] + sorted[middle]) / 2 : sorted[middle];
//    }
//
//    public int getMode() {
//        int[] sorted = Arrays.copyOf(sequence, sequence.length);
//        Arrays.sort(sorted);
//        int maxCount = 1;
//        int currentCount = 1;
//        int mode = sorted[0];
//        for (int i = 1; i < sorted.length; i++) {
//            if (sorted[i] == sorted[i - 1]) {
//                currentCount++;
//            } else {
//                if (currentCount > maxCount) {
//                    maxCount = currentCount;
//                    mode = sorted[i - 1];
//                }
//                currentCount = 1;
//            }
//        }
//        if (currentCount > maxCount) {
//            maxCount = currentCount;
//            mode = sorted[sorted.length - 1];
//        }
//        return mode;
//    }

    public static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static double divAvgHigh(Generator g) throws MathException {
        int[] generated=new int[100];
        for (int i = 0; i < generated.length; i++) {
            generated[i]=g.generate(++i)/g.generate(--i);
        }
        OptionalDouble result= Arrays.stream(generated).average();
        if (result.isPresent())
            return result.getAsDouble();
        throw mathExp("divAvgHigh(): average is null");
    }

    private static MathException mathExp(String output) {
        return new MathException(output);
    }

    public static double divAvgLow(Generator g) throws MathException {
        int[] generated=new int[100];
        for (int i = 0; i < generated.length;) {
            generated[i]=g.generate(i)/g.generate(++i);
        }

        OptionalDouble result= Arrays.stream(generated).average();
        if (result.isPresent())
            return result.getAsDouble();
        throw mathExp("divAvgLow(): average is null");
    }
}

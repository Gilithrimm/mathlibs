package com.safenar.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.safenar.math.MyMath.*;

class MyMathTest {
    //test naming convention as of 2X2022: {testedMethod}_for{TestCase}()

    @Test
    void isNatural_forNaturalNumber() {
        int number=1;
        boolean actual=isNatural(number);
        assertTrue(actual);
    }
    @Test
    void isNatural_forNonNaturalNumber(){
        int number=-1;
        boolean actual=isNatural(number);
        assertFalse(actual);
    }
    @Test
    void isNatural_forZero(){
        int number=0;
        boolean actual=isNatural(number);
        assertTrue(actual);
    }

    @Test
    void isPrime_forPrime() {
        int prime=11;
        boolean actual=isPrime(prime);
        assertTrue(actual);
    }
    @Test
    void isPrime_forNonPrime(){
        int nonPrime=327;
        boolean actual=isPrime(nonPrime);
        assertFalse(actual);
    }
    @Test
    void isPrime_forOne(){
        int one=1;
        boolean actual=isPrime(one);
        assertFalse(actual);
    }
    @Test
    void isPrime_forZero(){
        int zero=0;
        boolean actual=isPrime(zero);
        assertFalse(actual);
    }
    @Test
    void isPrime_forNegative(){
        //we can either 1) treat negative values with abs()
        //or 2) not pass them through
        int negative=-5;
        boolean actual=isPrime(negative);
        assertFalse(actual);//2)
    }

    @Test
    void isInSequence_forInSequence(){
        Sequence sequence=new Sequence(0,10,Generators.POW);
        int needle=25;
        boolean actual=isInSequence(sequence,needle);
        assertTrue(actual);

    }
    @Test
    void isInSequence_forNotInSequence(){
        Sequence sequence=new Sequence(0,10,Generators.POW);
        int needle=500;
        boolean actual=isInSequence(sequence,needle);
        assertFalse(actual);
    }
    @Test
    void isInSequence_forNullSequence() {
        Sequence seq=null;
        int needle=0;
        boolean actual=isInSequence(seq,needle);
        assertFalse(actual);
    }
    @Test
    void isInSequence_forNullGenerator(){
        Sequence seq=new Sequence(0,10,null);
        int zero=0;
        boolean actualZero=isInSequence(seq,zero);
        assertTrue(actualZero);
        int nonZero=1;
        boolean actual=isInSequence(seq,nonZero);
        assertFalse(actual);
    }

    @Test
    void sum_forNullSequence() {
        Sequence seq=null;
        int actual=sum(seq);
        assertEquals(0,actual);
    }
    @Test
    void sum_forNullGenerator(){
        Sequence seq=new Sequence(0,10,null);
        int actual=sum(seq);
        assertEquals(0,actual);
    }
    @Test
    void sum_for(){//what to test here?
    }
}
package com.safenar.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static com.safenar.math.Generators.*;
import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {
    Sequence seq=new Sequence(0,10,POW);

    @Test
    void toArray_for() {
        int[] expected={0,1,4,9,16,25,36,49,64,81,100};
        int[] actual=seq.toArray();
        assertArrayEquals(expected,actual);
    }

    @Test
    void value_forValueInSequence() {
        int inSequence=5;
        int value=seq.value(inSequence);
        assertEquals(25,value);
    }
    @Test
    void value_forValueNotInSequence(){
        int notInSequence=25;
        Executable result=()->seq.value(notInSequence);
        assertThrows(IndexOutOfBoundsException.class,result);
    }

    @Test
    void indexOf_forIndexInSequence() {
        int inSequence=25;
        int index=seq.indexOf(inSequence);
        assertEquals(5,index);
    }
    @Test
    void indexOf_forIndexNotInSequence(){
        int notInSequence=5;
        Executable result=()->seq.indexOf(notInSequence);
        assertThrows(IndexOutOfBoundsException.class,result);
    }
}
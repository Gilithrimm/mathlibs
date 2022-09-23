package com.safenar.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//import com.test.api.*;

class SequenceTest {
    Sequence tested;
    int[] expected=new int[]{0,1,4,9,10};

    @BeforeEach
    void setUp(){
        tested=new Sequence(0,10,Generators.POW);
    }

    @Test
    void toArray() {
        assertArrayEquals(expected,tested.toArray());
    }

    @Test
    void value() {
        assertEquals(expected[0],tested.value(0));
        assertEquals(expected[3],tested.value(3));
    }

    @Test
    void inSequence() {
        int exp=4;
        assertTrue(tested.isInSequence(exp));
    }

    @Test
    void indexOf(){
        int num=625;
        tested.setMax(1000);
        assertEquals(Math.sqrt(num),tested.indexOf(num));
    }
}
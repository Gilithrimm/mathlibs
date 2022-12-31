package com.safenar.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.safenar.math.Generators.*;
import static org.junit.jupiter.api.Assertions.*;

public class SequenceTest {
    Sequence seq=new Sequence(1,10,POW);

    @Test
    public void toArray_for() {//do we need this test? if not, then
        long[] expected={1,4,9,16,25,36,49,64,81,100};
        long[] actual=seq.toArray();
        assertArrayEquals(expected,actual);
    }

    @Test
    public void value_forValueInSequence() {
        int inSequence=5;
        int value=seq.value(inSequence);
        assertEquals(25,value);
    }
    @Test
    public void value_forValueNotInSequence(){
        int notInSequence=25;
        Executable result=()->seq.value(notInSequence);
        assertThrows(IndexOutOfBoundsException.class,result);
    }

    @Test
    public void indexOf_forIndexInSequence() {
        int inSequence=25;
        int index=seq.indexOf(inSequence);
        assertEquals(5,index);
    }
    @Test
    public void indexOf_forIndexNotInSequence(){
        int notInSequence=5;
        Executable result=()->seq.indexOf(notInSequence);
        assertThrows(IndexOutOfBoundsException.class,result);
    }
    
    @Test
    public void Iter_hasNext_forNextElement() {
        Iterator<Integer> iterator=seq.iterator();
        boolean actual=iterator.hasNext();
        assertTrue(actual);
    }
    @Test
    public void Iter_hasNext_forEmptySequence(){
        var iter=new Sequence(1,0,POW).iterator();
//        iter.next();
        boolean actual = iter.hasNext();
        assertFalse(actual);
    }
    @Test
    public void Iter_hasNext_forNoNextElements(){
        var iter=new Sequence(0,0,POW).iterator();
        iter.next();
        boolean actual = iter.hasNext();
        assertFalse(actual);
    }
    
    @Test
    void Iter_next_forNextElement() {
        var iter=seq.iterator();
        int next=iter.next();
        assertEquals(1,next);
    }
    @Test
    public void Iter_next_forEmptySequence(){
        var iter=new Sequence(-1,0,POW).iterator();
        iter.next();
        iter.next();
        Executable actual= iter::next;
        assertThrows(NoSuchElementException.class,actual);
    }
    @Test
    public void Iter_next_forNoNextElements(){
        var iter=new Sequence(0,0,POW).iterator();
        iter.next();
        Executable actual= iter::next;
        assertThrows(NoSuchElementException.class,actual);
    }
}
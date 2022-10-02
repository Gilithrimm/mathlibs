package com.safenar.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    Generator main;
    Generator other;

    @BeforeEach
    void setUp() {
        main = Generators.NEG;
        other = Generators.NEG.getGen();
    }

    @Test
    void testEquals() {
        assertTrue(main.equals(other));
    }

    @Test
    void testNotEquals() {
        other = Generators.SELF;
        assertFalse(main.equals(other));
    }

    @Test
    void testNotEqualsNull() {
        assertFalse(main.equals(null));
    }

    @Test
    void testNotEqualsDifferentClass() {
        assertNotEquals(main, new Object());
    }

    @Test
    void testHash() {
        assertEquals(main.hash(), other.hash());
    }

    @Test
    void testHashDifferent() {
        other = x -> x + 1;
        assertNotEquals(main.hash(), other.hash());
    }

    @Test
    void testHashDifferentClass() {
        assertNotEquals(main.hash(), new Object().hashCode());
    }

    @Test
    void testHashNull() {
        assertNotEquals(main.hash(), (Integer) null);
    }

    @Test
    void testHashZero() {
        assertNotEquals(main.hash(), 0);
    }

    @Test
    void testHash2(){
        assertEquals(main.hash2(),other.hash2());
    }

    @Test
    void testHash2IsHash(){
        ((HashGenerator) Generators.HASH.getGen()).setInner(main);
        assertEquals(main.hash(),main.hash2());
        assertEquals(other.hash(),other.hash2());
        assertEquals(main.hash(),other.hash());
//        if main.hash()==main.hash2()
//        && other.hash()==other.hash2()
//        && main.hash()==other.hash()
//        then main.hash()==other.hash2()
//        && main.hash2()==other.hash()
    }

    @Test
    void testHashGenerator(){

    }
}
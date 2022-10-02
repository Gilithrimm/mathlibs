package com.safenar.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorsTest {

    @Test
    void generate() {
        //build
        Generators[] gens=Generators.values();
        int[] vals=new int[gens.length];
        //operate
        for (int i = 0; i < gens.length; i++) {
            vals[i]=gens[i].generate(i);
        }
        //check
        assertArrayEquals(new int[]{0,-1,2,3,-1733670794,25,13},vals);
    }

    @Test
    void fromGenerator() {
        Generators test = Generators.fromGenerator(x -> -x);
        assertTrue(test.equals(Generators.NEG));
    }

    @Test
    void fromGenerator_hashesShouldBeEqual(){
        int hash=Generators.NEG.hash();
        Generators gen=Generators.fromGenerator(x -> -x);
        assertEquals(hash,gen.hash());
        assertEquals(hash,gen.hash2());
    }
}
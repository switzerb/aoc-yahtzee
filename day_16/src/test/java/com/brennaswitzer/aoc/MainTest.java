package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    
    Register r = new Register();
    
    @Before
    public void setUp() {
        int[] before = {3,2,1,1};
        int[] after = {3,2,2,1};
        r.addOpcode(9, 2, 1, 2, before, after);
    }
    
    @Test
    public void testMulr() {
        int[] before = {3, 2, 1, 1};
        int[] after = {3, 2, 2, 1};
        int opcode = 9;
        int A = 2;
        int B = 1;
        int C = 2;
        Register.Opcode i = new Register.Opcode(9,2,1,2, before, after);
        int[] result = r.mulr(i);
        assertTrue(Arrays.equals(result, after));
    }
    
    @Test
    public void testAddi() {
        int[] before = {3, 2, 1, 1};
        int[] after = {3, 2, 2, 1};
        int opcode = 9;
        int A = 2;
        int B = 1;
        int C = 2;
        Register.Opcode i = new Register.Opcode(9,2,1,2, before, after);
        int[] result = r.addi(i);
        assertTrue(Arrays.equals(result, after));
    }
    
    @Test
    public void testCountThrees() {
        r.countInstructions();
        assertEquals(1, r.countThrees());
    }
    
}

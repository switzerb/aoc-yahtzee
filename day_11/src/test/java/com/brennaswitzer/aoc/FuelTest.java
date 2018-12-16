package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FuelTest {


    @Before
    public void setup() {

    }

    @Test
    public void testGetHundreds() {
        Fuel fuel = new Fuel(8);
        assertEquals(2, fuel.hundreds(1234));
    }

    @Test
    public void testRackID() {
        Fuel fuel = new Fuel(8);
        assertEquals(13, fuel.getRackID(3));
    }

    @Test
    public void testGetPowerLevel() {
        Fuel fuel = new Fuel(8);
        assertEquals(4, fuel.getPowerLevel(3, 5));
    }

    @Test
    public void testGetPowerLevel2() {
        Fuel fuel = new Fuel(57);
        assertEquals(-5, fuel.getPowerLevel(122, 79));
    }

    @Test
    public void testGetPowerLevel3() {
        Fuel fuel = new Fuel(39);
        assertEquals(0, fuel.getPowerLevel(217, 196));
    }

    @Test
    public void testGetPowerLevel4() {
        Fuel fuel = new Fuel(71);
        assertEquals(4, fuel.getPowerLevel(101, 153));
    }



}

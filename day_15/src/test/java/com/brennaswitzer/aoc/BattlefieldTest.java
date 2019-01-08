package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BattlefieldTest {

    Battlefield battle1;
    Battlefield battle2;
    Battlefield battle3;

    @Before
    public void setup() throws IOException {
        battle1 = new Battlefield(Loader.getInput("test_input.txt"));
        battle2 = new Battlefield(Loader.getInput("test_input2.txt"));
        battle3 = new Battlefield(Loader.getInput("test_input3.txt"));
    }

    @Test
    public void test_width() {
        assertEquals(7, battle1.getWidth());
    }

    @Test
    public void test_height() {
        assertEquals(5, battle1.getHeight());
    }

    @Test
    public void test_readField() {
        String init = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        assertEquals(init, battle1.toString());
    }

    @Test
    public void test_runRound() {
        battle1.runRound();
    }

    @Ignore
    public void test_outcome() {
        while (!battle3.done) {
            battle3.runRound();
        }
        assertEquals(27730, battle3.outcome());
    }


}

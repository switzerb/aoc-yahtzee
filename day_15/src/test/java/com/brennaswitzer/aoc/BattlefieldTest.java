package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BattlefieldTest {

    Battlefield battle;

    @Before
    public void setup() throws IOException {

        List<String> input = Loader.getInput("test_input.txt");
        battle = new Battlefield(input);
    }

    @Test
    public void test_width() {
        assertEquals(7, battle.getWidth());
    }

    @Test
    public void test_height() {
        assertEquals(5, battle.getHeight());
    }

    @Test
    public void test_readField() {
        String init = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        assertEquals(init, battle.toString());
    }

    @Test
    public void test_runRound() {
        battle.runRound();
    }


}

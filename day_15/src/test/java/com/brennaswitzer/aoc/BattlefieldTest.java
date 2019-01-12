package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BattlefieldTest {

    @Before
    public void setup() throws IOException {
    }

    @Test
    public void test_width() {
        String s = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        assertEquals(7, battle.getWidth());
    }

    @Test
    public void test_height() {
        String s = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        assertEquals(5, battle.getHeight());
    }

    @Test
    public void test_readField() {
        String init = "#######\n" +
                "#E..G.#\n" +
                "#...#.#\n" +
                "#.G.#G#\n" +
                "#######";
        Battlefield battle = new Battlefield(init);
        assertEquals(init, battle.toString());
    }

    @Test
    public void test_runRound_step1() {
        String n = "#########\n" +
                "#.G...G.#\n" +
                "#...G...#\n" +
                "#...E..G#\n" +
                "#.G.....#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#########";

        String s = "#########\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..E..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#########";
        Battlefield battle = new Battlefield(s);
        for (int i = 0; i < 1; i++) {
            battle.runRound();
        }
        assertEquals(n, battle.toString());
    }

    @Test
    public void test_runRound_step2() {
        String n = "#########\n" +
                "#..G.G..#\n" +
                "#...G...#\n" +
                "#.G.E.G.#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########";

        String s = "#########\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..E..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#########";
        Battlefield battle = new Battlefield(s);
        for (int i = 0; i < 2; i++) {
            battle.runRound();
        }
        assertEquals(n, battle.toString());
    }

    @Test
    public void test_runRound_step3() {
        String n = "#########\n" +
                "#.......#\n" +
                "#..GGG..#\n" +
                "#..GEG..#\n" +
                "#G..G...#\n" +
                "#......G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#########";

        String s = "#########\n" +
                "#G..G..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..E..G#\n" +
                "#.......#\n" +
                "#.......#\n" +
                "#G..G..G#\n" +
                "#########";
        Battlefield battle = new Battlefield(s);
        for (int i = 0; i < 3; i++) {
            battle.runRound();
        }
        assertEquals(n, battle.toString());
    }

    @Test
    public void test_ex1_r1() {
        String progress = "" +
                "#######\n" +
                "#..G..#\n" +
                "#...EG#\n" +
                "#.#G#G#\n" +
                "#...#E#\n" +
                "#.....#\n" +
                "#######";

        String s = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        for (int i = 0; i < 1; i++) {
            battle.runRound();
        }
        assertEquals(progress, battle.toString());
    }

    @Test
    public void test_outcome() {
        String s = "#######\n" +
                "#.G...#\n" +
                "#...EG#\n" +
                "#.#.#G#\n" +
                "#..G#E#\n" +
                "#.....#\n" +
                "#######";
        Battlefield battle = new Battlefield(s);
        while (!battle.isOver()) {
            battle.runRound();
        }
        assertEquals(27730, battle.outcome());
    }


}

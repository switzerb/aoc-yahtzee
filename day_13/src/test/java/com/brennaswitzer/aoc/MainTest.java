package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    Track track;
    Track loop;
    Track collision;

    @Before
    public void setUp() throws IOException {
        List<String> lines = Loader.getInput("test_input.txt");
        track = new Track(lines);

        List<String> looper = Loader.getInput("test_loop.txt");
        loop = new Track(looper);

        List<String> coll = Loader.getInput("test_collision.txt");
        collision = new Track(coll);
    }

    @Test
    public void test_p1_ex1() {
        String snapshot = "/->-\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | v  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/   ";
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex2() {
        String snapshot = "/-->\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\->--/\n" +
                "  \\------/   ";
        track.tick(1);
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex3() {
        String snapshot = "/---v        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+>-/\n" +
                "  \\------/   ";
        track.tick(2);
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex4() {
        String snapshot = "/---\\        \n" +
                "|   v  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+->/\n" +
                "  \\------/   ";
        track.tick(3);
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex5() {
        String snapshot = "/---\\        \n" +
                "|   |  /----\\\n" +
                "| /->--+-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  \\-+--^\n" +
                "  \\------/   ";
        track.tick(4);
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex6() {
        String snapshot = "/---\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--v-\\  |\n" +
                "| | |  | |  |\n" +
                "\\-+-/  ^-+--/\n" +
                "  \\------/   ";
        track.tick(13);
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex7() {
        String snapshot = "/---\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  X |  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/   ";
        track.tick(14);
        assertEquals(snapshot, track.toString());
    }

    @Test
    public void test_p1_ex8() {
        track.tick(14);
        assertEquals("7,3", track.firstCollision());
    }


    @Test
    public void test_loop() {
        String snapshot = "/->-\\\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickOne() {
        String snapshot = "/-->\\\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        loop.tick(1);
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickTwo() {
        String snapshot = "/---v\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        loop.tick(2);
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickThree() {
        String snapshot = ">---\\\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        loop.tick(14);
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_tickCollisionStart() {
        String snapshot = "/---\\\n" +
                "^   |\n" +
                "|   |\n" +
                "v   |\n" +
                "\\---/";
        assertEquals(snapshot, collision.toString());
    }

    @Test
    public void test_tickCollisionEnd() {
        String snapshot = "/---\\\n" +
                "|   |\n" +
                "|   X\n" +
                "|   |\n" +
                "\\---/";
        collision.tick(7);
        assertEquals(snapshot, collision.toString());
    }

    @Test
    public void test_getCollisionPosition() {
        collision.tick(8);
        assertEquals("4,2", collision.firstCollision());
    }

}

package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    Track track;
    Track loop;

    @Before
    public void setUp() throws IOException {
        List<String> lines = Loader.getInput("test_input.txt");
        track = new Track(lines);

        List<String> looper = Loader.getInput("test_loop.txt");
        loop = new Track(looper);
    }

    @Test
    public void test_p1_ex1() {
        String snapshot = "/->-\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | v  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/   ";
        System.out.println(track);
        assertEquals(snapshot, track.toString());
    }

//    @Test
//    public void test_p1_ex2() {
//        String snapshot = "/-->\\        \n" +
//                "|   |  /----\\\n" +
//                "| /-+--+-\\  |\n" +
//                "| | |  | |  |\n" +
//                "\\-+-/  \\->--/\n" +
//                "  \\------/   ";
//        System.out.println(track);
//        track.tick(1);
//        assertEquals(snapshot, track.toString());
//    }

    @Test
    public void test_loop() {
        String snapshot = "/->-\\\n" +
                "|   |\n" +
                "|   |\n" +
                "|   |\n" +
                "\\---/";
        System.out.println(loop);
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
        System.out.println(loop);
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
        System.out.println(loop);
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
        System.out.println(loop);
        assertEquals(snapshot, loop.toString());
    }

    @Test
    public void test_run() {
        loop.tick(14);
    }

}

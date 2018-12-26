package com.brennaswitzer.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    NorthPole pole = new NorthPole();
    
    @Test
    public void test_p1_ex1() {
        String test = "#####\n" +
                      "#.|.#\n" +
                      "#|###\n" +
                      "#.|X#\n" +
                      "#####";
        pole.traverseMap("WNE", new Point(0,0));
        assertEquals(test, pole.toString());
    }

    @Test
    public void test_p1_ex2() {
        String example = "#########\n" +
                          "#.|.|.|.#\n" +
                          "#|#######\n" +
                          "#.|.|.|.#\n" +
                          "#|#####|#\n" +
                          "#.#.#X|.#\n" +
                          "#|#|#####\n" +
                          "#.|.|.|.#\n" +
                          "#########";
        pole.traverseMap("ENWWW(NEEE|SSE(EE|N))", new Point(0,0));
        System.out.println(pole);
        assertEquals(example, pole.toString());
    }

}

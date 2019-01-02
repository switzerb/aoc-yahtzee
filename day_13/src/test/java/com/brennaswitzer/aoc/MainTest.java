package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    Track track;

    @Before
    public void setUp() throws IOException {
        List<String> lines = Loader.getInput("test_input.txt");
        track = new Track(lines);
    }

    @Test
    public void test_p1_ex1() {
        System.out.println(track);
    }

}

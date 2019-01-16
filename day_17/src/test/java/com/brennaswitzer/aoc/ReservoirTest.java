package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ReservoirTest {

    Reservoir reservoir;

    @Before
    public void setup() throws IOException {
        List<String> input = Loader.getInput("test_input.txt");
        reservoir = new Reservoir();
    }

    @Test
    public void test_printGround() {
        System.out.println(reservoir);
    }


}

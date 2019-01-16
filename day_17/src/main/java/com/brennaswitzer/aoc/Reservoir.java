package com.brennaswitzer.aoc;

import java.util.Arrays;

public class Reservoir {

    final char CLAY = '#';
    final char SAND = '.';
    final char SPRING = '+';

    int width = 10;
    int height = 10;

    char[][] ground = new char[width][];

    Reservoir() {
        // Start with all sand
        for (int y = 0; y < height; y++) {
            ground[y] = new char[width];
            Arrays.fill(ground[y], SAND);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < width; x++) {
            if (x > 0) sb.append('\n');
            for (int y = 0; y < height; y++) {
                sb.append(ground[x][y]);
            }
        }
        return sb.toString();
    }
}

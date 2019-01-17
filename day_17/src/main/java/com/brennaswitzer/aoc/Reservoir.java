package com.brennaswitzer.aoc;

import java.util.Arrays;

public class Reservoir {

    final char CLAY = '#';
    final char SAND = '.';
    final char SPRING = '+';
    final char WATER = '|';

    int width;
    int height;
    char[][] ground;

    // Map data structure to hold water and clay positions
    // Key = Position and Value = WATER / CLAY

    Reservoir(int width, int height) {
        ground = new char[height][];
        this.width = width;
        this.height = height;

        // Start with all sand
        for (int y = 0; y < height; y++) {
            ground[y] = new char[width];
            Arrays.fill(ground[y], SAND);
        }
        set(10, 0, SPRING);
        set(10, 1, WATER);
        set(10, 2, WATER);

        for (int i = 2; i < 7; i++) {
            set(1, i, CLAY);
        }

        for (int i = 3; i < 7; i++) {
            set(12, i, CLAY);
        }

        for (int i = 1; i < 13; i++) {
            set(i, 7, CLAY);
        }

    }

    /**
     * Rules for water flow:
     * it always runs straight down until it hits clay (#)
     * water flows to the left and right until it reaches the top of the "container"
     * water can flow to either side
     */


    private void set(int x, int y, char c) {
        ground[y][x] = c;
    }

    private char get(int x, int y) {
        return ground[y][x];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < height; x++) {
            if (x > 0) sb.append('\n');
            for (int y = 0; y < width; y++) {
                sb.append(ground[x][y]);
            }
        }
        return sb.toString();
    }
}
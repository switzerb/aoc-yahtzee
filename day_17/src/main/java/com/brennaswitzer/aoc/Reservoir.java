package com.brennaswitzer.aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Reservoir {

    final char CLAY = '#';
    final char SAND = '.';
    final char SPRING = '+';
    final char WATER = '|';

    int width;
    int height;
    char[][] ground;

    Map<Point, Character> veins = new HashMap<>();

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

    void flow(Point start) {
        System.out.println(start);
    }


    private void set(int row, int col, char c) {
        veins.put(new Point(col, row), c);
    }

    private char get(int row, int col) {
        return veins.get(new Point(col, row));
    }

    private boolean isClay(int row, int col) {
        Point p = new Point(col, row);
        return veins.containsKey(p) && veins.get(p) == CLAY;
    }

    private boolean isWater(int row, int col) {
        Point p = new Point(col, row);
        return veins.containsKey(p) && veins.get(p) == WATER;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < height; x++) {
            if (x > 0) sb.append('\n');
            for (int y = 0; y < width; y++) {
                if (isClay(y, x)) {
                    sb.append(get(y, x));
                } else if (isWater(y, x)) {
                    sb.append('|');
                } else {
                    sb.append('.');
                }
            }
        }
        return sb.toString();
    }
}
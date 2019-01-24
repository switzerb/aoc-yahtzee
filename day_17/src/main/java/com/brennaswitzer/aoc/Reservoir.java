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
    char[][] visualizer;

    Map<Point, Character> ground = new HashMap<>();

    // Map data structure to hold water and clay positions
    // Key = Position and Value = WATER / CLAY

    Reservoir(int width, int height) {
        visualizer = new char[height][];
        this.width = width;
        this.height = height;

        // Start with all sand
        for (int y = 0; y < height; y++) {
            visualizer[y] = new char[width];
            Arrays.fill(visualizer[y], SAND);
        }
        set(10, 0, SPRING);

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
        Character meter = ground.get(start);
        Point current = start.go(Direction.South);
        while (!isClay(current)) {
            current = current.go(Direction.South);
            System.out.println(current);
        }
        ground.put(current, WATER);
    }


    private void set(int row, int col, char c) {
        ground.put(new Point(col, row), c);
    }

    private char get(int row, int col) {
        return ground.get(new Point(col, row));
    }

    private boolean isClay(Point p) {
        return ground.containsKey(p) && ground.get(p) == CLAY;
    }

    private boolean isClay(int row, int col) {
        Point p = new Point(col, row);
        return ground.containsKey(p) && ground.get(p) == CLAY;
    }

    private boolean isWater(int row, int col) {
        Point p = new Point(col, row);
        return ground.containsKey(p) && ground.get(p) == WATER;
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
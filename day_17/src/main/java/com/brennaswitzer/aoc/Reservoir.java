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
        set(10, 0, WATER);
//
//        for (int i = 2; i < 7; i++) {
//            set(1, i, CLAY);
//        }
//
//        for (int i = 3; i < 7; i++) {
//            set(12, i, CLAY);
//        }
//
//        for (int i = 1; i < 13; i++) {
//            set(i, 7, CLAY);
//        }

    }

    /**
     * Rules for water flow:
     * it always runs straight down until it hits clay (#)
     * water flows to the left and right until it reaches the top of the "container"
     * water can flow to either side
     */

    void flow(Point start) {
//        Character meter = ground.get(start);
//        Point current = start.go(Direction.South);
//        int i = 0;
//        while (i < 5) {
//            current = current.go(Direction.South);
//            System.out.println(current);
//            i++;
//        }
//        ground.put(current, WATER);
    }


    private void set(int x, int y, char c) {
        ground.put(new Point(x, y), c);
    }

    private char get(int x, int y) {
        return ground.get(new Point(x, y));
    }

    private boolean isClay(Point p) {
        return ground.containsKey(p) && ground.get(p) == CLAY;
    }

    private boolean isClay(int x, int y) {
        Point p = new Point(x, y);
        return ground.containsKey(p) && ground.get(p) == CLAY;
    }

    private boolean isWater(int x, int y) {
        Point p = new Point(x, y);
        return ground.containsKey(p) && ground.get(p) == WATER;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            if (y > 0) sb.append('\n');
            for (int x = 0; x < width; x++) {
                if (isClay(x, y)) {
                    sb.append(get(x, y));
                } else if (isWater(x, y)) {
                    sb.append('|');
                } else {
                    sb.append('.');
                }
            }
        }
        return sb.toString();
    }
}
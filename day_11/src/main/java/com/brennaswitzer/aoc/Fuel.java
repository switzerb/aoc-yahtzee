package com.brennaswitzer.aoc;

public class Fuel {

    int[][] grid = new int[300][300];
    int SERIAL;

    Fuel(int serial) {
        this.SERIAL = serial;
    }

    void buildCells() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                grid[x][y] = getPowerLevel(x, y);
            }
        }
    }

    int largestCellPower() {
        return 0;
    }

    int get3x3Total(int top, int left) {
        int sum = 0;
        for (int x = top; x < top + 3; x++) {
            for (int y = left; y < left + 3; y++) {
                sum += grid[x][y];
            }
        }
        return sum;
    }

    int getPowerLevel(int x, int y) {
        int rackID = getRackID(x);
        return hundreds(rackID * ((rackID * y) + SERIAL)) - 5;
    }

    int getRackID(int x) {
        return x + 10;
    }

    int hundreds(int value) {
        String v = String.valueOf(value);
        String[] split = v.split("");
        if (split.length >= 3) {
            return Integer.parseInt(split[split.length - 3]);
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < grid.length; x++) {
            sb.append("\n");
            for (int y = 0; y < grid.length; y++) {
                sb.append(grid[x][y]).append(" ");
            }
        }
        return sb.toString();
    }
}

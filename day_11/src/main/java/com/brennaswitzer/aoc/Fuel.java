package com.brennaswitzer.aoc;

public class Fuel {

    int[][] grid = new int[300][300];
    int SERIAL;

    Fuel(int serial) {
        this.SERIAL = serial;
    }

    int largestCellPower() {
        return 0;
    }

    int getPowerLevel(int x, int y) {
        int rackID = getRackID(x);
        int init = initLevel(rackID, y) + SERIAL;
        int levelUp = rackID * init;
        int throttle = hundreds(levelUp) - 5;
        return throttle;
    }

    int getRackID(int x) {
        return x + 10;
    }

    int initLevel(int rackID, int y) {
        return rackID * y;
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


}

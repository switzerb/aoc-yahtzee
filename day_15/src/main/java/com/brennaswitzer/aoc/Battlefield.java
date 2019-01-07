package com.brennaswitzer.aoc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battlefield {

    // map of the battlefield grid
    // govern the behavior and position of points
    // make sure we can get reading order for anything, like open squares and combatants

    char[][] battlefield;
    int width;
    int height;

    Battlefield(List<String> input) {
        height = input.size();
        battlefield = new char[height][];
        for (int i = 0; i < height; i++) {
            char[] b = input.get(i).toCharArray();
            width = b.length;
            battlefield[i] = Arrays.copyOf(b, width);
        }
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    List<Position> sortReadingOrder() {
        return new ArrayList<>();
    }

    /**
     * Get information about those spaces immediately adjacent to a Unit. It will always return a list of zero to four units.
     *
     * @param unit
     * @return
     */
    List<Unit> getAdjacent(Position unit) {
        return new ArrayList<>();
    }

    /**
     * The outcome of the battle: the number of full rounds that were completed (not counting the round in which combat ends)
     * multiplied by the sum of the hit points of all remaining units at the moment combat ends.
     * (Combat only ends when a unit finds no targets during its turn.)
     *
     * @return
     */
    int outcome() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < height; r++) {
            sb.append('\n');
            for (int c = 0; c < width; c++) {
                sb.append(battlefield[r][c]);
            }
        }
        return sb.toString();
    }

    class Position {

        private final java.awt.Point p;

        Position() {
            p = new Point();
        }

        public Position(Position n) {
            p = new java.awt.Point(n.getCol(), n.getRow());
        }

        public Position(int row, int col) {
            p = new java.awt.Point(col, row);
        }

        public int getRow() {
            return p.y;
        }

        public int getCol() {
            return p.x;
        }

    }
}

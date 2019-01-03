package com.brennaswitzer.aoc;

import java.awt.*;

public enum Direction {
    NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

    int row_delta, col_delta;

    Direction(int r, int c) {
        this.row_delta = r;
        this.col_delta = c;
    }

    Point straight(Point p) {
        return new Point(p.x + col_delta, p.y + row_delta);
    }

    Point corner(Point p) {
        if (this == EAST) {
            return new Point(p.x + col_delta, p.y + row_delta);
        } else {
            return new Point(p.x + col_delta, p.y + row_delta);
        }
    }


    public Direction turn(char track) {
        if (track == '\\') {
            return this == EAST ? SOUTH : NORTH;
        }
        if (track == '/') {
            return this == NORTH ? EAST : WEST;
        }
        throw new RuntimeException("That is not a track I recognize.");
    }
}
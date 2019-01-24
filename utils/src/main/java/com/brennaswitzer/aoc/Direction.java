package com.brennaswitzer.aoc;

public enum Direction {
    North(-1, 0), South(1, 0), East(0, 1), West(0, -1);

    int row_delta, col_delta;

    Direction(int r, int c) {
        this.row_delta = r;
        this.col_delta = c;
    }

    Point move(Point p) {
        return new Point(p.getRow() + row_delta, p.getCol() + col_delta);
    }

}
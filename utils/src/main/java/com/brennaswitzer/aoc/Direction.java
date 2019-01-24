package com.brennaswitzer.aoc;

public enum Direction {
    North(0, 1), South(0, -1), East(1, 0), West(-1, 0);

    int y_delta, x_delta;

    Direction(int x, int y) {
        this.y_delta = y;
        this.x_delta = x;
    }

    Point move(Point p) {
        return new Point(p.getX() + x_delta, p.getY() + y_delta);
    }

}
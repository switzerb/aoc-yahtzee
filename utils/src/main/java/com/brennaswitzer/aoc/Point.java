package com.brennaswitzer.aoc;

public class Point {

    private final java.awt.Point p;

    public Point(Point n) {
        p = new java.awt.Point(n.getX(), n.getY());
    }

    public Point(int x, int y) {
        p = new java.awt.Point(x, y);
    }

    private int getY() {
        return p.y;
    }

    private int getX() {
        return p.x;
    }


}

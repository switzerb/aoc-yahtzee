package com.brennaswitzer.aoc;

public class Point implements Comparable<Point> {

    private final java.awt.Point p;

    public Point(Point n) {
        p = new java.awt.Point(n.getX(), n.getY());
    }

    public Point(int x, int y) {
        p = new java.awt.Point(x, y);
    }

    public Point go(Direction dir) {
        return dir.move(this);
    }

    public int getRow() {
        return p.y;
    }

    public int getCol() {
        return p.x;
    }

    public int getY() {
        return p.y;
    }

    public int getX() {
        return p.x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return p.equals(point.p);
    }

    @Override
    public int hashCode() {
        return p.hashCode();
    }

    @Override
    public int compareTo(Point o) {
        int c = p.y - o.p.y;
        if (c != 0) return c;
        return p.x - o.p.x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + p.y + "," + p.x + "]");
        return sb.toString();
    }



}

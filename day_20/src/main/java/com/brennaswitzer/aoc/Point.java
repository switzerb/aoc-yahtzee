package com.brennaswitzer.aoc;

public class Point implements Comparable<Point> {
    
    private final java.awt.Point p;
    
    public Point(Point n) {
        p = new java.awt.Point(n.getCol(), n.getRow());
    }
    
    public Point(int row, int col) {
        p = new java.awt.Point(col, row);
    }
    
    public int getRow() {
        return p.y;
    }
    
    public int getCol() {
        return p.x;
    }
    
    public void setLocation(int row, int col) {
        this.p.y = row;
        this.p.x = col;
    }
    
    public void setLocation(Point point) {
        this.p.y = point.p.y;
        this.p.x = point.p.x;
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

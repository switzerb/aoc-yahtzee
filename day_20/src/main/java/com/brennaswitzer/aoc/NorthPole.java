package com.brennaswitzer.aoc;

import java.util.*;
import java.util.stream.Collectors;

public class NorthPole {
    
    HashMap<Point, Character> facility = new HashMap<>();
    Point current = new Point(0, 0);
    
    NorthPole() {
        start();
    }
    
    void start() {
        Point start = new Point(0, 0);
        current.setLocation(start);
        facility.put(start, 'X');
    }
    
    void mapRoute(String route) {
        String[] dirs = route.split("");
        for (String d : dirs) {
            if (d.equals("W")) {
                mapWest();
            }
            if (d.equals("E")) {
                mapEast();
            }
            if (d.equals("N")) {
                mapNorth();
            }
        }
    }
    
    void mapWest() {
        Point door = new Point( current.getRow(), current.getCol() - 1);
        Point room = new Point( current.getRow(), current.getCol() - 2);
        facility.put(door, '|');
        facility.put(room, '.');
        current.setLocation(room);
    }
    
    void mapEast() {
        Point door = new Point( current.getRow(), current.getCol() + 1);
        Point room = new Point( current.getRow(), current.getCol() + 2);
        facility.put(door, '|');
        facility.put(room, '.');
        current.setLocation(room);
    }
    
    void mapNorth() {
        Point door = new Point( current.getRow() - 1, current.getCol());
        Point room = new Point( current.getRow() - 2, current.getCol());
        facility.put(door, '-');
        facility.put(room, '.');
        current.setLocation(room);
    }
    
    void mapSouth() {
        Point door = new Point( current.getRow() + 1, current.getCol());
        Point room = new Point( current.getRow() + 2, current.getCol());
        facility.put(door, '-');
        facility.put(room, '.');
        current.setLocation(room);
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        List<Point> sortedKeys = new ArrayList<>(facility.keySet());
        Collections.sort(sortedKeys);
        
        Point upperLeft = sortedKeys.get(0);
        Point lowerRight = sortedKeys.get(sortedKeys.size() - 1);
        
        for (int r = upperLeft.getRow() - 1 ; r <= lowerRight.getRow() + 1; r++) {
            sb.append("\n");
            for (int c = upperLeft.getCol() - 1; c <= lowerRight.getCol() + 1; c++) {
                Point p = new Point(r, c);
                if (facility.containsKey(p)) {
                    sb.append(facility.get(p));
                } else {
                    sb.append("#");
                }
            }
        }

        return sb.toString();
    }
    
    public class Point implements Comparable<Point> {
        
        private final java.awt.Point p;
        
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
}

package com.brennaswitzer.aoc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NorthPole {
    
    HashMap<Point, Character> facility = new HashMap<>();
    Point current = new Point(0,0);
    
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
            System.out.println(current);
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
    }
    
    void mapEast() {
    }
    
    void mapNorth() {
    }
    
    void mapSouth() {
    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Point, Character>> entries = facility.entrySet();

//        for (int i = -10; i < 10; i++) {
//            sb.append("\n");
//            for (int j = -10; j < 10; j++) {
//                Point p = new Point(i, j);
//                if (facility.containsKey(p)) {
//                    System.out.println(facility.get(p));
////                    sb.append(facility.get(p));
//                } else {
////                    sb.append("?");
//                }
//            }
//        }

//
//        for (Map.Entry<Point, Character> point : entries) {
//            sb.append(point.getValue());
//        }
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

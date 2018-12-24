package com.brennaswitzer.aoc;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NorthPole {
    
    HashMap<Point, Character> facility = new HashMap<>();
    Point current = new Point();
    
    NorthPole() {
        start();
    }
    
    void start() {
        Point start = new Point(0, 0);
        facility.put(start, 'X');
    }
    
    void mapRoute(String route) {
        String[] dirs = route.split("");
        for (String d : dirs) {
            if (d.equals("W")) {
                mapWest();
            }
            if(d.equals("E")) {
                mapEast();
            }
            if(d.equals("N")){
                mapNorth();
            }
        }
    }
    
    void mapWest() {
        Point tail = current.getLocation();
        tail.setLocation(tail.getX() + -1,  tail.getY() + 0);
        facility.put(tail, '|');
        
        tail.setLocation(tail.getX() + -1,  tail.getY() + 0);
        facility.put(tail, '.');
        
        System.out.println(tail);
    }
    
    void mapEast() {
        Point tail = current.getLocation();
        tail.setLocation(tail.getX() + 1,  tail.getY() + 0);
        facility.put(tail, '|');
    
        tail.setLocation(tail.getX() + 1,  tail.getY() + 0);
        facility.put(tail, '.');
    }
    
    void mapNorth() {
        Point tail = current.getLocation();
        tail.setLocation(tail.getX() + 0,  tail.getY() + -1);
        facility.put(tail, '|');
    
        tail.setLocation(tail.getX() + 0,  tail.getY() + -1);
        facility.put(tail, '.');
    
    }
    
    void mapSouth() {
    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Point, Character>> entries = facility.entrySet();
        
        for (Map.Entry<Point, Character> point : entries) {
            sb.append(point.getValue());
        }
        return sb.toString();
    }
    
}

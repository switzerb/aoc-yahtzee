package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NorthPole {
    
    HashMap<Point, Character> facility = new HashMap<>();
    Point start = new Point(0, 0);
    
    NorthPole() {
        facility.put(start, 'X');
    }
    
    void markCorners(Point p) {
        facility.put(p.go(Direction.North).go(Direction.West), '#');
        facility.put(p.go(Direction.North).go(Direction.East), '#');
        facility.put(p.go(Direction.South).go(Direction.West), '#');
        facility.put(p.go(Direction.South).go(Direction.East), '#');
    }
    
    public enum Direction {
        North(-1, 0), South(1, 0), East(0, 1), West(0, -1);
        
        int row_delta, col_delta;
        
        Direction(int r, int c) {
            this.row_delta = r;
            this.col_delta = c;
        }
        
        public static Direction parse(char dir) {
            if (dir == 'W') {
                return Direction.West;
            }
            if (dir == 'E') {
                return Direction.East;
            }
            if (dir == 'N') {
                return Direction.North;
            }
            if (dir == 'S') {
                return Direction.South;
            }
            throw new IllegalArgumentException("Unrecognized direction.");
        }
        
        Point move(int distance, Point p) {
            return new Point(p.getRow() + row_delta * distance, p.getCol() + col_delta * distance);
        }
        
    }
    
    Point mapDirection(Direction direction, Point X) {
        Point door = direction.move(1, X);
        Point room = direction.move(2, X);
        
        facility.put(door, '|');
        facility.put(room, '.');
        markCorners(room);
        return room;
    }
    
    void traverseMap(String directions, Point start) {
        Point current = start;
        
        for (char c : directions.toCharArray()) {
            if (c == '(') {
                // open parens is start of a branch
                int open_p = directions.indexOf("(");
                traverseMap(directions.substring(open_p + 1), start);
                
            } else if (c == ')') {
                // end parens is end of an expression
                
                
            } else if (c == '|') {
            
            
            } else {
                Direction direction = Direction.parse(c);
                current = mapDirection(direction, current);
            }
            
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Point> keys = new ArrayList<>(facility.keySet());
        int max_row = Integer.MIN_VALUE;
        int max_col = Integer.MIN_VALUE;
        int min_row = Integer.MAX_VALUE;
        int min_col = Integer.MAX_VALUE;
        
        for (Point p : keys) {
            max_row = Math.max(max_row, p.getRow());
            min_row = Math.min(min_row, p.getRow());
            max_col = Math.max(max_col, p.getCol());
            min_col = Math.min(min_col, p.getCol());
        }
        
        for (int r = min_row; r <= max_row; r++) {
            if (r != min_row) {
                sb.append("\n");
            }
            for (int c = min_col; c <= max_col; c++) {
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
    
}

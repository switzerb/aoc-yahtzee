package com.brennaswitzer.aoc;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Track {

    private char[][] track;
    private List<Cart> carts = new ArrayList<>();
    private int width;
    private int height;
    private Point firstCollision = new Point();

    Track(List<String> lines) {
        width = getWidth(lines);
        height = lines.size();
        track = new char[height][width];

        int col, row = 0;

        for (String line : lines) {
            track[row] = new char[width];
            Arrays.fill(track[row], ' ');
            char[] chrs = line.toCharArray();

            col = 0;
            for (char c : chrs) {
                track[row][col] = c;

                if (c == '>') {
                    carts.add(new Cart(col, row, Direction.EAST));
                    track[row][col] = '-';
                }

                if (c == 'v') {
                    carts.add(new Cart(col, row, Direction.SOUTH));
                    track[row][col] = '|';
                }

                if (c == '<') {
                    carts.add(new Cart(col, row, Direction.WEST));
                    track[row][col] = '-';
                }

                if (c == '^') {
                    carts.add(new Cart(col, row, Direction.NORTH));
                    track[row][col] = '|';
                }
                col++;
            }
            row++;
        }

    }

    private int getWidth(List<String> lines) {
        int width = 0;
        for (String l : lines) {
            char[] row = l.toCharArray();
            width = Math.max(width, row.length);
        }
        return width;
    }

    char getNext(Direction dir, Point current) {
        if (dir == Direction.EAST) {
            return track[current.y][current.x + 1];
        }
        if (dir == Direction.SOUTH) {
            return track[current.y + 1][current.x];
        }
        if (dir == Direction.WEST) {
            return track[current.y][current.x - 1];
        }
        if (dir == Direction.NORTH) {
            return track[current.y - 1][current.x];
        }
        throw new IllegalArgumentException("Cart unable to move that direction");
    }

    private boolean step() {
        // sort list of carts by row and column
        for (Cart c : carts) {
            Direction dir = c.getDirection();
            Point current = c.getCurrent();

            char next = getNext(dir, current);
            c.move(next);

            if (hasCollision()) {
                c.wreck();
                firstCollision = c.getCurrent();
                return true;
            }
        }
        return false;
    }

    public void tick() {
        boolean crash = false;
        int seconds = 0;
        while (!crash) {
            crash = step();
            seconds++;
        }
    }

    public void tick(int steps) {
        boolean stop = false;
        for (int i = 0; i < steps; i++) {
            stop = step();
            if (stop) break;
        }
    }

    public String firstCollision() {
        return firstCollision.x + "," + firstCollision.y;
    }

    public boolean hasCollision() {
        Map<Point, Cart> positions = new HashMap<>();

        for (Cart c : carts) {
            if (positions.containsKey(c.getCurrent())) {
                return true;
            } else {
                positions.put(c.getCurrent(), c);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Map<Point, Cart> positions = new HashMap<>();

        for (Cart c : carts) {
            positions.put(c.getCurrent(), c);
        }

        for (int row = 0; row < height; row++) {
            if (row > 0) {
                sb.append('\n');
            }
            for (int col = 0; col < width; col++) {
                Point location = new Point(col, row);
                if (positions.containsKey(location)) {
                    Cart cart = positions.get(location);
                    sb.append(cart.toString());
                } else {
                    sb.append(track[row][col]);
                }
            }
        }
        return sb.toString();
    }

}

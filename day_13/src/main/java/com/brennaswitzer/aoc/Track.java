package com.brennaswitzer.aoc;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Track {

    private char[][] track;
    private List<Cart> carts = new ArrayList<>();
    private int width;
    private int height;
    private List<Point> collisions = new ArrayList<>();

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

    public static Comparator<Cart> SORT_BY_LOCATION = new Comparator<Cart>() {
        @Override
        public int compare(Cart c1, Cart c2) {
            int x1 = c1.position.x;
            int x2 = c2.position.x;
            int y1 = c1.position.y;
            int y2 = c2.position.y;

            if (y1 < y2) return -1;
            if (y1 > y2) return 1;
            return Integer.compare(x1, x2);
        }
    };

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

    public void step() {
        carts.sort(SORT_BY_LOCATION);
        for (Cart c : carts) {
            Direction dir = c.getDirection();
            Point current = c.getCurrent();

            char next = getNext(dir, current);
            c.move(next);

            if (hasCollision()) {
                collisions.add(new Point(c.getCurrent()));
            }
        }
    }

    public String tick() {
        while (carts.size() > 1) {
            step();
            carts = getRemaining(carts);
        }
        Point last = carts.get(0).getCurrent();
        return last.x + "," + last.y;
    }

    public void tick(int steps) {
        for (int i = 0; i < steps; i++) {
            step();
            carts = getRemaining(carts);
        }
    }

    public String firstCollision() {
        Point first = collisions.get(0);
        return first.x + "," + first.y;
    }

    public List<Cart> getRemaining(List<Cart> carts) {
        List<Cart> remaining = new ArrayList<>();
        Map<Point, Cart> positions = new HashMap<>();

        for (Cart c : carts) {
            if (positions.containsKey(c.getCurrent())) {
                remaining.remove(positions.get(c.getCurrent()));
                positions.remove(c.getCurrent());
            } else {
                remaining.add(c);
                positions.put(c.getCurrent(), c);
            }
        }
        return remaining;
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

    public String withCollisions() {
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

                if (collisions.size() > 0 && collisions.get(0).equals(location)) {
                    sb.append("X");
                } else if (positions.containsKey(location)) {
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

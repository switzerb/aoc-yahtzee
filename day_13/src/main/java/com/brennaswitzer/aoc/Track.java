package com.brennaswitzer.aoc;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Track {

    private char[][] track;
    private List<Cart> carts = new ArrayList<>();
    private int width;
    private int height;

    Track(List<String> lines) {
        width = getWidth(lines);
        height = lines.size();
        track = new char[height][width];

        int col, row = 0;

        // write our input to the array so we can iterate over the track
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
        if(dir == Direction.EAST) {
            return track[current.y][current.x + 1];
        }
        if(dir == Direction.SOUTH) {
            return track[current.y + 1][current.x];
        }
        if(dir == Direction.WEST) {
            return track[current.y][current.x - 1];
        }
        if(dir == Direction.NORTH) {
            return track[current.y - 1][current.x];
        }
        throw new IllegalArgumentException("Cart unable to move that direction");
    }

    public void tick(int steps) {

        for(int i = 0; i < steps; i++) {
            for(Cart c : carts) {
                Direction dir = c.getDirection();
                Point current = c.getCurrent();

                char next = getNext(dir, current);
                c.move(next);
                //when the carts move, we need to sort the list of carts to make sure we are looping through by row, col correctly
            }
            System.out.println(this);
            System.out.println('\n');
        }

        // for each cart in carts
        // what is the direction it is facing?
        // what are the possible moves?
        // switch \ / + - | all imply a direction
        // it is only at an intersection that the carts have a choice of which way to turn
        // the intersection requires knowledge of what the cart has done previously
        // move carts in the direction that they are pointing one step

        // if two carts have the same current location, they produce a collision X and an answer to the puzzle
        // while not collision, get points and see if there are any the same

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Map<Point, Cart> positions = new HashMap<>();

        for(Cart c : carts) {
            positions.put(c.getCurrent(), c);
        }

        for (int row = 0; row < height; row++) {
            if (row > 0) {
                sb.append('\n');
            }
            for (int col = 0; col < width; col++) {
                Point location = new Point(col, row);
                if(positions.containsKey(location)) {
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

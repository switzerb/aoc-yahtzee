package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.Arrays;
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

        int X, Y = 0;

        // write our input to the array so we can iterate over the track
        for (String line : lines) {
            track[Y] = new char[width];
            Arrays.fill(track[Y], ' ');
            char[] chrs = line.toCharArray();

            X = 0;
            for (char c : chrs) {
                track[Y][X] = c;
                X++;
            }
            Y++;
        }

        // initialize starting places of the carts
        placeCarts();
    }

    void placeCarts() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                if (track[row][col] == '>') {
                    carts.add(new Cart(col, row, Direction.RIGHT));
                }

                if (track[row][col] == 'v') {
                    carts.add(new Cart(col, row, Direction.DOWN));
                }

                if (track[row][col] == '<') {
                    carts.add(new Cart(col, row, Direction.LEFT));
                }

                if (track[row][col] == '^') {
                    carts.add(new Cart(col, row, Direction.UP));
                }
            }
        }
    }

    int getWidth(List<String> lines) {
        int width = 0;
        for (String l : lines) {
            char[] row = l.toCharArray();
            width = Math.max(width, row.length);
        }
        return width;
    }

    public void tick(int step) {

        for(Cart c : carts) {
            c.move();

            //when the carts move, we need to sort the list of carts to make sure we are looping through by row, col correctly
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

        for (int y = 0; y < height; y++) {
            if (y > 0) {
                sb.append('\n');
            }
            for (int x = 0; x < width; x++) {
                sb.append(track[y][x]);
            }
        }
        return sb.toString();
    }

}

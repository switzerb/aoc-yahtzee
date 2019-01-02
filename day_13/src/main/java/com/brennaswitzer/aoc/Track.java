package com.brennaswitzer.aoc;

import java.util.Arrays;
import java.util.List;

public class Track {

    private char[][] track;
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
    }

    int getHeight() {
        return height;
    }

    int getWidth(List<String> lines) {
        int width = 0;
        for (String l : lines) {
            char[] row = l.toCharArray();
            width = Math.max(width, row.length);
        }
        return width;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            if( y > 0 ) { sb.append('\n');}
            for (int x = 0; x < width; x++) {
                sb.append(track[y][x]);
            }
        }
        return sb.toString();
    }
}

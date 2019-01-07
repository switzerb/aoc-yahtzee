package com.brennaswitzer.aoc;

import java.awt.*;

class Position {

    private final java.awt.Point p;

    Position() {
        p = new Point();
    }

    public Position(Position n) {
        p = new java.awt.Point(n.getCol(), n.getRow());
    }

    public Position(int row, int col) {
        p = new java.awt.Point(col, row);
    }

    public int getRow() {
        return p.y;
    }

    public int getCol() {
        return p.x;
    }

}

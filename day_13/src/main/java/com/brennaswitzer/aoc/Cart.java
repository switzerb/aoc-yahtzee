package com.brennaswitzer.aoc;

import java.awt.*;


public class Cart {

    // carts have direction, and keep track of their state of the last turn they took (left, straight, right, left straight, right, etc.)

    Point position;
    Direction direction;
    Turn rotation = Turn.LEFT;

    Cart(int x, int y, Direction dir) {
        position = new Point(x, y);
        direction = dir;
    }

    Point getCurrent() {
        return position;
    }

    void move() {

        // it moves according to the track that is right in "front" of it, which will depend on direction facing
    }

    void turn() {
        // if LEFT
         // if direction = up, direction left, etc...
    }
}

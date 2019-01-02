package com.brennaswitzer.aoc;

import java.awt.*;


public class Cart {

    // carts have direction, and keep track of their state of the last turn they took (left, straight, right, left straight, right, etc.)

    Point position;
    Direction direction;
    Turn nextTurn = Turn.LEFT;

    Cart(int x, int y, Direction dir) {
        position = new Point(x, y);
        direction = dir;
    }

    /**
     * Returns the current direction the cart is facing
     * @return Direction direction
     */
    Direction getDirection() {
        return direction;
    }

    /**
     * Returns the current X,Y coordinates of the cart
     * @return Point current
     */
    Point getCurrent() {
        return position;
    }

    void move(char next) {

        //if '-' then move forward one

        // if intersection, then nextTurn.turn();
        // it moves according to the track that is right in "front" of it, which will depend on direction facing
    }

    void turn() {
        // if WEST
         // if direction = up, direction left, etc...
    }
}

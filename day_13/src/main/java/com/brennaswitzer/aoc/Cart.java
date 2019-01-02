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
     *
     * @return Direction direction
     */
    Direction getDirection() {
        return direction;
    }

    /**
     * Returns the current X,Y coordinates of the cart
     *
     * @return Point current
     */
    Point getCurrent() {
        return position;
    }

    void move(char next) {

        // this doesn't work if we are facing the other direction!
        if (next == '-') {
            position.move(position.x + 1, position.y);
        }

        if (next == '\\') {
            position.move(position.x + 1, position.y);
            direction = Direction.SOUTH;
        }

        // if intersection, then nextTurn.turn();
        // it moves according to the track that is right in "front" of it, which will depend on direction facing
    }

    void turn() {
        // if WEST
        // if direction = up, direction left, etc...
    }

    @Override
    public String toString() {
        if(direction == Direction.EAST) {
            return ">";
        }
        if(direction == Direction.WEST) {
            return "<";
        }
        if(direction == Direction.NORTH) {
            return "^";
        }
        if(direction == Direction.SOUTH) {
            return "v";
        }
        return " ";
    }
}

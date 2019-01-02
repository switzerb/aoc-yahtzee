package com.brennaswitzer.aoc;

import java.awt.*;

public class Cart {

    Point position;
    char direction;

    Cart(int x, int y, char dir) {
        position = new Point(x, y);
        direction = dir;
    }
}

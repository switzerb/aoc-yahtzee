package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;


// TODO : create a snapshot of state so that we don't mutate our stars as we move them
// TODO: only generate the part of the sky that you need to look at. it's not absolute

public class Sky {

    List<Point> stars = new ArrayList<>();
    int seconds = 0;
    int X_MIN = Integer.MIN_VALUE;
    int X_MAX = Integer.MAX_VALUE;
    int Y_MIN = Integer.MIN_VALUE;
    int Y_MAX = Integer.MAX_VALUE;


    void setStar(int x, int y, int vx, int vy) {
        Point star = new Point(x, y, vx, vy);
        stars.add(star);
    }

    void draw() {
        int minX = Integer.MIN_VALUE, maxX = Integer.MAX_VALUE, minY = Integer.MIN_VALUE, maxY = Integer.MAX_VALUE;
        int xDiff = Integer.MAX_VALUE, yDiff = Integer.MAX_VALUE;
        boolean first = true;

        // To have the lights spell out something, points have to be close together. We warp time while both dimensions keep decreasing and stop when we
        // detect an increase on either X or Y coordinate.
        do {
            if (first) {
                first = false;
            } else {
                xDiff = maxX - minX;
                yDiff = maxY - minY;
            }

            minX = Integer.MAX_VALUE;
            maxX = Integer.MIN_VALUE;
            minY = Integer.MAX_VALUE;
            maxY = Integer.MIN_VALUE;

            for (Point point : stars) {
                point.move();

                if (point.getX() < minX) {
                    minX = point.getX();
                }
                if (point.getX() > maxX) {
                    maxX = point.getX();
                }

                if (point.getY() < minY) {
                    minY = point.getY();
                }
                if (point.getY() > maxY) {
                    maxY = point.getY();
                }
            }
            seconds++;
        } while ((maxX - minX) < xDiff && (maxY - minY) < yDiff);

        // Since we detected an increase on either X or Y axis, we're 1 step too far. So we back off 1 step to get the message.
        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;
        for (Point point : stars) {
            if (point.getX() < minX) {
                minX = point.getX();
            }
            if (point.getX() > maxX) {
                maxX = point.getX();
            }

            if (point.getY() < minY) {
                minY = point.getY();
            }
            if (point.getY() > maxY) {
                maxY = point.getY();
            }

            point.moveBack();
        }
        seconds--;

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                boolean found = false;
                for (Point point : stars) {
                    if (point.hasStar(x, y)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }

    }

    int getSeconds() {
        return this.seconds;
    }

    static public class Point {
        int x;
        int y;
        int vx;
        int vy;

        Point(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        void move() {
            this.x += this.vx;
            this.y += this.vy;
        }

        void moveBack() {
            this.x -= this.vx;
            this.y -= this.vy;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        boolean hasStar(int x, int y) {
            return this.x == x && this.y == y;
        }

    }

}

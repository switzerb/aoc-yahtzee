package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;


// TODO : create a snapshot of state so that we don't mutate our stars as we move them
// TODO: only generate the part of the sky that you need to look at. it's not absolute

public class Sky {

  List<Point> stars = new ArrayList<>();
  int offset = 10;
  int[][] sky;

  int getOffsetX(int x) {
    return x + offset;
  }

  int getOffsetY(int y) {
    return y + offset;
  }


  void setStar(int x, int y, int vx, int vy) {
    Point star = new Point(x, y, vx, vy);
    stars.add(star);
  }

  void draw(int s) {
    // need our calculate function back to we can create a new arraylist for every second in the sky rather than mutation
    // either that or every second needs to just be "one"
    // the size of the grid needs the size of the stars we need to draw, so we need the parse the output of the data points
    sky = new int[10][10];
    for (int x = 0; x < sky.length; x++) {
      for (int y = 0; y < sky.length; y++) {
        sky[x][y] = 0;
      }
    }

    // set new state based on which second it is
    for (Point p : stars) {
      p.move();
    }

    // assign those values to the grid
    for (Point p : stars) {
      sky[p.getX()][p.getY()] = 1;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int x = 0; x < sky.length; x++) {
      for (int y = 0; y < sky.length; y++) {
        if (sky[x][y] == 1) {
          sb.append("#");
        } else {
          sb.append(".");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
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

    int getX() {
      return x;
    }

    int getY() {
      return y;
    }

  }

}

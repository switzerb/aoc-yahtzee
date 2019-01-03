package com.brennaswitzer.aoc;

import java.io.IOException;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {

    List<String> lines = Loader.getInput("input.txt");
    Track track = new Track(lines);

    track.tick();
    System.out.println("Solution Part One: " + track.firstCollision());


  }
}

package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws IOException {

    Loader.getInput(Main.class);

    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("input.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(in));

    List<Integer> frequencies = new ArrayList<>();

    while (true) {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      frequencies.add(Integer.parseInt(line.trim()));
    }

    System.out.println(Main.partOneSolver(frequencies));
    System.out.println(Main.partTwoSolver(frequencies));

    // TODO: Make script to spin up new AOC day
    // TODO: read in utility for reading input
  }

  public static int partOneSolver(List<Integer> frequencies) {
    int sum = 0;
    for( Integer i : frequencies ) {
      sum += i;
    }
    return sum;
  }

  public static int partTwoSolver(List<Integer> frequencies) {
    int sum = 0;
    Set<Integer> sums = new HashSet<>();
    sums.add(0);

    while(true) {
      for (Integer frequency : frequencies) {
        sum += frequency;
        if (sums.contains(sum)) {
          return sum;
        }
        sums.add(sum);
      }
    }
  }

}

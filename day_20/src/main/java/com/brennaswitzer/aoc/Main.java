package com.brennaswitzer.aoc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//    ClassLoader cl = Main.class.getClassLoader();
//    InputStream in = cl.getResourceAsStream("input.txt");
//    BufferedReader r = new BufferedReader(new InputStreamReader(in));
//
//    List<String> input = new ArrayList<>();
//
//    while (true) {
//      String line = r.readLine();
//      if (line == null) {
//        break;
//      }
//      input.add(line);
//    }
        
        
        NorthPole facility = new NorthPole();
        Point start = new Point(0, 0);
        String input = "WNE";
        facility.traverseMap(input.toCharArray(), start);
        System.out.println(facility);
    }
}

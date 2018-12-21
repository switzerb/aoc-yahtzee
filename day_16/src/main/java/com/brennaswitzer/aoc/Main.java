package com.brennaswitzer.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassLoader cl = Main.class.getClassLoader();
        InputStream in = cl.getResourceAsStream("input.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        
        List<String> input = new ArrayList<>();
        Register instructions = new Register();
        
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            if (line.length() > 0) {
                input.add(line);
            }
        }
        
        int count = 0;
        int id = 0;
        int A = 0;
        int B = 0;
        int C = 0;
        int[] before = {0, 0, 0, 0};
        int[] after = {0, 0, 0, 0};
        
        for (String l : input) {
            count++;
            
            String[] split = l.split(" ");
            
            if (split[0].trim().equals("Before:")) {
                before = parseBefore(split);
            } else if (split[0].trim().equals("After:")) {
                after = parseAfter(split);
            } else {
                id = Integer.parseInt(split[0].trim());
                A = Integer.parseInt(split[1].trim());
                B = Integer.parseInt(split[2].trim());
                C = Integer.parseInt(split[3].trim());
            }
            
            if (count % 3 == 0) {
                instructions.addOpcode(id, A, B, C, before, after);
            }
        }
        
        System.out.println(instructions.countInstructions());
        
    }
    
    public static int[] parseBefore(String[] split) {
        return new int[]{
                Integer.parseInt(split[1].substring(1, 2)),
                Integer.parseInt(split[2].substring(0, 1)),
                Integer.parseInt(split[3].substring(0, 1)),
                Integer.parseInt(split[4].substring(0, 1))
        };
    }
    
    public static int[] parseAfter(String[] split) {
        return new int[]{
                Integer.parseInt(split[2].substring(1, 2)),
                Integer.parseInt(split[3].substring(0, 1)),
                Integer.parseInt(split[4].substring(0, 1)),
                Integer.parseInt(split[5].substring(0, 1))
        };
    }
}

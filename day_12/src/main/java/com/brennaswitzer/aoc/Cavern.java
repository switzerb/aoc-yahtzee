package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;

public class Cavern {

    List<int[]> generations = new ArrayList<>();

    Cavern(String initial) {
        int[] right = new int[initial.length()];
        int[] left = new int[initial.length()];
        
        int count = 0;
        String[] state = initial.split("");
        for (String s : state) {
            if (s.equals("#")) {
                right[count] = 1;
            } else {
                right[count] = 0;
            }
            count++;
        }
        generations.add(right);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int[] generation : generations) {
            for(int i = 0; i < generation.length; i++){
                if(generation[i] == 1){
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
        }
        return sb.toString();
    }
}

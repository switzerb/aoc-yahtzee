package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;

public class Cavern {
    
    List<String> generations = new ArrayList<>();
    List<Rule> rules = new ArrayList<>();
    
    Cavern(String initial) {
        generations.add("..." + initial + "...........");
    }
    
    void addRule(String rule) {
        String[] split = rule.split("=>");
        Rule r = new Rule(split[0].trim(), split[1].trim());
        rules.add(r);
    }
    
    String willGrow(String pattern) {
        for(Rule r : rules) {
            if(pattern.equals(r.in)) {
                return r.out;
            }
        }
        return ".";
    }
    
    void evolution() {
        String prev = getGeneration(0);
        String next = "";
        for(int e = 0; e < 20; e++) {
            next = nextGeneration(prev);
            prev = next;
        }
    }
    
    String nextGeneration(String prev) {
        StringBuilder next = new StringBuilder();
        char[] pots = prev.toCharArray();
        
        for(int i = 3; i < pots.length -2; i++) {
            // need to send the pot, plus two previous and two after to willGrow function
            char[] p = { pots[i-2], pots[i-1] ,pots[i], pots[i+1], pots[i+2]};
            String pattern = new String(p);
            String result = willGrow(pattern);
            next.append(result);
        }
        generations.add("..." + next.toString());
        return next.toString();
    }
    
    String getGeneration(int index) {
        return generations.get(index);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String generation : generations) {
            sb.append(generation);
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static class Rule {
        String in;
        String out;
        
        Rule(String in, String out) {
            this.in = in;
            this.out = out;
        }
    }
}

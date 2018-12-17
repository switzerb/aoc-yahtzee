package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;

public class Cavern {
    
    List<String> generations = new ArrayList<>();
    List<Rule> rules = new ArrayList<>();
    int center = 0;
    
    Cavern(String initial) {
        generations.add(initial);
    }
    
    void addRule(String rule) {
        String[] split = rule.split("=>");
        Rule r = new Rule(split[0].trim(), split[1].trim());
        rules.add(r);
    }
    
    String willGrow(String pattern) {
        for (Rule r : rules) {
            if (pattern.equals(r.in)) {
                return r.out;
            }
        }
        return ".";
    }
    
    int getSumOfPots(int gens) {
        int sum = 0;
        
        String prev = getGeneration(0);
    
        for (int e = 0; e < gens; e++) {
            prev = "....." + prev + "...";
            String next = "";
            center += 3;
        
            for (int i = 2; i < prev.length() - 2; i++) {
                // need to send the pot, plus two previous and two after to willGrow function
                String pattern = prev.substring(i - 2, i + 3);
                next += willGrow(pattern);
            }
            generations.add(next);
            prev = next;
        }
    
        String last = getGeneration(gens);
        for (int i = 0; i < last.length(); i++) {
            if(last.charAt(i) == '#') {
                sum += i - center;
            }
        }
        return sum;
    }
    
    int hugeEvolution() {
        return getSumOfPots(20);
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

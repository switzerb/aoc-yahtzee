package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;

public class Node {
    
    String path = "";
    List<Node> branches = new ArrayList<>();
    
    Node() { }
    
    Node buildDirections(String directions) {
        
        System.out.println(directions);
        
        Node n = new Node();
        int open = directions.indexOf("(");
        
        if(open > -1) {
            buildDirections(directions.substring(open + 1));
        } else {
            n.path = directions.substring(0, open);
            
        }
        return n;
    }
}

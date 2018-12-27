package com.brennaswitzer.aoc;

import java.util.LinkedList;

public class Graph {

    private int vertices;
    private LinkedList<Integer>[] adjacent;
    
    Graph(int v) {
        this.vertices = v;
        //noinspection unchecked
        adjacent = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i=0; i<v; ++i)
            adjacent[i] = new LinkedList<>();
    }
}

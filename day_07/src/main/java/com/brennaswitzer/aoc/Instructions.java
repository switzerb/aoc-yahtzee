package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Instructions {

    Graph instructions;
    List<String> done = new ArrayList<>();
    SortedSet<String> available = new TreeSet<>();
    int[] workers;

    Instructions(Graph instructions, int workers) {
        this.instructions = instructions;
        this.workers = new int[workers];
        sortInstructions(this.instructions);
    }

    private void sortInstructions(Graph graph) {
        for (List<String> v : graph.values()) {
            java.util.Collections.sort(v);
        }
    }

    String getInstructionOrder() {
        String parent = findFirstInstruction();
        done.add(parent);

        while (true) {
            String next = getNext(parent);
            done.add(next);
            parent = next;
            if (available.size() == 0) {
                break;
            }
        }
        return String.join("", done);
    }

    List<String> getAvailable() {

        /**
         * Step is available if
         * all the parents are done or there are no parents
         * there is an available worker to do it
         * the seconds have elapsed that require that step to be done
         */

        List<String> open = new ArrayList<>();

        for (String key : instructions.keySet()) {
            if (!hasParent(key)) {
                open.add(key);
            }
        }
        java.util.Collections.sort(open);
        return open;

    }

    String getNext(String parent) {
        List<String> dependencies = instructions.get(parent);
        available.addAll(dependencies);
        String next = null;

        for (String s : available) {
            if (hasAllDone(s)) {
                next = s;
                available.remove(s);
                return next;
            }
        }
        if (findFirstInstruction() != null) {
            return findFirstInstruction();
        }
        // this means there are no available next steps and there are no letters that don't have a dependency left
        throw new RuntimeException("There is no next");

    }

    boolean hasAllDone(String i) {
        // check to see that all of the keys that have this string in their list are in the done list
        for (Graph.Entry<String, List<String>> entry : instructions.entrySet()) {
            for (String s : entry.getValue()) {
                if (i.equals(s) && !keyInDone(entry.getKey())) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean keyInDone(String key) {
        return done.contains(key);
    }

    String findFirstInstruction() {
        List<String> nodependency = new ArrayList<>();
        // the only key that doesn't have a dependency, meaning the key is not present as a value for anything in the map
        for (String key : instructions.keySet()) {
            if (!hasParent(key)) {
                nodependency.add(key);
            }
        }
        java.util.Collections.sort(nodependency);
        for (String s : nodependency) {
            if (!done.contains(s)) {
                return s;
            }
        }
        return null;
    }

    boolean hasParent(String key) {
        for (List<String> list : instructions.values()) {
            for (String l : list) {
                if (l.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    void timeToAssemble() {

        // for each step that is available
        // assign

//        int max = getMaxTimeValue();
//        String step = findFirstInstruction();
//
//        // loop through all the seconds up to the max amount of time it might take
//        for (int t = 60; t < max; t++) {
//            String parent = findFirstInstruction();
//
//            if(t >= timeToFinish(parent)) {
//                done.add(parent);
//            }
//
//            // if we have an available worker and an available step, start the clock
//            if (getWorkerIndex() != -1) {
//                workers[getWorkerIndex()] = timeToFinish(step);
//            }
//
//        }

    }

    int getWorkerIndex() {
        for (int i = 0; i < workers.length; i++) {
            if (workers[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    int timeToFinish(String letter) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
        return 60 + 1 + alphabet.indexOf(letter);
    }

    int getMaxTimeValue() {
        int max = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
        String[] split = alphabet.split("");
        for (String s : split) {
            max += timeToFinish(s);
        }
        return max;

    }
}

package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Instructions {

    Graph steps;
    List<String> done = new ArrayList<>();
    SortedSet<String> available = new TreeSet<>();
    int[] workers;

    Instructions(Graph steps, int workers) {
        this.steps = steps;
        this.workers = new int[workers];
        sortSteps(this.steps);
        getStepOrder();
    }

    private void sortSteps(Graph graph) {
        for (List<String> v : graph.values()) {
            java.util.Collections.sort(v);
        }
    }
    
    String printSteps() {
        return String.join("", done);
    }

    void getStepOrder() {
        String parent = start();
        done.add(parent);

        while (true) {
            String next = getNext(parent);
            done.add(next);
            parent = next;
            if (available.size() == 0) {
                break;
            }
        }
    }
    
    int timeToComplete() {

        int max = getMaxTimeValue();
        int seconds = 0;
    
        while(seconds < 10) {
            System.out.println(done);
    
            /**
             * Step is available if
             * all the parents are done or there are no parents
             * there is an available worker to do it
             * the seconds have elapsed that require that step to be done
             */
    
            System.out.println(seconds);

            //            if(t >= timeToFinish(parent)) {
//                done.add(parent);
//            }
//
//            // if we have an available worker and an available step, start the clock
//            if (getWorkerIndex() != -1) {
//                workers[getWorkerIndex()] = timeToFinish(step);
//            }
    
            seconds++;
        }
        
        return seconds;
    }
    
    String getNext(String parent) {
        List<String> dependencies = steps.get(parent);
        available.addAll(dependencies);
        String next;

        for (String s : available) {
            if (hasAllDone(s)) {
                next = s;
                available.remove(s);
                return next;
            }
        }
        if (start() != null) {
            return start();
        }
        // this means there are no available next steps and there are no letters that don't have a dependency left
        throw new RuntimeException("There is no next");

    }
    
    private String start() {
        List<String> nodependency = new ArrayList<>();
        // the only key that doesn't have a dependency, meaning the key is not present as a value for anything in the map
        for (String step : steps.keySet()) {
            if (!hasParent(step)) {
                nodependency.add(step);
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
    
    
    private boolean hasAllDone(String i) {
        // check to see that all of the keys that have this string in their list are in the done list
        for (Graph.Entry<String, List<String>> entry : steps.entrySet()) {
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
    
    boolean hasParent(String key) {
        for (List<String> list : steps.values()) {
            for (String l : list) {
                if (l.equals(key)) {
                    return true;
                }
            }
        }
        return false;
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
        return letter.charAt(0) - 4;
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

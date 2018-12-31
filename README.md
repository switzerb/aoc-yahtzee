# aoc-yahtzee

This repo contains code to solve puzzles for the [Advent of Code 2018](https://adventofcode.com/2018) challenge. The solutions
are written in Java (see details of requirements below).

## Requirements to run

[Java 1.8.0 build 191](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
Java JDK 1.8.0
Maven Apache Maven 3.5.4

## General Notes
The solvers are segmented into one module per puzzle day.
The text of the puzzles are printed in individual README.md files located at the root of each module folder.
in the
To run your input, you will need to replace the puzzle input that is currently in the files with the puzzle input to be tested.

In general, the input is located in day_XX/src/main/resources/input.txt. To get specific instructions for each day, look in the README.md for each individual day.

### Run AOC solvers 

To compile the project and install local binaries:

    $ mvn clean
    $ mvn install
    
Run solver by pasting input into **<project-root>/<aoc_day>/src/main/resources/input.txt** and then execute command:

    $ ./run.sh
    
In some cases, the input must be put directly into the Main class file instead of read in from a resource file. 

### Run All Days

If it's more convenient, you can run all the solvers at once (with your input files in the correct location) by running at the root of the project.

    $ ./run_all.sh
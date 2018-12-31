# aoc-yahtzee

This repo contains code to solve puzzles for the [Advent of Code 2018](https://adventofcode.com/2018) challenge. The solutions
are written in Java (see details of requirements below).

### Requirements to run
[Java 1.8.0 build 191](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
Java JDK 1.8.0
Maven Apache Maven 3.5.4

### General Notes
The solvers are segmented into one module per puzzle day. To run your input, you will need to replace the puzzle input that is currently in the files with the puzzle input to be tested and then run the solver. Please note that not all days are solved (yet) and so not all puzzles are available to be run. There is a manifest for days and where the input files must be stored below in the "Run Solvers" section.

In general, the input is located in day_XX/src/main/resources/input.txt. To get specific instructions for each day, look in the README.md for each individual day.

### Build project
To compile the project and install solvers locally, check the repo out of github and then compile

    $ git clone https://github.com/switzerb/aoc-yahtzee.git
    $ cd aoc-yahtzee
    $ mvn install


### Run Solvers
For each day that you want to run, you need to replace the current input.txt (or in some cases, add input directly into the Main class file) and then run the solver. Run solver by pasting input into **<project-root>/<aoc_day>/src/main/resources/input.txt** and then execute command:

    $ ./run.sh

If you have cut and paste the input into the approriate place for each day, you can run all the solvers at the same time using from the root of the project:

    $ ./run_all.sh

Below is a manifest of the days that are available for solving and where to paste the input

| Day One | day_01/src/main/resources/input.txt

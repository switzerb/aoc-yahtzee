package com.brennaswitzer.aoc;

public class Main {

  public static void main(String[] args) {

    MarbleGame game = new MarbleGame(416, 71617);
    MarbleGame gameTwo = new MarbleGame(416, 7161700);

    System.out.println("Answer for Part One: " + game.playGame());
    System.out.println("Answer for Part Two: " + gameTwo.playGame());
  }

}

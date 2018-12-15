package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {

  Sky one = new Sky(30);
  Sky two = new Sky(10);

  @Before
  public void setup() {
    one.setStar(3, 9, 1, -2);
  }

  @Test
  public void testPointMovement() {
    Sky.Point test = new Sky.Point(3, 9, 1, -2);
    assertEquals(4, test.getXpos());
    assertEquals(7, test.getYpos());
  }

  @Test
  public void testSeeSky() {
    two.setStar(0, 0, 1, 1);
    two.setStar(1, 1, 1, 1);

    for (int i = 0; i < 4; i++) {
      two.draw(i);
      System.out.println(two);
    }
  }


}

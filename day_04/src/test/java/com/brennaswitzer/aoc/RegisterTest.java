package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class RegisterTest {

  Register register;

  @Before
  public void setup() throws IOException {
    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("input.txt");
    BufferedReader r = new BufferedReader(new InputStreamReader(in));

    List<String> input = new ArrayList<>();

    while (true) {
      String line = r.readLine();
      if (line == null) {
        break;
      }
      input.add(line.trim());
    }

    List<Record> records = Main.parseRecords(input);
    register = new Register(records);
  }

  @Test
  public void testGetSleepiestMinute() {
    assertEquals(23, register.getSleepiestMinutePerGuard(761));
  }

  @Test
  public void testGetSleepiestMinute2() {
    assertEquals(0, register.getSleepiestMinutePerGuard(743));
  }

  @Test
  public void testGetUniqueGaurds() {
    assertEquals(0, register.getAllGuards());
  }

  @Test
  public void testGuardTotal() {
    assertEquals( 321, register.getTotalforGuard(2591));
    assertEquals( 307, register.getTotalforGuard(1889));
    assertEquals( 164, register.getTotalforGuard(3329));
    assertEquals( 278, register.getTotalforGuard(2789));
    assertEquals( 372, register.getTotalforGuard(2141));
    assertEquals( 420, register.getTotalforGuard(761));
  }

  @Test
  public void testGetGuardwithMostMinutes() {
    assertEquals(0, register.getGuardwithMostMinutes());
  }
}

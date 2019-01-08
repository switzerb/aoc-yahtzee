package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnitTest {

    Battlefield battle;

    @Before
    public void setup() throws IOException {
        battle = new Battlefield(Loader.getInput("test_input3.txt"));
    }

    @Test
    public void test_enemiesInRangeNeg() {
        Unit goblin = battle.units.get(0);
        assertEquals(false, goblin.enemiesInRange(battle));
    }

    @Test
    public void test_enemiesInRangePos() {
        Unit elf = battle.units.get(1);
        assertEquals(true, elf.enemiesInRange(battle));
    }

}

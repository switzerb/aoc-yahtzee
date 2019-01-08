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
        assertEquals(null, goblin.canAttack(battle));
    }

    @Test
    public void test_enemiesInRangePos() {
        Adjacent near = new Adjacent();
        Unit goblin = battle.units.get(2);
        near.put('E', goblin);
        Unit elf = battle.units.get(1);
        assertEquals(near, elf.canAttack(battle));
    }

}

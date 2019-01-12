package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Battlefield {

    // map of the battlefield grid
    // govern the behavior and position of points
    // make sure we can get reading order for anything, like open squares and combatants

    List<Unit> units;
    char[][] battlefield;
    int width;
    int height;
    int rounds;
    boolean done;

    Battlefield(List<String> input) {
        height = input.size();
        battlefield = new char[height][];
        for (int i = 0; i < height; i++) {
            char[] b = input.get(i).toCharArray();
            width = b.length;
            battlefield[i] = Arrays.copyOf(b, width);
        }
        initUnits();
    }

    Battlefield(String init) {
        String[] split = init.split("\n");
        List<String> input = Arrays.asList(split);
        height = input.size();
        battlefield = new char[height][];
        for (int i = 0; i < height; i++) {
            char[] b = input.get(i).toCharArray();
            width = b.length;
            battlefield[i] = Arrays.copyOf(b, width);
        }
        initUnits();
    }

    private void initUnits() {
        units = new ArrayList<>();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (battlefield[r][c] == 'E') {
                    Unit elf = new Unit('E', new Position(r, c));
                    units.add(elf);
                }
                if (battlefield[r][c] == 'G') {
                    Unit goblin = new Unit('G', new Position(r, c));
                    units.add(goblin);
                }
            }
        }
        units.sort(UNITS_IN_READING_ORDER);
    }

    List<Unit> getUnitsByTeam(char team) {
        List<Unit> byTeam = new ArrayList<>();
        for (Unit u : units) {
            if (u.getSelf() == team) {
                byTeam.add(u);
            }
        }
        byTeam.sort(UNITS_IN_READING_ORDER);
        return byTeam.size() > 0 ? byTeam : null;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    char getPosition(Position p) {
        return battlefield[p.getRow()][p.getCol()];
    }

    /**
     * Runs a single round of play.
     * Combat proceeds in rounds; in each round, each unit that is still alive takes a turn, resolving all of its actions before the next unit's turn begins.
     * For instance, the order in which units take their turns within a round is the reading order of their starting positions in that round
     */
    void runRound() {
        System.out.println("ROUND " + rounds);

        units = cleanBattlefield();

        for (Unit unit : units) {
            if (!unit.isDead()) {
                done = unit.turn(this);
            }
            if (done) break;
        }
        rounds++;
    }

    List<Unit> cleanBattlefield() {
        List<Unit> nextRound = new ArrayList<>();
        for (Unit u : units) {
            if (!u.isDead()) {
                nextRound.add(u);
            }
        }
        nextRound.sort(UNITS_IN_READING_ORDER);
        return nextRound;
    }

    boolean isOver() {
        return done;
    }


    /**
     * Return a unit by current position on the battlefield. If there is no unit at that position, return null
     *
     * @param position
     * @return Unit
     */
    Unit getUnitByPosition(Position position) {
        for (Unit u : units) {
            if (u.getCurrent().equals(position)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Remove a unit from the battlefield when it's dead
     *
     * @param target unit that is being removed
     */
    public void removeUnit(Unit target) {
        Position current = target.getCurrent();
        battlefield[current.getRow()][current.getCol()] = '.';
        target.kill();
    }

    /**
     * Move a unit on the field to somewhere new and replace that position with a '.' instead
     * @param unit
     * @return new position of unit
     */
    void moveUnit(Unit unit, Position moveTo) {
        battlefield[unit.getCurrent().getRow()][unit.getCurrent().getCol()] = '.';
        battlefield[moveTo.getRow()][moveTo.getCol()] = unit.self;
    }

    /**
     * The outcome of the battle: the number of full rounds that were completed (not counting the round in which combat ends)
     * multiplied by the sum of the hit points of all remaining units at the moment combat ends.
     * (Combat only ends when a unit finds no targets during its turn.)
     *
     * @return
     */
    int outcome() {
        int sum = 0;
        for (Unit u : units) {
            sum += u.getHitpoints();
        }
        return sum * rounds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < height; r++) {
            if (r > 0) sb.append('\n');
            for (int c = 0; c < width; c++) {
                sb.append(battlefield[r][c]);
            }
        }
        return sb.toString();
    }

    public static Comparator<Unit> UNITS_IN_READING_ORDER = new Comparator<Unit>() {
        @Override
        public int compare(Unit u1, Unit u2) {

            if (u1.getCurrent().getRow() < u2.getCurrent().getRow()) return -1;
            if (u1.getCurrent().getRow() > u2.getCurrent().getRow()) return 1;
            return Integer.compare(u1.getCurrent().getCol(), u2.getCurrent().getCol());
        }
    };

}

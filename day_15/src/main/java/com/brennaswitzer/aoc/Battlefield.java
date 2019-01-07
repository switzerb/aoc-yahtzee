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

    /**
     * Move a unit on the field to somewhere new and replace that position with a '.' instead
     *
     * @param unit
     * @return new position of unit
     */
    Position updatePosition(Unit unit) {
        return new Position();
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    /**
     * Runs a single round of play.
     * Combat proceeds in rounds; in each round, each unit that is still alive takes a turn, resolving all of its actions before the next unit's turn begins.
     * For instance, the order in which units take their turns within a round is the reading order of their starting positions in that round
     */
    void runRound() {

        // sort the units before a round to make sure they operate in reading order
        units.sort(UNITS_IN_READING_ORDER);

        for (Unit unit : units) {
            System.out.println("Unit: " + unit.getTeam() + " , Position: " + unit.getCurrent());
        }

    }

    List<Position> sortReadingOrder() {
        return new ArrayList<>();
    }

    /**
     * Get information about those spaces immediately adjacent to a Unit. It will always return a list of zero to four units.
     *
     * @param unit
     * @return
     */
    List<Unit> getAdjacent(Position unit) {
        return new ArrayList<>();
    }

    /**
     * The outcome of the battle: the number of full rounds that were completed (not counting the round in which combat ends)
     * multiplied by the sum of the hit points of all remaining units at the moment combat ends.
     * (Combat only ends when a unit finds no targets during its turn.)
     *
     * @return
     */
    int outcome() {
        return 0;
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

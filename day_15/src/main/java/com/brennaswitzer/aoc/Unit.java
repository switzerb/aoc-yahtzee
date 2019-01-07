package com.brennaswitzer.aoc;

import java.awt.*;

public class Unit {

    char team;
    Point start;
    Point current;
    int hitpoints = 200;
    int power = 3;
    boolean done;


    int getHitpoints() {
        return hitpoints;
    }

    int getPower() {
        return power;
    }

    /**
     * Determines whether a unit is dead after having been attacked
     *
     * @param attack
     * @return
     */
    boolean damage(int attack) {
        hitpoints = hitpoints - attack;
        return hitpoints > 0;
    }

    /**
     * Each unit begins its turn by identifying all possible targets (enemy units)
     * if no enemy targets, combat ends
     * else unit takes a turn,
     * If the unit is not already in range of a target, and there are no open squares which are in range of a target, the unit ends its turn.
     * If the unit is already in range of a target, it does not move, but continues its turn with an attack. Otherwise, since it is not in range of a target, it moves.
     * if in range, attack
     */
    void turn() {

        // identifyTargets()
        // if targetsInRange() {
        // attack()
        // else
        // move()
        //  if unit can't move, the unit ends it's turn
//        If the unit is not already in range of a target, and there are no open squares which are in range of a target, the unit ends its turn.
    }

    boolean turnOver() {
        return done;
    }


    /**
     * Determines if a unit should take a turn
     *
     * @return
     */
    boolean isAlive() {
        return false;
    }

    /**
     * Get a map of all enemy combatants
     * if no enemy targets, combat ends (note this might be in the middle of a round)
     */
    void identifyTargets() {

    }

    /**
     * Alternatively, the unit might already be in range of a target.
     */
    void targetsInRange() {

    }

    /**
     * Then, the unit identifies all of the open squares (.) that are in range of each target;
     * these are the squares which are adjacent (immediately up, down, left, or right)
     * to any target and which aren't already occupied by a wall or another unit.
     */
    void openInRangeOfTarget() {

    }

    /**
     * To attack, the unit first determines all of the targets that are in range of it by being immediately adjacent to it.
     * If there are no such targets, the unit ends its turn. Otherwise, the adjacent target with the fewest hit points is selected; in a tie, the adjacent target with the fewest hit points which is first in reading order is selected.
     */
    void attack() {

    }

    /**
     * To move, the unit first considers the squares that are in range and determines
     * which of those squares it could reach in the fewest steps. A step is a single movement to any adjacent
     * (immediately up, down, left, or right) open (.) square. Units cannot move into walls or other units.
     * The unit does this while considering the current positions of units and does not
     * do any prediction about where units will be later.
     * If the unit cannot reach (find an open path to) any of the squares that are in range, it ends its turn.
     * If multiple squares are in range and tied for being reachable in the fewest steps,
     * the square which is first in reading order is chosen. For example:
     * <p>
     * Targets:      In range:     Reachable:    Nearest:      Chosen:
     * #######       #######       #######       #######       #######
     * #E..G.#       #E.?G?#       #E.@G.#       #E.!G.#       #E.+G.#
     * #...#.#  -->  #.?.#?#  -->  #.@.#.#  -->  #.!.#.#  -->  #...#.#
     * #.G.#G#       #?G?#G#       #@G@#G#       #!G.#G#       #.G.#G#
     * #######       #######       #######       #######       #######
     */
    void findDestination() {

        // find targets (we know we have targets otherwise we wouldn't be here)
        // find open squares in range
        // find which of those are reachable (no enemy or wall in the way)
        // find which ones are nearest (smallest manhattan distance)
        // sort positions by reading order and then choose the first one
        // then move()

    }

    /**
     * The unit then takes a single step toward the chosen square along the shortest path to that square.
     * If multiple steps would put the unit equally closer to its destination,
     * the unit chooses the step which is first in reading order.
     * <p>
     * The Elf sees three squares in range of a target (?), two of which are nearest (!),
     * and so the first in reading order is chosen (+).
     * Under "Distance", each open square is marked with its distance from the destination square;
     * the two squares to which the Elf could move on this turn (down and to the right) are both equally good moves
     * and would leave the Elf 2 steps from being in range of the Goblin.
     * <p>
     * Because the step which is first in reading order is chosen, the Elf moves right one square.
     * <p>
     * In range:     Nearest:      Chosen:       Distance:     Step:
     * #######       #######       #######       #######       #######
     * #.E...#       #.E...#       #.E...#       #4E212#       #..E..#
     * #...?.#  -->  #...!.#  -->  #...+.#  -->  #32101#  -->  #.....#
     * #..?G?#       #..!G.#       #...G.#       #432G2#       #...G.#
     * #######       #######       #######       #######       #######
     */
    void move() {

    }

    void squaresInRange() {

    }
}

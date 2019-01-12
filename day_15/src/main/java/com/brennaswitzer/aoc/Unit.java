package com.brennaswitzer.aoc;

import java.util.*;

public class Unit {

    char self;
    char enemy;
    Position current;
    int hitpoints = 200;
    int power = 3;
    boolean done;

    Unit(char self, Position current) {
        this.self = self;
        this.enemy = this.self == 'E' ? 'G' : 'E';
        this.current = current;
    }

    Position getCurrent() {
        return current;
    }

    char getSelf() {
        return self;
    }

    int getHitpoints() {
        return hitpoints;
    }

    void setHitpoints(int hp) {
        this.hitpoints = hp;
    }

    /**
     * Each unit begins its turn by identifying all possible targets (enemy units)
     * if no enemy targets, combat ends
     * else unit takes a turn,
     * If the unit is not already in range of a target, and there are no open squares which are in range of a target, the unit ends its turn.
     * If the unit is already in range of a target, it does not move, but continues its turn with an attack. Otherwise, since it is not in range of a target, it moves.
     * if in range, attack
     *
     * @return true if combat is ended, false if turn is over
     */
    boolean turn(Battlefield state) {
        System.out.println("Running Unit: " + getSelf() + " , Position: " + getCurrent());
        List<Unit> enemies = findEnemies(state);
        if (enemies == null) return true;

        Adjacent enemiesNear = inAttackRange(state);
        if (enemiesNear != null) {
            Unit target = attack(enemiesNear);
            if (target.isDead()) {
                state.removeUnit(target);
            }
        } else {
            Position targetPosition = getTargetPosition(state, enemies);

            // are there are no open squares in range, then turn is over
            // else move
            // move()
        }
        //  if unit can't move, the unit ends it's turn
        return false;
    }

    /**
     * Determines if a unit is dead
     * @return boolean true if unit is dead and false if it is not
     */
    boolean isDead() {
        return hitpoints <= 0;
    }

    /**
     * Get a map of all enemy combatants, if no enemy targets, combat ends (note this might be in the middle of a round)
     * return null if there are no enemy targets
     */
    List<Unit> findEnemies(Battlefield state) {
        return state.getUnitsByTeam(this.self == 'E' ? 'G' : 'E');
    }

    /**
     * To attack, the unit first determines all of the targets that are in range of it by being immediately adjacent to it.
     */
    Adjacent inAttackRange(Battlefield state) {
        Adjacent enemiesInRange = new Adjacent();

        for (Direction dir : Direction.values()) {
            Unit u = state.getUnitByPosition(current.go(dir));
            if (u != null) {
                enemiesInRange.put(current.go(dir), u);
            }
        }
        return enemiesInRange.size() > 0 ? enemiesInRange : null;
    }

    /**
     * For all adjacent enemies, attack the one with the lowest hitpoints
     * In a tie, the adjacent target with the fewest hit points which is first in reading order is selected.
     *
     * @param enemies Map of all enemies in attack range
     */
    Unit attack(Adjacent enemies) {
        int min = Integer.MAX_VALUE;
        Unit target = null;
        for (Unit u : enemies.values()) {
            if (u.getHitpoints() < min) {
                min = u.getHitpoints();
                target = u;
            }
        }
        assert target != null;
        target.setHitpoints(target.getHitpoints() - this.power);
        return target;
    }

    /**
     * To move, the unit first considers the squares that are in range near enemy targets and determines
     * If the unit cannot reach (find an open path to) any of the squares that are in range, it ends its turn.
     * If multiple squares are in range and tied for being reachable in the fewest steps,
     * the square which is first in reading order is chosen. For example:
     * <p>
     * Enemies:      In range:     Reachable:    Nearest:      Chosen:
     * #######       #######       #######       #######       #######
     * #E..G.#       #E.?G?#       #E.@G.#       #E.!G.#       #E.+G.#
     * #...#.#  -->  #.?.#?#  -->  #.@.#.#  -->  #.!.#.#  -->  #...#.#
     * #.G.#G#       #?G?#G#       #@G@#G#       #!G.#G#       #.G.#G#
     * #######       #######       #######       #######       #######
     */
    Position getTargetPosition(Battlefield state, List<Unit> enemies) {
        // Enemies on the battlefield
        TreeSet<Position> openPositions = inRange(enemies, state);

        // In range, reachable and nearest me
        TreeSet<Position> closest = nearest(state, openPositions, getCurrent());

        // We are sorted in reading order, so take the first item in the set as our target
        return closest.first();
    }

    /**
     * Identify the open squares (.) that are in range of each target;
     * these are the squares which are adjacent (immediately up, down, left, or right)
     * to any target and which aren't already occupied by a wall or another unit.*
     */
    TreeSet<Position> inRange(List<Unit> enemies, Battlefield field) {
        TreeSet<Position> openPositions = new TreeSet<>();
        for (Unit u : enemies) {

            for (Direction dir : Direction.values()) {
                char c = field.getPosition(u.getCurrent().go(dir));
                if (c == '.') {
                    openPositions.add(u.getCurrent().go(dir));
                }
            }
        }
        return openPositions;
    }

    /**
     * Set of positions that are in range, reachable and nearest
     *
     * @param field   Current battlefield
     * @param inRange Open positions near enemies
     * @param start   Our current position
     * @return TreeSet<Position> Ordered position set nearest to our current position and reachable
     */

    TreeSet<Position> nearest(Battlefield field, Set<Position> inRange, Position start) {
        int min_steps = Integer.MAX_VALUE;
        TreeSet<Position> nearest = new TreeSet<>();
        LinkedList<Collector> queue = new LinkedList<>();
        Map<Position, Integer> visited = new HashMap<>();
        Collector current = new Collector(start, 0);
        visited.put(start, 0);
        queue.add(current);

        while (queue.size() != 0) {
            current = queue.poll();

            for (Direction dir : Direction.values()) {
                Collector next = new Collector(current.getPosition().go(dir), current.getSteps() + 1);

                if (field.getPosition(next.getPosition()) == '.' && !visited.containsKey(next.getPosition())) {

                    if (inRange.contains(next.getPosition()) && next.getSteps() <= min_steps) {
                        nearest.add(next.getPosition());
                        min_steps = next.getSteps();
                    }

                    visited.put(next.getPosition(), next.getSteps());
                    queue.add(next);
                }

            }
        }
        return nearest;
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

}

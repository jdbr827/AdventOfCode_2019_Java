package year_2024.day_16;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.testng.internal.collections.Pair;
import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


@AllArgsConstructor

class State implements Comparable<State> {
    Integer score;
    CartesianPoint location;
    CardinalDirection facing;

    @Override
    public int compareTo(@NotNull State o) {
        return score.compareTo(o.score);
    }

    @Override
    public String toString() {
        return "State{" +
                "score=" + score +
                ", location=" + location +
                ", facing=" + facing +
                '}';
    }
}

public class Day16 {
    Character[][] grid;
    CartesianPoint start;
    CartesianPoint end;
    int N;

    public Day16(String inputFilename) {
        grid = new AOCScanner(inputFilename).scanAsChar2DArray();
        N = grid.length;
        int M = grid[0].length;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (grid[i][j] == 'S') {
                    start = new CartesianPoint(j, N-i-1);
                    grid[i][j] = '.';
                } else if (grid[i][j] == 'E') {
                    end = new CartesianPoint(j, N-i-1);
                    grid[i][j] = '.';
                }
            }
        }
    }


    public int bestReindeerScore() {
        Set<Pair<CartesianPoint, CardinalDirection>> found = new HashSet<>();

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, start, CardinalDirection.EAST));
        while (!pq.isEmpty() && !pq.peek().location.equals(end)) {
            //System.out.println(pq);
            State state = pq.poll();
            if (found.add(Pair.of(state.location, state.facing))) {
                for (CardinalDirection direction : CardinalDirection.values()) {
                    CartesianPoint proposed = state.location.add(direction.velocity);
                    if (!isWall(proposed) && !found.contains(Pair.of(proposed, direction))) {
                            pq.add(new State(
                                    state.score + 1 + (1000 * rotationsBetween(state.facing, direction)),
                                    proposed,
                                    direction));
                        }
                    }
                }
            }
        return pq.isEmpty() ? 0 : pq.poll().score;
        }

    private int rotationsBetween(CardinalDirection facing, CardinalDirection direction) {
        if (facing.opposite() == direction) {
            return 2;
        }
        if (facing.equals(direction)) {
            return 0;
        }
        return 1;
    }

    private boolean isWall(CartesianPoint proposed) {
        return grid[proposed.y][N - proposed.x-1] == '#';
    }
}

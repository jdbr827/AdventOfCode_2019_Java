package year_2024.day_16;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.testng.internal.collections.Pair;
import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.*;

@AllArgsConstructor

class Status {
    CartesianPoint location;
    CardinalDirection facing;

    @Override
    public String toString() {
        return "Status{" +
                "location=" + location +
                ", facing=" + facing +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(location, status.location) && facing == status.facing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, facing);
    }
}

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

    public Status getStatus() {
        return new Status(location, facing);
    }
}


class Racetrack {
    Character[][] grid;
    int N;
    @Getter
    CartesianPoint start;

    @Getter
    CartesianPoint end;

    public Racetrack(String inputFilename) {
        grid = new AOCScanner(inputFilename).scanAsChar2DArray();
        N = grid.length;
        int M = grid[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 'S') {
                    start = new CartesianPoint(j, N - i - 1);
                    grid[i][j] = '.';
                } else if (grid[i][j] == 'E') {
                    end = new CartesianPoint(j, N - i - 1);
                    grid[i][j] = '.';
                }
            }
        }
    }

    boolean isSpace(CartesianPoint proposed) {
        return grid[N - proposed.y - 1][proposed.x] != '#';
    }
}
public class Day16 {
    Racetrack racetrack;

    public Day16(String inputFilename) {
        racetrack = new Racetrack(inputFilename);
    }


    // (i, j) => (j, N-i-1) ... (

    public int bestReindeerScore() {
        Set<Pair<CartesianPoint, CardinalDirection>> found = new HashSet<>();

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, racetrack.getStart(), CardinalDirection.EAST));
        while (!pq.isEmpty() && !pq.peek().location.equals(racetrack.getEnd())) {
            //System.out.println(pq);
            State state = pq.poll();
            assert state != null;
            if (found.add(Pair.of(state.location, state.facing))) {
                for (CardinalDirection direction : CardinalDirection.values()) {
                    CartesianPoint proposed = state.location.add(direction.velocity);
                    if (racetrack.isSpace(proposed) && !found.contains(Pair.of(proposed, direction))) {
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



    public int numGoodSeats() {
        //System.out.println(isWall(new CartesianPoint(12, 9)));
        Map<Status, Integer> bestScore = new HashMap<>();
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, racetrack.getStart(), CardinalDirection.EAST));

        Queue<Status> goodStatuses = new LinkedList<>();
        //int targetScore;
        while (!pq.isEmpty() && !pq.peek().location.equals(racetrack.getEnd())) {
            //System.out.println(pq);
            @NotNull State state = Objects.requireNonNull(pq.poll());
            if (!bestScore.containsKey(state.getStatus())) {
                bestScore.put(state.getStatus(), state.score);
                CartesianPoint proposedForward = state.location.add(state.facing.velocity);
                if (racetrack.isSpace(proposedForward) && !bestScore.containsKey(new Status(proposedForward, state.facing))) {
                    pq.add(new State(
                          state.score + 1,
                          proposedForward,
                          state.facing
                    ));
                }
                for (CardinalDirection direction : CardinalDirection.values()) {
                    if (!direction.equals(state.facing) && !bestScore.containsKey(new Status(state.location, direction))) {
                        pq.add(new State(
                                state.score + 1000*rotationsBetween(state.facing, direction),
                                state.location,
                                direction
                        ));
                    }
                }
            }
        }


        assert pq.peek() != null;
        int targetScore = pq.peek().score;
        while (!pq.isEmpty() && pq.peek().score == targetScore) {
            State state = pq.poll();
            if (state.location.equals(racetrack.getEnd())) {
                bestScore.put(state.getStatus(), targetScore);
                goodStatuses.add(state.getStatus());
            }
        }
        Set<CartesianPoint> goodLocations = new HashSet<>();
        while (!goodStatuses.isEmpty()) {
            Status status = goodStatuses.poll();
            goodLocations.add(status.location);
            int score = bestScore.get(status);
            for (CardinalDirection dir : CardinalDirection.values()) {
                CartesianPoint nbr = status.location.add(dir.opposite().velocity);
                Status nxtStatus = new Status(nbr, dir);
                if (racetrack.isSpace(nbr) && bestScore.containsKey(nxtStatus) && bestScore.get(nxtStatus) + 1 + (1000*rotationsBetween(dir, status.facing)) == score) {
                    goodStatuses.add(nxtStatus);
                }
            }
        }
        return goodLocations.size();

    }
}

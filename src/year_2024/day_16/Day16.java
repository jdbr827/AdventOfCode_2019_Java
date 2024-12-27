package year_2024.day_16;

import lombok.AllArgsConstructor;
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

public class Day16 {
    Character[][] grid;
    CartesianPoint start;
    CartesianPoint end;
    int N;

    public Day16(String inputFilename) {
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


    // (i, j) => (j, N-i-1) ... (

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
        return grid[N-proposed.y-1][proposed.x] == '#';
    }

    public int numGoodSeats() {
        //System.out.println(isWall(new CartesianPoint(12, 9)));
        Set<Status> found = new HashSet<>();
        Map<Status, Integer> bestScore = new HashMap<>();
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, start, CardinalDirection.EAST));

        Queue<Status> goodStatuses = new LinkedList<>();
        //int targetScore;
        while (!pq.isEmpty() && !pq.peek().location.equals(end)) {
            //System.out.println(pq);
            @NotNull State state = Objects.requireNonNull(pq.poll());
//            if (state.location.equals(end)) {
//                if (!bestScore.containsKey(state.getStatus())) {
//                    targetScore = state.score;
//                    goodStatuses.add(state.getStatus());
//                }
//
//            }
            if (!bestScore.containsKey(state.getStatus())) {
                bestScore.put(state.getStatus(), state.score);
                CartesianPoint proposedForward = state.location.add(state.facing.velocity);
                if (!isWall(proposedForward) && !bestScore.containsKey(new Status(proposedForward, state.facing))) {
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
        //System.out.println(targetScore);

        while (!pq.isEmpty() && pq.peek().score == targetScore) {
            State state = pq.poll();
            //System.out.println(state);
            if (state.location.equals(end)) {
                //System.out.println("HI");
                bestScore.put(state.getStatus(), targetScore);
                //System.out.println(bestScore.get(state.getStatus()));
                goodStatuses.add(state.getStatus());
            }
        }
        //System.out.println(goodStatuses.peek());
        //System.out.println(bestScore.entrySet());
        Set<CartesianPoint> goodLocations = new HashSet<>();
        //System.out.println(bestScore.get(new Status(new CartesianPoint(13, 1), CardinalDirection.EAST)));
        while (!goodStatuses.isEmpty()) {
            Status status = goodStatuses.poll();
            goodLocations.add(status.location);
            //System.out.println(bestScore);
            int score = bestScore.get(status);
            for (CardinalDirection dir : CardinalDirection.values()) {
                CartesianPoint nbr = status.location.add(dir.opposite().velocity);
                //System.out.println(nbr + " " + bestScore.get(nbr));
                Status nxtStatus = new Status(nbr, dir);
                if (!isWall(nbr) && bestScore.containsKey(nxtStatus) && bestScore.get(nxtStatus) + 1 + (1000*rotationsBetween(dir, status.facing)) == score) {
                    //System.out.println(nbr + " " + dir + " " + bestScore.get(nxtStatus));
                    goodStatuses.add(nxtStatus);
                }
            }
        }
        //System.out.println(goodLocations);
        return goodLocations.size();

    }
}

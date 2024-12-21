package year_2024.day_06;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

import java.util.HashSet;
import java.util.Set;

public class Day6 {
    Character[][] grid;
    Guard guard;
    int N;
    int M;
    final CartesianPoint startPoint;

    public Day6(String inputFilename) {
        grid = new AOCScanner(inputFilename).scanAsChar2DArray();
        N = grid.length;
        M = grid[0].length;
        startPoint = findStart();
        guard = new Guard((CartesianPoint) startPoint.clone(), CardinalDirection.NORTH);
    }

    private CartesianPoint findStart() {
        for (int i = 0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (grid[i][j] == '^') {
                    return new CartesianPoint(j,N-1-i);
                }
            }
        }
        return null;
    }

    public int numDistinctSpacesPatrolled() {
        guard.trackPath();
        return guard.spacesVisited.size();
    }

    public int numLoopCreatingObstacleLocations() {
        guard.trackPath();
        int tot = 0;
        for (CartesianPoint spacePatrolled : guard.spacesVisited) {
            if (addingObstacleHereCreatesLoop(spacePatrolled)) {
                tot += 1;
            }
        }
        return tot;
    }

    private boolean addingObstacleHereCreatesLoop(CartesianPoint spacePatrolled) {
        int i = N - spacePatrolled.y - 1;
        int j = spacePatrolled.x;
        if (grid[i][j] != '.') {return false;}
        System.out.println(startPoint);
        System.out.println(findStart());
        Guard alternateGuard = new Guard((CartesianPoint) startPoint.clone(), CardinalDirection.NORTH);
        grid[i][j] = '#';
        boolean ans = alternateGuard.getsStuckInLoop();
        grid[i][j] = '.';
        return ans;
    }

    private boolean isObstacle(CartesianPoint position) {
        return grid[N-1-position.y][position.x] == '#';
    }

    class Guard extends RotatingMovingRobot {
        Set<CartesianPoint> spacesVisited = new HashSet<>();
        Set<Pair<CartesianPoint, CardinalDirection>> statesVisited = new HashSet<>();
        public Guard(CartesianPoint position, CardinalDirection facing) {
            super(position, facing);
        }

        private void moveBackward() {
            rotateClockwise();
            rotateClockwise();
            moveForward();
            rotateClockwise();
            rotateClockwise();
        }

        public void trackPath() {
            while (position.isInBoundariesInclusive(0, M-1, 0, N-1)) {
                if (isObstacle(position)) {
                    moveBackward();
                    rotateClockwise();
                }
                spacesVisited.add(getPosition());
                moveForward();
            }

        }

        public boolean getsStuckInLoop() {
            while (position.isInBoundariesInclusive(0, M-1, 0, N-1)) {
                if (isObstacle(position)) {
                    moveBackward();
                    rotateClockwise();
                }
                if (!(statesVisited.add(Pair.of(getPosition(), getFacing())))) {
                    return true;
                }
                moveForward();
            }
            return false;
        }
    }
}

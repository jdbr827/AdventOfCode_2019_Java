package year_2024.day_06;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day11.RotatingMovingRobot;
import year_2019.day15.model.CardinalDirection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Day6 {
    Guard guard;
    int N;
    int M;
    CartesianPoint startPoint;
    Collection<CartesianPoint> obstacleLocations = new HashSet<>();

    public Day6(String inputFilename) {
        Character[][] grid = new AOCScanner(inputFilename).scanAsChar2DArray();
        N = grid.length;
        M = grid[0].length;
        for (int i = 0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (grid[i][j] == '^') {
                    startPoint = new CartesianPoint(j,N-1-i);
                } else if (grid[i][j] == '#') {
                    obstacleLocations.add(new CartesianPoint(j, N-1-i));
                }
            }
        }
        guard = new Guard((CartesianPoint) startPoint.clone(), CardinalDirection.NORTH);
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
        Guard alternateGuard = new Guard((CartesianPoint) startPoint.clone(), CardinalDirection.NORTH);
        obstacleLocations.add(spacePatrolled);
        boolean ans = alternateGuard.getsStuckInLoop();
        obstacleLocations.remove(spacePatrolled);
        return ans;
    }

    private boolean isObstacle(CartesianPoint position) {
        return obstacleLocations.contains(position);
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

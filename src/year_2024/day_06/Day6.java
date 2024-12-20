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

    public Day6(String inputFilename) {
        grid = new AOCScanner(inputFilename).scanAsChar2DArray();
        guard = new Guard(findGuardStartingPosition(), CardinalDirection.NORTH);
    }

    public CartesianPoint findGuardStartingPosition() {
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == '^') {
                    return new CartesianPoint(j,grid.length-1-i);
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
        int tot = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid.length; j++) {
                if (addingObstacleHereCreatesLoop(i, j)) {
                    tot += 1;
                }
            }
        }
        return tot;
    }

    private boolean addingObstacleHereCreatesLoop(int i, int j) {
        if (grid[i][j] != '.') {return false;}
        guard = new Guard(findGuardStartingPosition(), CardinalDirection.NORTH);
        grid[i][j] = '#';
        boolean ans = guard.getsStuckInLoop();
        grid[i][j] = '.';
        return ans;
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
            while (position.isInBoundariesInclusive(0, grid[0].length-1, 0, grid.length)) {
                if (isObstacle(position)) {
                    moveBackward();
                    rotateClockwise();
                }
                spacesVisited.add(getPosition());
                moveForward();
            }

        }

        public boolean getsStuckInLoop() {
            while (position.isInBoundariesInclusive(0, grid[0].length-1, 0, grid.length-1)) {
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

    private boolean isObstacle(CartesianPoint position) {
        return grid[grid.length-1-position.y][position.x] == '#';
    }
}

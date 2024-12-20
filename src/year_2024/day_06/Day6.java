package year_2024.day_06;

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

    class Guard extends RotatingMovingRobot {
        Set<CartesianPoint> spacesVisited = new HashSet<>();

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
    }

    private boolean isObstacle(CartesianPoint position) {
        return grid[grid.length-1-position.y][position.x] == '#';
    }
}

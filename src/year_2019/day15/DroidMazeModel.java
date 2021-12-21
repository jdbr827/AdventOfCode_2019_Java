package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    CartesianPoint droidLocation = new CartesianPoint(0, 0);
    Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point
    Map<Point, Integer> oxygenDistance = new HashMap<>(); // distance from starting point of a point
    private Stack<CardinalDirection> directionStack;
    private CardinalDirection attemptDirection;
    DroidMazeController controller;
    private final CardinalDirection startDirection = CardinalDirection.NORTH;

    DroidMazeModel(DroidMazeController controller) {
        this.controller = controller;
    }
    void moveDroid(CardinalDirection direction) {
        droidLocation.translate(direction.velocity.x, direction.velocity.y);
    }



    public void findOxygenTank() throws InterruptedException {
        attemptDirection = startDirection;
        directionStack = new Stack<>();
        DroidMazeOutputInstruction result;
        while ((result = controller.moveDroidFindingTank(attemptDirection)) != TANK) {
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            if (result == SPACE) {
                directionStack.push(attemptDirection);
                attemptDirection = attemptDirection.counterclockwise();

            } else {
                attemptDirection = attemptDirection.clockwise();
                while (!directionStack.isEmpty() && attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.pop();
                    controller.moveDroidFindingTank(prevDir.opposite());
                    attemptDirection = prevDir.clockwise();
                }
            }
        }
        directionStack.push(attemptDirection);
        attemptDirection = attemptDirection.counterclockwise();
        oxygenDistance.put((Point) droidLocation.clone(), 0);
    }

    public void oxygenTankDFS() throws InterruptedException {
        int result;
        directionStack.clear();
        attemptDirection = startDirection;
        while (!directionStack.isEmpty() || !attemptDirection.equals(startDirection.counterclockwise())) {
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            result = controller.moveDroidFromTank(attemptDirection);
            if (result != 0) {
                directionStack.push(attemptDirection);
                attemptDirection = attemptDirection.counterclockwise();
            } else {
                attemptDirection = attemptDirection.clockwise();
                while (!directionStack.isEmpty() && attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.pop();
                    controller.moveDroidFromTank(prevDir.opposite());
                    attemptDirection = prevDir.clockwise();
                }
            }
        }
    }
}

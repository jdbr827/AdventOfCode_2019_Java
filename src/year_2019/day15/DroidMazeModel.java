package year_2019.day15;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static year_2019.day15.DroidMazeController.CardinalDirection;

public class DroidMazeModel {
    public Map<Point,Boolean> isOpen = new HashMap<>();
    Point droidLocation = new Point(0, 0);
    Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point
    private Stack<CardinalDirection> directionStack;
    private CardinalDirection attemptDirection;
    DroidMazeController controller;

    DroidMazeModel(DroidMazeController controller) {
        this.controller = controller;
    }
    void moveDroid(CardinalDirection direction) {
        droidLocation.translate(direction.velocity.x, direction.velocity.y);
    }



    public void droidBrain() throws InterruptedException {
        CardinalDirection startDirection = CardinalDirection.NORTH;
        attemptDirection = startDirection;
        directionStack = new Stack<>();
        int result;
        while ((result = controller.moveDroid(attemptDirection)) != 2) {
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            if (result == 1) {
                directionStack.push(attemptDirection);
                attemptDirection = attemptDirection.counterclockwise();
            } else {
                attemptDirection = attemptDirection.clockwise();
                while (!directionStack.isEmpty() && attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.pop();
                    controller.moveDroid(prevDir.opposite());
                    attemptDirection = prevDir.clockwise();
                }
            }
        }
        directionStack.push(attemptDirection);
        attemptDirection = attemptDirection.counterclockwise();

        while (!directionStack.isEmpty() || !attemptDirection.equals(startDirection.counterclockwise())) {
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            result = controller.moveDroid(attemptDirection);
            if (result != 0) {
                directionStack.push(attemptDirection);
                attemptDirection = attemptDirection.counterclockwise();
            } else {
                attemptDirection = attemptDirection.clockwise();
                while (!directionStack.isEmpty() && attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.pop();
                    controller.moveDroid(prevDir.opposite());
                    attemptDirection = prevDir.clockwise();
                }
            }
        }
    }
}

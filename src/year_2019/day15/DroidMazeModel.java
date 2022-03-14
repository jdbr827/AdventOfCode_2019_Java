package year_2019.day15;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    final DroidMazeRobot droidMazeRobot = new DroidMazeRobot();
    Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point
    Map<Point, Integer> oxygenDistance = new HashMap<>(); // distance from starting point of a point
    DroidMazeController controller;

    DroidMazeModel(DroidMazeController controller) {
        this.controller = controller;
    }
    void moveDroid(CardinalDirection direction) {
        droidMazeRobot.moveDroid(direction);
    }



    public void findOxygenTank() throws InterruptedException {
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        droidMazeRobot.directionStack = new Stack<>();
        DroidMazeOutputInstruction result;
        while ((result = controller.moveDroidFindingTank(droidMazeRobot.attemptDirection)) != TANK) {
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            if (result == SPACE) {
                droidMazeRobot.directionStack.push(droidMazeRobot.attemptDirection);
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
            } else {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
                while (!droidMazeRobot.directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(droidMazeRobot.directionStack.peek().opposite())) {
                    CardinalDirection prevDir = droidMazeRobot.directionStack.pop();
                    controller.moveDroidFindingTank(prevDir.opposite());
                    droidMazeRobot.attemptDirection = prevDir.clockwise();
                }
            }
        }
        droidMazeRobot.directionStack.push(droidMazeRobot.attemptDirection);
        droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
        oxygenDistance.put((Point) droidMazeRobot.getDroidLocation().clone(), 0);
    }

    public void oxygenTankDFS() throws InterruptedException {
        DroidMazeOutputInstruction result;
        droidMazeRobot.directionStack.clear();
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        while (!droidMazeRobot.directionStack.isEmpty() || !droidMazeRobot.attemptDirection.equals(droidMazeRobot.startDirection.counterclockwise())) {
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            result = controller.moveDroidFromTank(droidMazeRobot.attemptDirection);
            if (result != WALL) {
                droidMazeRobot.directionStack.push(droidMazeRobot.attemptDirection);
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
            } else {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
                while (!droidMazeRobot.directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(droidMazeRobot.directionStack.peek().opposite())) {
                    CardinalDirection prevDir = droidMazeRobot.directionStack.pop();
                    controller.moveDroidFromTank(prevDir.opposite());
                    droidMazeRobot.attemptDirection = prevDir.clockwise();
                }
            }
        }
    }
}

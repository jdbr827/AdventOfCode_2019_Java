package year_2019.day15;

import year_2019.CartesianPoint;

import javax.swing.*;
import java.util.Stack;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    private final DroidMazeRobot droidMazeRobot = new DroidMazeRobot();
    DroidMazeController controller;

    DroidMazeModel(DroidMazeController controller) {
        this.controller = controller;
    }
    void moveDroid(CardinalDirection direction) {
        droidMazeRobot.moveDroid(direction);
    }



    // Stop when we have found the tank
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
    }

    public CartesianPoint getDroidLocation() {
        return droidMazeRobot.getDroidLocation();
    }

    public void allPointsDFS() throws InterruptedException {
        DroidMazeOutputInstruction result;
        droidMazeRobot.directionStack = new Stack<>();
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        while (!droidMazeRobot.directionStack.isEmpty() || !droidMazeRobot.attemptDirection.equals(droidMazeRobot.startDirection.counterclockwise())) {
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            System.out.println(getDroidLocation() + " " + droidMazeRobot.attemptDirection);
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

    public Stack<CardinalDirection> getDirectionStack() {
        return droidMazeRobot.directionStack;
    }
}

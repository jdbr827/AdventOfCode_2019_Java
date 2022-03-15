package year_2019.day15;

import year_2019.CartesianPoint;

import javax.swing.*;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    private final DroidMazeRobot droidMazeRobot = new DroidMazeRobot();
    DroidMazeController controller;
    Stack<CardinalDirection> directionStack = new Stack<>();

    DroidMazeModel(DroidMazeController controller) {
        this.controller = controller;
    }
    void moveDroid(CardinalDirection direction) {
        droidMazeRobot.moveDroid(direction);
    }



    // Stop when we have found the tank
    public void findOxygenTank() throws InterruptedException {
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        directionStack = new Stack<>();
        DroidMazeOutputInstruction result;
        while ((result = controller.moveDroidFindingTank(droidMazeRobot.attemptDirection)) != TANK) {
            findNextAttemptDirection(result, true);
        }
        droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
    }

    public CartesianPoint getDroidLocation() {
        return droidMazeRobot.getDroidLocation();
    }



    public void allPointsDFS() throws InterruptedException {
        DroidMazeOutputInstruction result;
        directionStack = new Stack<>();
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        while (!directionStack.isEmpty() || !droidMazeRobot.attemptDirection.equals(droidMazeRobot.startDirection.counterclockwise())) {
            result = controller.moveDroidFromTank(droidMazeRobot.attemptDirection);
            findNextAttemptDirection(result, false);
        }
    }

    private void findNextAttemptDirection(DroidMazeOutputInstruction result, boolean isFindingTank) throws InterruptedException {
        if (result != WALL) {
            droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
        } else {
            droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
            while (!directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(directionStack.peek().opposite())) {
                CardinalDirection prevDir = directionStack.peek(); // we will pop on move
                if (isFindingTank) {
                    controller.moveDroidFindingTank(prevDir.opposite());
                } else {
                    controller.moveDroidFromTank(prevDir.opposite());
                }
                droidMazeRobot.attemptDirection = prevDir.clockwise();
            }
        }
    }

    public Stack<CardinalDirection> getDirectionStack() {
        return directionStack;
    }

    public void resetOrigin(CartesianPoint droidLocation) {
        directionStack.clear();

    }
}

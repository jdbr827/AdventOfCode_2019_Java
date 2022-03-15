package year_2019.day15;

import year_2019.CartesianPoint;

import java.util.Stack;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    final DroidMazeRobot droidMazeRobot = new DroidMazeRobot();
    DroidMazeController controller;
    Stack<CardinalDirection> directionStack = new Stack<>();
    DroidMazeOutputInstruction result = SPACE; // result of previous attempted droid move

    DroidMazeModel(DroidMazeController controller) {
        this.controller = controller;
    }


    void moveDroid(CardinalDirection direction) {
        droidMazeRobot.moveDroid(direction);
        if (!directionStack.isEmpty() && directionStack.peek() == direction.opposite()) {
            directionStack.pop();
        } else {
            directionStack.push(direction);
        }
    }

    public CartesianPoint getDroidLocation() {
        return droidMazeRobot.getDroidLocation();
    }


    public void unifiedDFS() throws InterruptedException {
        directionStack = new Stack<>();
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        while (!controller.currentTracker.searchIsFinished()) {
            result = controller.attemptDroidMove(droidMazeRobot.attemptDirection, controller.currentTracker);
            if (result != WALL) {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
            } else {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
                while (!directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.peek(); // we will pop on move
                    controller.attemptDroidMove(prevDir.opposite(), controller.currentTracker);
                    droidMazeRobot.attemptDirection = prevDir.clockwise();
                }
            }
        }
    }

    public Stack<CardinalDirection> getDirectionStack() {
        return directionStack;
    }

    public void resetOrigin() {
        directionStack.clear();

    }
}

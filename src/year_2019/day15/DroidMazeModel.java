package year_2019.day15;

import year_2019.CartesianPoint;

import javax.swing.*;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

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
    }



    // Stop when we have found the tank
    // Assumes you are not starting on the tank
    public void findOxygenTank() throws InterruptedException {
        unifiedDFS(controller.findingTracker);
    }

    public CartesianPoint getDroidLocation() {
        return droidMazeRobot.getDroidLocation();
    }


    public void unifiedDFS(DistanceTracker distanceTracker) throws InterruptedException {
        directionStack = new Stack<>();
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        while (!distanceTracker.searchIsFinished()) {
            result = controller.attemptDroidMove(droidMazeRobot.attemptDirection, distanceTracker);
            findNextAttemptDirection(result, distanceTracker);
        }
    }

    public void allPointsDFS() throws InterruptedException {
        unifiedDFS(controller.oxygenTracker);
    }

    private void findNextAttemptDirection(DroidMazeOutputInstruction result, DistanceTracker distanceTracker) throws InterruptedException {
        if (result != WALL) {
            droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
        } else {
            droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
            while (!directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(directionStack.peek().opposite())) {
                CardinalDirection prevDir = directionStack.peek(); // we will pop on move
                controller.attemptDroidMove(prevDir.opposite(), distanceTracker);
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

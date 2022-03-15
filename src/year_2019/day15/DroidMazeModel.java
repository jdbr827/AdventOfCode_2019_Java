package year_2019.day15;

import year_2019.CartesianPoint;

import java.util.Stack;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    final DroidMazeRobot droidMazeRobot = new DroidMazeRobot();
    DroidMazeController controller;
    Stack<CardinalDirection> directionStack = new Stack<>();
    DroidMazeOutputInstruction result = SPACE; // result of previous attempted droid move
    DroidMazeBrain brain;

    DroidMazeModel(DroidMazeController controller, long[] brainTape) {
        this.controller = controller;
        brain = new DroidMazeBrain(brainTape);
        brain.startProgram();
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

    public DroidMazeOutputInstruction attemptDroidMove(CardinalDirection direction) throws InterruptedException {
        brain.sendInput(direction.inputInstruction);
        DroidMazeOutputInstruction outputInstruction = brain.getNextOutputInstruction();
        DistanceTracker distanceTracker = controller.currentTracker;
        int distance = distanceTracker.getDistanceAtCurrentLocation();
        CartesianPoint desiredPoint = new CartesianPoint(getDroidLocation().x + direction.velocity.x, getDroidLocation().y + direction.velocity.y);
        if (outputInstruction != WALL) {
            controller.moveDroid(direction);
            distance = Math.min(distance + 1, distanceTracker.getDistanceAtCurrentLocation());
            distanceTracker.setDistanceAtCurrentLocation(distance);
        }
        controller.paintPointInView(outputInstruction, desiredPoint);
        return outputInstruction;
    }


    public void unifiedDFS() throws InterruptedException {
        directionStack = new Stack<>();
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        while (!controller.currentTracker.searchIsFinished()) {
            result = attemptDroidMove(droidMazeRobot.attemptDirection);
            if (result != WALL) {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
            } else {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
                while (!directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.peek(); // we will pop on move
                    attemptDroidMove(prevDir.opposite());
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

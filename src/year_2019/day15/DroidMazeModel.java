package year_2019.day15;

import year_2019.CartesianPoint;

import javax.swing.*;
import java.util.Stack;

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
            if (result == SPACE) {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
            } else {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
                while (!directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.peek(); // we will pop when we move
                    controller.moveDroidFindingTank(prevDir.opposite());
                    droidMazeRobot.attemptDirection = prevDir.clockwise();
                }
            }
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
//            assert(model.dfsDistance.get(model.droidLocation).equals(directionStack.size()));
            System.out.println(getDroidLocation() + " " + droidMazeRobot.attemptDirection);
            result = controller.moveDroidFromTank(droidMazeRobot.attemptDirection);
            if (result != WALL) {
                //directionStack.push(droidMazeRobot.attemptDirection);
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.counterclockwise();
            } else {
                droidMazeRobot.attemptDirection = droidMazeRobot.attemptDirection.clockwise();
                while (!directionStack.isEmpty() && droidMazeRobot.attemptDirection.equals(directionStack.peek().opposite())) {
                    CardinalDirection prevDir = directionStack.peek(); // we will pop on move
                    controller.moveDroidFromTank(prevDir.opposite());
                    droidMazeRobot.attemptDirection = prevDir.clockwise();
                }
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

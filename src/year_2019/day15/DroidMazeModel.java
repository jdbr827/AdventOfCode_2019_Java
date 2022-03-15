package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    final DroidMazeRobot droidMazeRobot = new DroidMazeRobot();
    DroidMazeController controller;
    Stack<CardinalDirection> directionStack = new Stack<>();
    DroidMazeOutputInstruction result = SPACE; // result of previous attempted droid move
    DroidMazeBrain brain;
    MapDistanceTracker currentTracker = new TankFindingDistanceTracker(Color.BLACK);


    DroidMazeModel(DroidMazeController controller, long[] brainTape) {
        this.controller = controller;
        brain = new DroidMazeBrain(brainTape);
        brain.startProgram();
    }

    void setCurrentTracker(MapDistanceTracker tracker){
        currentTracker = tracker;
        resetOrigin();
    }


    void moveDroid(CardinalDirection direction) {
        droidMazeRobot.moveDroid(direction);
        updateDirectionStack(direction);
        controller.moveDroidInView();
    }

    private void updateDirectionStack(CardinalDirection direction) {
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
        DistanceTracker distanceTracker = currentTracker;
        int distance = distanceTracker.getDistanceAtCurrentLocation();
        CartesianPoint desiredPoint = new CartesianPoint(getDroidLocation().x + direction.velocity.x, getDroidLocation().y + direction.velocity.y);
        if (outputInstruction != WALL) {
            moveDroid(direction);
            distance = Math.min(distance + 1, distanceTracker.getDistanceAtCurrentLocation());
            distanceTracker.setDistanceAtCurrentLocation(distance);
        }
        controller.paintPointInView(outputInstruction, desiredPoint);
        return outputInstruction;
    }


    public void unifiedDFS() throws InterruptedException {
        directionStack = new Stack<>();
        droidMazeRobot.attemptDirection = droidMazeRobot.startDirection;
        while (!currentTracker.searchIsFinished()) {
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
        controller.updateStackInView();
        currentTracker.resetOrigin();
    }

     public void setCurrentTrackerToTank() {
        setCurrentTracker(new TankFindingDistanceTracker(Color.BLACK));
    }

    public void setCurrentTrackerToAllPoints() {
        setCurrentTracker(new AllPointsDistanceTracker(Color.BLUE));
    }

    abstract class MapDistanceTracker extends DistanceTracker {
        protected final Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point

        public MapDistanceTracker(Color color) {
            super(color);
        }

        @Override
        public Integer getDistanceAtCurrentLocation() {
            return dfsDistance.getOrDefault(getDroidLocation(), Integer.MAX_VALUE);
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             dfsDistance.put(getDroidLocation(), distance);
             controller.setDistanceInView(getDroidLocation(), distance, viewColor);
        }

        public void resetOrigin() {
            dfsDistance.clear();
            setDistanceAtCurrentLocation(0);
        }
    }

    class TankFindingDistanceTracker extends MapDistanceTracker {

        public TankFindingDistanceTracker(Color color) {
            super(color);
        }

        public Boolean searchIsFinished() {
            return result == TANK;
        }
    }

    class AllPointsDistanceTracker extends MapDistanceTracker {

        public AllPointsDistanceTracker(Color color) {
            super(color);
        }

        int directionsChecked = 0;
        int furthestDistance = 0;

        public Boolean searchIsFinished() {
            if(directionStack.isEmpty()) {
                directionsChecked++;
                return directionsChecked == 5;
            }
            return false;
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             super.setDistanceAtCurrentLocation(distance);
             furthestDistance = Math.max(furthestDistance, distance);
             controller.setFurthestDistanceInView(furthestDistance);
        }

    }

}

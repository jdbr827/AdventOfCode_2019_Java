package year_2019.day15.model;

import year_2019.CartesianPoint;
import year_2019.day15.DroidMazeController;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collector;

import static year_2019.day15.model.DroidMazeOutputInstruction.*;

public class DroidMazeModel {
    final DroidMazeRobot droidMazeRobot = new DroidMazeRobot();
    DroidMazeController controller;
    DirectionStack directionStack = new DirectionStack();
    DroidMazeOutputInstruction result = SPACE; // result of previous attempted droid move
    DroidMazeBrain brain;
    MapDistanceTracker currentTracker = new TankFindingDistanceTracker(Color.BLACK);


    public DroidMazeModel(DroidMazeController controller, long[] brainTape) {
        this.controller = controller;
        brain = new DroidMazeBrain(brainTape);
        brain.startProgram();
    }

    private void setCurrentTracker(MapDistanceTracker tracker){
        currentTracker = tracker;
        resetOrigin();
    }


    void moveDroid(CardinalDirection direction) {
        droidMazeRobot.moveDroid(direction);
        directionStack.moveDroid(direction);
        controller.moveDroidInView();
    }

    public CartesianPoint getDroidLocation() {
        return droidMazeRobot.getDroidLocation();
    }

    public DroidMazeOutputInstruction attemptDroidMove(CardinalDirection direction) throws InterruptedException {
        DroidMazeOutputInstruction outputInstruction = brainProcessMoveAttempt(direction);
        CartesianPoint desiredPoint = computeDesiredPoint(direction);
        controller.paintPointInView(outputInstruction, desiredPoint);
        if (outputInstruction != WALL) {
            int distance = currentTracker.getDistanceAtCurrentLocation();
            moveDroid(direction);
            distance = Math.min(distance + 1, currentTracker.getDistanceAtCurrentLocation());
            currentTracker.setDistanceAtCurrentLocation(distance);
        }
        return outputInstruction;
    }

    private CartesianPoint computeDesiredPoint(CardinalDirection direction) {
        CartesianPoint desiredPoint = getDroidLocation();
        desiredPoint.translate(direction.velocity.x, direction.velocity.y);
        return desiredPoint;
    }

    private DroidMazeOutputInstruction brainProcessMoveAttempt(CardinalDirection direction) throws InterruptedException {
        brain.sendInput(direction.inputInstruction);
        return brain.getNextOutputInstruction();
    }


    public void unifiedDFS() throws InterruptedException {
        directionStack = new DirectionStack();
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

    public DirectionStack getDirectionStack() {
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


    /**
     * A stack type class with the invariant that it represents the set of steps (without backtracks)
     * taken by the bot to get from the set origin to its current location.s
     */
    public static class DirectionStack {

        private final Stack<CardinalDirection> dStack = new Stack<>();

        public void moveDroid(CardinalDirection direction) {
            if (!dStack.isEmpty() && dStack.peek() == direction.opposite()) {
                dStack.pop();
            } else {
                dStack.push(direction);
            }
        }

        public String toString() {
            return dStack.stream().map(CardinalDirection::getShortName).collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString));
        }

        public void clear() {
            dStack.clear();
        }

        public boolean isEmpty() {
            return dStack.isEmpty();
        }

        public CardinalDirection peek() {
            return dStack.peek();
        }
    }

}

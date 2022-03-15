package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeController {
    DroidMazeBrain brain;
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model = new DroidMazeModel(this);
    MapDistanceTracker findingTracker = new MapDistanceTracker(Color.BLACK);
    MapDistanceTracker oxygenTracker = new MapDistanceTracker(Color.BLUE);



    public DroidMazeController(long[] brainTape) {
        brain = new DroidMazeBrain(brainTape);
        brain.startProgram();
        findingTracker.resetOrigin();
        view.paintPoint(new CartesianPoint(0, 0), Color.WHITE);
    }

    public int findOxygenTank() throws InterruptedException {
        model.findOxygenTank();
        return findingTracker.getDistanceAtCurrentLocation();
    }

    public DroidMazeOutputInstruction moveDroidFindingTank(CardinalDirection direction) throws InterruptedException {
        return attemptDroidMove(direction, findingTracker);
    }

    public DroidMazeOutputInstruction moveDroidFromTank(CardinalDirection direction) throws InterruptedException {
        return attemptDroidMove(direction, oxygenTracker);
    }

    public DroidMazeOutputInstruction attemptDroidMove(CardinalDirection direction, DistanceTracker distanceTracker) throws InterruptedException {
        brain.sendInput(direction.inputInstruction);
        DroidMazeOutputInstruction outputInstruction = brain.getNextOutputInstruction();
        int distance = distanceTracker.getDistanceAtCurrentLocation();
        CartesianPoint desiredPoint = new CartesianPoint(model.getDroidLocation().x + direction.velocity.x, model.getDroidLocation().y + direction.velocity.y);
        if (outputInstruction != WALL) {
            moveDroid(direction);
            distance = Math.min(distance + 1, distanceTracker.getDistanceAtCurrentLocation());
            distanceTracker.setDistanceAtCurrentLocation(distance);
        }
        view.paintPoint(desiredPoint, outputInstruction.getPaintColor());
        view.repaint();
        return outputInstruction;
    }

    private void moveDroid(CardinalDirection direction) {
        model.moveDroid(direction);
        view.setDroidLocation(model.getDroidLocation());
        if (!model.directionStack.isEmpty() && model.directionStack.peek() == direction.opposite()) {
            model.directionStack.pop();
        } else {
            model.directionStack.push(direction);
        }
        updateStackInView();
    }

    private void updateStackInView() {
        view.setDirectionStack(model.getDirectionStack().stream().map(CardinalDirection::getShortName).collect(Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString)));
    }

    /**
     * Searches the entire maze space and records distances from the starting point
     * @throws InterruptedException
     */
    public void computeAllDistancesFromPoint() throws InterruptedException {
        oxygenTracker.setDistanceAtCurrentLocation(0);
        model.allPointsDFS();
    }

    public void resetOrigin() {
        view.resetOrigin(model.getDroidLocation());
        model.resetOrigin(model.getDroidLocation());
        findingTracker.resetOrigin();
        updateStackInView();
        view.repaint();
    }

    class MapDistanceTracker implements DistanceTracker {
        private final Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point
        Color viewColor;

        public MapDistanceTracker(Color color) {
            setViewColor(color);
        }

        public void setViewColor(Color color) {
            viewColor = color;
        }
        @Override
        public Integer getDistanceAtCurrentLocation() {
            return dfsDistance.getOrDefault(model.getDroidLocation(), Integer.MAX_VALUE);
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             dfsDistance.put(model.getDroidLocation(), distance);
             view.setDistance(model.getDroidLocation(), distance, viewColor);
        }

        public void resetOrigin() {
            dfsDistance.clear();
            setDistanceAtCurrentLocation(0);
        }
    }

}

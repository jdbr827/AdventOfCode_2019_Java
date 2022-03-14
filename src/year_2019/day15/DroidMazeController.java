package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeController {
    DroidMazeBrain brain;
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model = new DroidMazeModel(this);
    FindingTankDistanceTracker findingTracker = new FindingTankDistanceTracker();
    OxygenDistanceTracker oxygenTracker = new OxygenDistanceTracker();



    public DroidMazeController(long[] brainTape) {
        brain = new DroidMazeBrain(brainTape);
        findingTracker.setDistanceAtCurrentLocation(0);
        brain.startProgram();
        view.setDistance(model.getDroidLocation(), 0);
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

    private DroidMazeOutputInstruction attemptDroidMove(CardinalDirection direction, DistanceTracker distanceTracker) throws InterruptedException {
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
    }

    //only works if current droid location is at oxygen tank
    public void computeOxygenTankDistances() throws InterruptedException {
        oxygenTracker.setDistanceAtCurrentLocation(0);
        model.oxygenTankDFS();
    }

    class FindingTankDistanceTracker implements DistanceTracker {
        private final Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point

        @Override
        public Integer getDistanceAtCurrentLocation() {
            return dfsDistance.getOrDefault(model.getDroidLocation(), Integer.MAX_VALUE);
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             dfsDistance.put(model.getDroidLocation(), distance);
             view.setDistance(model.getDroidLocation(), distance);
        }
    }

    class OxygenDistanceTracker implements DistanceTracker {
        private final Map<Point, Integer> oxygenDistance = new HashMap<>(); // distance from starting point of a point

        @Override
        public Integer getDistanceAtCurrentLocation() {
            return oxygenDistance.getOrDefault(model.getDroidLocation(), Integer.MAX_VALUE);
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
            oxygenDistance.put(model.getDroidLocation(), distance);
            view.setOxygenDistance(model.getDroidLocation(), distance);

        }
    }

}

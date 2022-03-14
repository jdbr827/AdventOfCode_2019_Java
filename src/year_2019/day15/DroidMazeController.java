package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeController {
    DroidMazeBrain brain;
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model = new DroidMazeModel(this);
    FindingTankDistanceTracker findingTracker = new FindingTankDistanceTracker();
    OxygenDistanceTracker oxygenTracker = new OxygenDistanceTracker();


    public DroidMazeController(long[] brainTape) {
        brain = new DroidMazeBrain(brainTape);
        brain.startProgram();
        model.dfsDistance.put((Point) model.droidMazeRobot.getDroidLocation().clone(), 0);
        view.setDistance(model.droidMazeRobot.getDroidLocation(), 0);
        view.paintPoint(new CartesianPoint(0, 0), Color.WHITE);
    }

    public int findOxygenTank() throws InterruptedException {
        model.findOxygenTank();
        return model.dfsDistance.get((Point) model.droidMazeRobot.getDroidLocation().clone());
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
        CartesianPoint desiredPoint = new CartesianPoint(model.droidMazeRobot.getDroidLocation().x + direction.velocity.x, model.droidMazeRobot.getDroidLocation().y + direction.velocity.y);
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
        view.setDroidLocation(model.droidMazeRobot.getDroidLocation());
    }

    private Integer getDroidOxygenDistance() {
        return model.oxygenDistance.getOrDefault(model.droidMazeRobot.getDroidLocation(), Integer.MAX_VALUE);
    }

    public void computeOxygenTankDistances() throws InterruptedException {
        model.oxygenTankDFS();
    }

    class FindingTankDistanceTracker implements DistanceTracker {

        @Override
        public Integer getDistanceAtCurrentLocation() {
            return model.dfsDistance.getOrDefault(model.droidMazeRobot.getDroidLocation(), Integer.MAX_VALUE);
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             model.dfsDistance.put(model.droidMazeRobot.getDroidLocation(), distance);
             view.setDistance(model.droidMazeRobot.getDroidLocation(), distance);
        }
    }

    class OxygenDistanceTracker implements DistanceTracker {

        @Override
        public Integer getDistanceAtCurrentLocation() {
            return getDroidOxygenDistance();
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
            model.oxygenDistance.put(model.droidMazeRobot.getDroidLocation(), distance);
            view.setOxygenDistance(model.droidMazeRobot.getDroidLocation(), distance);

        }
    }

}

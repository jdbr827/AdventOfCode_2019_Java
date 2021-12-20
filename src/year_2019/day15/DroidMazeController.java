package year_2019.day15;

import year_2019.IntCodeComputer.IntCode;
import year_2019.IntCodeComputer.IntCodeAPI;

import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DroidMazeController {
    IntCodeAPI brain;
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model = new DroidMazeModel(this);


    public DroidMazeController(long[] brainTape) {
        brain = new IntCodeAPI(brainTape);
        brain.startProgram();
        model.dfsDistance.put((Point) model.droidLocation.clone(), 0);
        view.setDistance(model.droidLocation, 0);
        view.paintPoint(new Point(0, 0), Color.WHITE);
    }

    public int findOxygenTank() throws InterruptedException {
        model.findOxygenTank();
        return model.dfsDistance.get((Point) model.droidLocation.clone());
    }

    public int moveDroidFindingTank(CardinalDirection direction) throws InterruptedException {
        brain.sendInput(direction.inputInstruction);
        int outputInstruction = brain.waitForOutputKnown().intValue();
        int distance = model.dfsDistance.get(model.droidLocation);
        Point desiredPoint = new Point(model.droidLocation.x + direction.velocity.x, model.droidLocation.y + direction.velocity.y);
        if (outputInstruction != 0) {
            model.moveDroid(direction);
            view.setDroidLocation(model.droidLocation);
            if (outputInstruction == 2) {
                view.paintPoint(desiredPoint, Color.GREEN);
            } else {
                view.paintPoint(desiredPoint, Color.WHITE);
            }
//            view.paintDroid(model.droidLocation);
            distance = Math.min(distance + 1, model.dfsDistance.getOrDefault(model.droidLocation, Integer.MAX_VALUE));
            model.dfsDistance.put(desiredPoint, distance);
            view.setDistance(model.droidLocation, distance);
        } else {
            view.paintPoint(desiredPoint, Color.BLACK);
        }
        view.repaint();
        return outputInstruction;
    }

    public int moveDroidFromTank(CardinalDirection direction) throws InterruptedException {
        brain.sendInput(direction.inputInstruction);
        int outputInstruction;
        int distance = getDroidOxygenDistance();
        Point desiredPoint = new Point(model.droidLocation.x + direction.velocity.x, model.droidLocation.y + direction.velocity.y);
        if ((outputInstruction = brain.waitForOutputKnown().intValue()) != 0) {
            model.moveDroid(direction);
            view.setDroidLocation(model.droidLocation);
            if (outputInstruction == 2) {
                view.paintPoint(desiredPoint, Color.GREEN);
            } else {
                view.paintPoint(desiredPoint, Color.WHITE);
            }
            distance = Math.min(distance + 1, getDroidOxygenDistance());
            model.oxygenDistance.put(desiredPoint, distance);
            view.setOxygenDistance(model.droidLocation, distance);
        } else {
            view.paintPoint(desiredPoint, Color.BLACK);
        }
        view.repaint();

        return outputInstruction;
    }

    private Integer getDroidOxygenDistance() {
        return model.oxygenDistance.getOrDefault(model.droidLocation, Integer.MAX_VALUE);
    }

    public void computeOxygenTankDistances() throws InterruptedException {
        model.oxygenTankDFS();
    }

}

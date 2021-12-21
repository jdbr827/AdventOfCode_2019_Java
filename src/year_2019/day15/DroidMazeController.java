package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeController {
    DroidMazeBrain brain;
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model = new DroidMazeModel(this);


    public DroidMazeController(long[] brainTape) {
        brain = new DroidMazeBrain(brainTape);
        brain.startProgram();
        model.dfsDistance.put((Point) model.droidMazeRobot.droidLocation.clone(), 0);
        view.setDistance(model.droidMazeRobot.droidLocation, 0);
        view.paintPoint(new CartesianPoint(0, 0), Color.WHITE);
    }

    public int findOxygenTank() throws InterruptedException {
        model.findOxygenTank();
        return model.dfsDistance.get((Point) model.droidMazeRobot.droidLocation.clone());
    }

    public DroidMazeOutputInstruction moveDroidFindingTank(CardinalDirection direction) throws InterruptedException {
        brain.sendInput(direction.inputInstruction);
        DroidMazeOutputInstruction outputInstruction = brain.getNextOutputInstruction();
        int distance = model.dfsDistance.get(model.droidMazeRobot.droidLocation);
        CartesianPoint desiredPoint = new CartesianPoint(model.droidMazeRobot.droidLocation.x + direction.velocity.x, model.droidMazeRobot.droidLocation.y + direction.velocity.y);
        if (outputInstruction != WALL) {
            model.moveDroid(direction);
            view.setDroidLocation(model.droidMazeRobot.droidLocation);
            if (outputInstruction == TANK) {
                view.paintPoint(desiredPoint, Color.GREEN);
            } else {
                view.paintPoint(desiredPoint, Color.WHITE);
            }
//            view.paintDroid(model.droidLocation);
            distance = Math.min(distance + 1, model.dfsDistance.getOrDefault(model.droidMazeRobot.droidLocation, Integer.MAX_VALUE));
            model.dfsDistance.put(desiredPoint, distance);
            view.setDistance(model.droidMazeRobot.droidLocation, distance);
        } else {
            view.paintPoint(desiredPoint, Color.BLACK);
        }
        view.repaint();
        return outputInstruction;
    }

    public DroidMazeOutputInstruction moveDroidFromTank(CardinalDirection direction) throws InterruptedException {
        brain.sendInput(direction.inputInstruction);
        DroidMazeOutputInstruction outputInstruction;
        int distance = getDroidOxygenDistance();
        CartesianPoint desiredPoint = new CartesianPoint(model.droidMazeRobot.droidLocation.x + direction.velocity.x, model.droidMazeRobot.droidLocation.y + direction.velocity.y);
        if ((outputInstruction = brain.getNextOutputInstruction()) != WALL) {
            model.moveDroid(direction);
            view.setDroidLocation(model.droidMazeRobot.droidLocation);
            if (outputInstruction == TANK) {
                view.paintPoint(desiredPoint, Color.GREEN);
            } else {
                view.paintPoint(desiredPoint, Color.WHITE);
            }
            distance = Math.min(distance + 1, getDroidOxygenDistance());
            model.oxygenDistance.put(desiredPoint, distance);
            view.setOxygenDistance(model.droidMazeRobot.droidLocation, distance);
        } else {
            view.paintPoint(desiredPoint, Color.BLACK);
        }
        view.repaint();

        return outputInstruction;
    }

    private Integer getDroidOxygenDistance() {
        return model.oxygenDistance.getOrDefault(model.droidMazeRobot.droidLocation, Integer.MAX_VALUE);
    }

    public void computeOxygenTankDistances() throws InterruptedException {
        model.oxygenTankDFS();
    }

}

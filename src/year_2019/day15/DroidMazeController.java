package year_2019.day15;

import year_2019.IntCodeComputer.IntCode;

import java.awt.*;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DroidMazeController {
    BlockingQueue<Long> inputs = new LinkedBlockingQueue<>();
    BlockingQueue<Long> outputs = new LinkedBlockingQueue<>();
    IntCode brain;
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model = new DroidMazeModel(this);


    public DroidMazeController(long[] brainTape) {
        brain = new IntCode(brainTape, inputs, outputs);
        brain.start();
        model.dfsDistance.put((Point) model.droidLocation.clone(), 0);
        view.setDistance(model.droidLocation, 0);
    }

    public int findOxygenTank() throws InterruptedException {
        model.findOxygenTank();
        return model.dfsDistance.get((Point) model.droidLocation.clone());
    }

    public int moveDroidFindingTank(CardinalDirection direction) throws InterruptedException {
        inputs.put(direction.inputInstruction);
        int outputInstruction = outputs.take().intValue();
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
            model.isOpen.put(desiredPoint, true);
            view.setDistance(model.droidLocation, distance);
        } else {
            view.paintPoint(desiredPoint, Color.BLACK);
             model.isOpen.put(desiredPoint, false);
        }
        view.table1.repaint();


        System.out.println(desiredPoint);
        System.out.println(outputInstruction);
        System.out.println(model.droidLocation);
//        view.paintPoint(desiredPoint, outputInstruction);
        System.out.println("-----");
        return outputInstruction;
    }

    public int moveDroidFromTank(CardinalDirection direction) throws InterruptedException {
        inputs.put(direction.inputInstruction);
        int outputInstruction = outputs.take().intValue();
        int distance = model.oxygenDistance.get(model.droidLocation);
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
            distance = Math.min(distance + 1, model.oxygenDistance.getOrDefault(model.droidLocation, Integer.MAX_VALUE));
            model.oxygenDistance.put(desiredPoint, distance);
            model.isOpen.put(desiredPoint, true);
            view.setOxygenDistance(model.droidLocation, distance);
        } else {
            view.paintPoint(desiredPoint, Color.BLACK);
            model.isOpen.put(desiredPoint, false);
        }
        view.table1.repaint();


        System.out.println(desiredPoint);
        System.out.println(outputInstruction);
        System.out.println(model.droidLocation);
//        view.paintPoint(desiredPoint, outputInstruction);
        System.out.println("-----");
        return outputInstruction;
    }

    public void computeOxygenTankDistances() throws InterruptedException {
        model.oxygenTankDFS();
    }

    enum CardinalDirection {
        NORTH(1L, new Point(0, 1)),
        EAST(3L, new Point(1, 0)),
        SOUTH(2L, new Point(0, -1)),
        WEST(4L, new Point(-1, 0));

        final long inputInstruction;
        final Point velocity;


        CardinalDirection(long inputInstruction, Point velocity) {
            this.inputInstruction = inputInstruction;
            this.velocity = velocity;
        }

        CardinalDirection opposite() {
            return CardinalDirection.values()[Math.floorMod(this.ordinal() + 2, 4)];
        }

        CardinalDirection clockwise() {
            return CardinalDirection.values()[Math.floorMod(this.ordinal() + 1, 4)];
        }

        CardinalDirection counterclockwise() {
            return CardinalDirection.values()[Math.floorMod(this.ordinal() + 3, 4)];
        }
    }
}

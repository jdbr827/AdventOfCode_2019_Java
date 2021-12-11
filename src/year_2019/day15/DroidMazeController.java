package year_2019.day15;

import year_2019.IntCodeComputer.IntCode;

import java.awt.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DroidMazeController {
    BlockingQueue<Long> inputs = new LinkedBlockingQueue<>();
    BlockingQueue<Long> outputs = new LinkedBlockingQueue<>();
    IntCode brain;
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model = new DroidMazeModel();

    public DroidMazeController(long[] brainTape) {
        brain = new IntCode(brainTape, inputs, outputs);
        brain.start();
    }

    public void moveDroid(CardinalDirection direction) throws InterruptedException {
        inputs.put(direction.inputInstruction);
        int outputInstruction = outputs.take().intValue();
        if (outputInstruction != 0) {
            model.moveDroid(direction);
            System.out.println(model.droidLocation);
        }
        System.out.println(outputInstruction);
    }

    enum CardinalDirection {
        NORTH(1L, new Point(0, 1)),
        SOUTH(2L, new Point(0, -1)),
        EAST(3L, new Point(1, 0)),
        WEST(4L, new Point(-1, 0));

        final long inputInstruction;
        final Point velocity;


        CardinalDirection(long inputInstruction, Point velocity) {
            this.inputInstruction = inputInstruction;
            this.velocity = velocity;

        }
    }
}

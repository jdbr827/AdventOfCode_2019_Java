package year_2019.day13;

import javafx.util.Pair;
import year_2019.IntCodeComputer.IntCode;
import year_2019.IntCodeComputer.IntCodeAPI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static year_2019.day13.Day13.DAY_13_PUZZLE_INPUT;

public class BrickBreakerController {

    public static final Point SCORE_OUTPUTS = new Point(-1, 0);
    private final long[] gameTape;


    Joystick joystick = new Joystick();
    BrickBreakerView view = new BrickBreakerView();
    int score = 0;
    private IntCodeAPI brain;

    BrickBreakerController(long[] gameTape) {
        this.gameTape = gameTape;
    }


    public Optional<Long> takeOrConfirmDeath(IntCode brain, BlockingQueue<Long> outputs) throws InterruptedException {
        Optional<Long> optVal = Optional.ofNullable(outputs.poll(40, TimeUnit.MILLISECONDS));
        while (!optVal.isPresent()) {
            if (!brain.isAlive()) {
                System.out.println("LOCK!");
                return Optional.empty();
            }
            if (brain.getState() == Thread.State.WAITING && outputs.isEmpty() && view.useAutopilot) {
                outputs.poll(40, TimeUnit.MILLISECONDS);
                joystick.doNextJoystickInput();
            }
            optVal = Optional.ofNullable(outputs.poll(40, TimeUnit.MILLISECONDS));
        }
        return optVal;
    }


    Optional<Pair<Point, Integer>> getNextOutput() throws Exception {
        Optional<Long> optX = brain.waitForOutputOptional(() -> {
            if (view.useAutopilot) {
                joystick.doNextJoystickInput();
            }
        });
        if (optX.isPresent()) {
            int x = optX.get().intValue();
            int y = brain.waitForOutputKnown().intValue();
            int obj_id = brain.waitForOutputKnown().intValue();
            return Optional.of(new Pair<>(new Point(x, y), obj_id));
        }
        return Optional.empty();
    }


    public int playGame() throws Exception {
        brain = new IntCodeAPI(gameTape, joystick.joystickInputs);
        brain.startProgram();
        Optional<Pair<Point, Integer>> nxt;
        while ((nxt = getNextOutput()).isPresent()) {
            processOneOutput(nxt.get());
        }

        return score;
    }

    private void processOneOutput(Pair<Point, Integer> nxt) {
        int obj_id = nxt.getValue();
        Point p = nxt.getKey();

        if (Objects.equals(p, SCORE_OUTPUTS)) {
            score = obj_id;
            view.scoreTextPane.setText(String.valueOf(score));
        } else {
            if (obj_id == 3) {
                joystick.paddleX = p.x;
            }
            if (obj_id == 4) {
                joystick.ballX = p.x;
            }
            view.populatePoint(p.x, p.y, obj_id);
        }
    }


    Map<Point, Integer> createGameGrid() throws InterruptedException {
        BlockingQueue<Long> inputs = new LinkedBlockingQueue<>();
        BlockingQueue<Long> outputs = new LinkedBlockingQueue<>();
        IntCode brain = new IntCode(DAY_13_PUZZLE_INPUT, inputs, outputs);
        Map<Point, Integer> gameGrid = new HashMap<>();

        brain.start();
        Optional<Long> optX;
        while ((optX = takeOrConfirmDeath(brain, outputs)).isPresent()) {
            int x = optX.get().intValue();
            int y = outputs.take().intValue();
            int obj_id = outputs.take().intValue();
            gameGrid.put(new Point(y, x), obj_id);
        }
        return gameGrid;
    }
}

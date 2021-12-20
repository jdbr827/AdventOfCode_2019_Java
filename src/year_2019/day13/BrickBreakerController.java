package year_2019.day13;

import javafx.util.Pair;
import year_2019.IntCodeComputer.IntCode;
import year_2019.IntCodeComputer.IntCodeAPI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static year_2019.day13.Day13.DAY_13_PUZZLE_INPUT;

public class BrickBreakerController {

    public static final Point SCORE_OUTPUTS = new Point(-1, 0);
    private final long[] gameTape;
    BrickBreakerModel model = new BrickBreakerModel();
    BrickBreakerView view = new BrickBreakerView();

    private IntCodeAPI brain;

    BrickBreakerController(long[] gameTape) {
        this.gameTape = gameTape;
    }

    Optional<Pair<Point, Integer>> getNextOutput() throws Exception {
        Optional<Long> optX = brain.waitForOutputOptional(() -> {
            if (view.useAutopilot) {
                model.joystick.doNextJoystickInput();
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
        brain = new IntCodeAPI(gameTape, model.joystick.joystickInputs);
        brain.startProgram();
        Optional<Pair<Point, Integer>> nxt;
        while ((nxt = getNextOutput()).isPresent()) {
            processOneOutput(nxt.get());
        }
        return model.score;
    }

    private void processOneOutput(Pair<Point, Integer> nxt) {
        int obj_id = nxt.getValue();
        Point p = nxt.getKey();

        if (Objects.equals(p, SCORE_OUTPUTS)) {
            setScore(obj_id);
        } else {
            populatePoint(obj_id, p);
        }
    }

    private void populatePoint(int obj_id, Point p) {
        model.populatePoint(p, obj_id);
        view.populatePoint(p.x, p.y, obj_id);
    }


    private void setScore(int score) {
        model.score = score;
        view.scoreTextPane.setText(String.valueOf(model.score));
    }


    Map<Point, Integer> createGameGrid() throws Exception {
        IntCodeAPI brain = new IntCodeAPI(DAY_13_PUZZLE_INPUT);
        Map<Point, Integer> gameGrid = new HashMap<>();
        brain.startProgram();
        Optional<Long> optX;
        while ((optX = brain.waitForOutputOptional()).isPresent()) {
            int x = optX.get().intValue();
            int y = brain.waitForOutputKnown().intValue();
            int obj_id = brain.waitForOutputKnown().intValue();
            gameGrid.put(new Point(y, x), obj_id);
        }
        return gameGrid;
    }
}

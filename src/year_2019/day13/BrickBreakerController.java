package year_2019.day13;

import javafx.util.Pair;
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
    private BrickBreakerBrain brain;

    BrickBreakerController(long[] gameTape) {
        this.gameTape = gameTape;
    }


    public int playGame() throws Exception {
        brain = new BrickBreakerBrain(gameTape, model.joystick);
        brain.startProgram();
        Optional<Pair<Point, Integer>> nxt;
        while ((nxt = brain.getNextOutput()).isPresent()) {
            processOneOutput(nxt.get());
        }
        return model.getScore();
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
        model.setScore(score);
        view.scoreTextPane.setText(String.valueOf(model.getScore()));
    }


    Map<Point, Integer> createGameGrid() throws Exception {
        BrickBreakerBrain brain = new BrickBreakerBrain(DAY_13_PUZZLE_INPUT);
        Map<Point, Integer> gameGrid = new HashMap<>();
        brain.startProgram();
        Optional<Pair<Point, Integer>> nxt;
        while ((nxt = brain.getNextOutput()).isPresent()) {
            int obj_id = nxt.get().getValue();
            Point p = nxt.get().getKey();
            gameGrid.put(new Point(p.y, p.x), obj_id);
        }
        return gameGrid;
    }

    public void flipUseAutopilot() {
        brain.setUseAutopilot(!brain.isUsingAutopilot());
    }
}

package year_2022.day_09;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;


public class Day9InstructionPair {
    IChessKing.MovementDirection direction;
    int numTimes;

    final static Map<String, IChessKing.MovementDirection> ABBREVIATIONS = Map.ofEntries(
            entry("R", IChessKing.MovementDirection.RIGHT),
            entry("L", IChessKing.MovementDirection.LEFT),
            entry("D", IChessKing.MovementDirection.DOWN),
            entry("U", IChessKing.MovementDirection.UP)
    );

    Day9InstructionPair(String direction, int numTimes) {
        this.numTimes = numTimes;
        this.direction = ABBREVIATIONS.get(direction);
    }
}

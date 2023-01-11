package year_2022.day_09;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import static java.util.Map.entry;

@AllArgsConstructor
public class Day9InstructionPair implements Consumer<Rope> {
    private final IChessKing.MovementDirection direction;
    private final int numTimes;

    final static Map<String, IChessKing.MovementDirection> ABBREVIATIONS = Map.ofEntries(
            entry("R", IChessKing.MovementDirection.RIGHT),
            entry("L", IChessKing.MovementDirection.LEFT),
            entry("D", IChessKing.MovementDirection.DOWN),
            entry("U", IChessKing.MovementDirection.UP)
    );

    Day9InstructionPair(String direction, int numTimes) {
        this(ABBREVIATIONS.get(direction), numTimes);
    }

    @Override
    public void accept(Rope rope) {
        for (int i=0; i<numTimes; i++) {
            rope.moveRope(direction);
        }
    }
}

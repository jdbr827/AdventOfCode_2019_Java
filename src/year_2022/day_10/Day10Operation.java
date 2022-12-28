package year_2022.day_10;

import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class Day10Operation {
    Function<Integer, Integer> operation;
    int timeToExecute;
    String name;


    public static Day10Operation Noop() {
        return new Day10Operation(x -> x, 1, "Noop");
    }

    public static Day10Operation addx(int x) {
        return new Day10Operation((n) -> x + n, 2, "addx " + x);
    }
}


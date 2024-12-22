package year_2024.day_11;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.testng.AssertJUnit.assertEquals;

public class Day11Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_11/input_aoc_2024_11.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_11/input_aoc_2024_11_small.txt";

    @Test
    public void test_part1() throws ExecutionException, InterruptedException {
        //assertEquals(7, Day11.numStonesAfterBlinkingNTimes(125L, 6));
        assertEquals(1, Day11.numStonesAfterBlinkingNTimes(List.of(1L), 1));
        assertEquals(6, Day11.numStonesAfterBlinkingNTimes(List.of(17L), 4));
        assertEquals(8, Day11.numStonesAfterBlinkingNTimes(List.of(17L), 5));
        assertEquals(15, Day11.numStonesAfterBlinkingNTimes(List.of(17L), 6));
        assertEquals(22, Day11.numStonesAfterBlinkingNTimes(SMALL_INPUT_FILENAME, 6));
        assertEquals(55312, Day11.numStonesAfterBlinkingNTimes(SMALL_INPUT_FILENAME, 25));
        assertEquals(187738, Day11.numStonesAfterBlinkingNTimes(INPUT_FILENAME, 25));
    }

    @Test void test_part2() {
        assertEquals(223767210249237L, Day11.numStonesAfterBlinkingNTimes(INPUT_FILENAME, 75));
    }
}

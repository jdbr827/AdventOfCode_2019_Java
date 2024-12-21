package year_2024.day_11;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day11Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_11/input_aoc_2024_11.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_11/input_aoc_2024_11_small.txt";

    @Test
    public void test_part1() {
        assertEquals(22, new Day11(SMALL_INPUT_FILENAME).numStonesAfterBlinkingNTimes(6));
        assertEquals(55312, new Day11(SMALL_INPUT_FILENAME).numStonesAfterBlinkingNTimes(25));
        assertEquals(187738, new Day11(INPUT_FILENAME).numStonesAfterBlinkingNTimes(25));
    }
}

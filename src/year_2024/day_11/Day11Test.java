package year_2024.day_11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Day11Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_11/input_aoc_2024_11.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_11/input_aoc_2024_11_small.txt";

    @Test
    public void test_part1() {
        assertEquals(22, Day11.numStonesAfterBlinkingNTimes(Day11.readIn(SMALL_INPUT_FILENAME), 6));
        assertEquals(55312, Day11.numStonesAfterBlinkingNTimes(Day11.readIn(SMALL_INPUT_FILENAME), 25));
        assertEquals(187738, Day11.numStonesAfterBlinkingNTimes(Day11.readIn(INPUT_FILENAME), 25));
    }

    @Test void test_part2() {
        System.out.println(Day11.numStonesAfterBlinkingNTimes(List.of(1L), 75));
        System.out.println(Day11.numStonesAfterBlinkingNTimes(Day11.readIn(SMALL_INPUT_FILENAME), 75));

        //System.out.println(new Day11(SMALL_INPUT_FILENAME).numStonesAfterBlinkingNTimes(75));
        //System.out.println(new Day11(INPUT_FILENAME).numStonesAfterBlinkingNTimes(75));
    }
}

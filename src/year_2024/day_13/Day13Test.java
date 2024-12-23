package year_2024.day_13;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day13Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_13/input_aoc_2024_13.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_13/input_aoc_2024_13_small.txt";

    @Test
    public void test_part1() {
        assertEquals(480, new Day13(SMALL_INPUT_FILENAME).fewestTokensToWinAllPossiblePrizes());
        System.out.println(new Day13(INPUT_FILENAME).fewestTokensToWinAllPossiblePrizes());

    }

}

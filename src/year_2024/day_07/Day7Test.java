package year_2024.day_07;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day7Test {


    public static final String INPUT_FILENAME = "src/year_2024/day_07/input_aoc_2024_7.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_07/input_aoc_2024_7_small.txt";

    @Test
    public void test_part1() {
        assertEquals(3749, new Day7(SMALL_INPUT_FILENAME).totalCalibrationResult());
        assertEquals(5512534574980L, new Day7(INPUT_FILENAME).totalCalibrationResult());
    }


    @Test
    public void test_part2() {
        assertEquals(11387, new Day7(SMALL_INPUT_FILENAME).totalCalibrationResultWithConcat());
        assertEquals(5512534574980L, new Day7(INPUT_FILENAME).totalCalibrationResultWithConcat());
    }

}

package year_2024.day_07;

import org.junit.jupiter.api.Test;
import year_2024.day_06.Day6;

import static org.testng.AssertJUnit.assertEquals;

public class Day7Test {


    public static final String INPUT_FILENAME = "src/year_2024/day_07/input_aoc_2024_7.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_07/input_aoc_2024_7_small.txt";

    @Test
    public void test_part1() {
        assertEquals(3749, new Day7(SMALL_INPUT_FILENAME).totalCalibrationResult());
        System.out.println(new Day7(INPUT_FILENAME).totalCalibrationResult());
        //assertEquals(5208, new Day7(INPUT_FILENAME).totalCalibrationResult());
    }

}

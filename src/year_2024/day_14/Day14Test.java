package year_2024.day_14;

import org.junit.jupiter.api.Test;
import viewModelUtil.CartesianPoint;

import static org.testng.AssertJUnit.assertEquals;

public class Day14Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_14/input_aoc_2024_14.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_14/input_aoc_2024_14_small.txt";

    @Test
    public void test_part1() {

        assertEquals(12, new Day14(SMALL_INPUT_FILENAME, 11, 7).safetyFactorAfter100Seconds());
        assertEquals(228410028, new Day14(INPUT_FILENAME, 101, 103).safetyFactorAfter100Seconds());
    }

}

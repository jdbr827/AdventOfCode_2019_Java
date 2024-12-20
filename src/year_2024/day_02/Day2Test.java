package year_2024.day_02;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day2Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_02/input_aoc_2024_2.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_02/input_aoc_2024_2_small.txt";


    @Test
    public void test_part1() {
        assertEquals(2, new Day2(SMALL_INPUT_FILENAME).numSafeReports());
        assertEquals(407, new Day2(INPUT_FILENAME).numSafeReports());
    }

    @Test
    public void test_part2() {
        assertEquals(4, new Day2(SMALL_INPUT_FILENAME).numDampenableReports());
    }
}

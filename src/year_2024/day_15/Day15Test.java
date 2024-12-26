package year_2024.day_15;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day15Test {


    public static final String INPUT_FILENAME = "src/year_2024/day_15/input_aoc_2024_15.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_15/input_aoc_2024_15_small.txt";
    public static final String MINI_INPUT_FILENAME = "src/year_2024/day_15/input_aoc_2024_15_mini.txt";


    @Test
    public void test_part1() {
        assertEquals(2028, new Day15(MINI_INPUT_FILENAME).sumOfGPSAfterMoving());
        assertEquals(10092, new Day15(SMALL_INPUT_FILENAME).sumOfGPSAfterMoving());
        assertEquals(1487337, new Day15(INPUT_FILENAME).sumOfGPSAfterMoving());
    }



}

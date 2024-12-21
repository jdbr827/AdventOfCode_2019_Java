package year_2024.day_10;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day10Test {


    public static final String INPUT_FILENAME = "src/year_2024/day_10/input_aoc_2024_10.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_10/input_aoc_2024_10_small.txt";

    @Test
    public void test_part1() {
        assertEquals(36, new Day10(SMALL_INPUT_FILENAME).trailheadScoreSum());
        System.out.println(new Day10(INPUT_FILENAME).trailheadScoreSum());
    }

}

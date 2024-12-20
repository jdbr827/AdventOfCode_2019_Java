package year_2024.day_06;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day6Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_06/input_aoc_2024_6.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_06/input_aoc_2024_6_small.txt";

    @Test
    public void test_part1() {
        assertEquals(41, new Day6(SMALL_INPUT_FILENAME).numDistinctSpacesPatrolled());
        assertEquals(5208, new Day6(INPUT_FILENAME).numDistinctSpacesPatrolled());
    }

    @Test
    public void test_part2() {
        assertEquals(6, new Day6(SMALL_INPUT_FILENAME).numLoopCreatingObstacleLocations());
        assertEquals(1972, new Day6(INPUT_FILENAME).numLoopCreatingObstacleLocations());
    }
}

package year_2024.day_12;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day12Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_12/input_aoc_2024_12.txt";
    public static final String MINI_INPUT_FILENAME = "src/year_2024/day_12/input_aoc_2024_12_mini.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_12/input_aoc_2024_12_small.txt";
    public static final String MEDIUM_INPUT_FILENAME = "src/year_2024/day_12/input_aoc_2024_12_medium.txt";

    @Test
    public void test_part1() {
        assertEquals(140, new Day12(MINI_INPUT_FILENAME).totalFencingCost());
        assertEquals(772, new Day12(SMALL_INPUT_FILENAME).totalFencingCost());
        assertEquals(1930, new Day12(MEDIUM_INPUT_FILENAME).totalFencingCost());
        assertEquals(1374934, new Day12(INPUT_FILENAME).totalFencingCost());
    }

    @Test
    public void test_part2() {
        assertEquals(80, new Day12(MINI_INPUT_FILENAME).totalFencingCostBulk());
        assertEquals(436, new Day12(SMALL_INPUT_FILENAME).totalFencingCostBulk());
        assertEquals(1206, new Day12(MEDIUM_INPUT_FILENAME).totalFencingCostBulk());
        assertEquals(841078, new Day12(INPUT_FILENAME).totalFencingCostBulk());
    }

}

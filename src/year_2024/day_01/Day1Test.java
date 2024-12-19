package year_2024.day_01;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;


public class Day1Test {


    public static final String INPUT_FILENAME = "src/year_2024/day_01/input_aoc_2024_1.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_01/input_aoc_2024_1_small.txt";


    @Test
    public void test_part1() {
        assertEquals(11, new Day1(SMALL_INPUT_FILENAME).getDistanceSum());
        assertEquals(936063, new Day1(INPUT_FILENAME).getDistanceSum());
    }

    @Test
    public void test_part2() {
        assertEquals(31, new Day1(SMALL_INPUT_FILENAME).getSimilarityScore());
        assertEquals(23150395, new Day1(INPUT_FILENAME).getSimilarityScore());
    }
}

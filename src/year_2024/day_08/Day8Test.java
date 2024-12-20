package year_2024.day_08;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day8Test {


    public static final String INPUT_FILENAME = "src/year_2024/day_08/input_aoc_2024_8.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_08/input_aoc_2024_8_small.txt";

    @Test
    public void test_part1() {
        assertEquals(14, new Day8(SMALL_INPUT_FILENAME).numLocationsWithAntiNodes());
        assertEquals(336, new Day8(INPUT_FILENAME).numLocationsWithAntiNodes());
    }

    @Test
    public void test_part2() {
        assertEquals(34, new Day8(SMALL_INPUT_FILENAME).numLocationsWithHarmonicAntiNodes());
        assertEquals(1131, new Day8(INPUT_FILENAME).numLocationsWithHarmonicAntiNodes());
    }
}

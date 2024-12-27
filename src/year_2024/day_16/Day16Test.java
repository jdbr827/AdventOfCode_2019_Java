package year_2024.day_16;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day16Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_16/input_aoc_2024_16.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_16/input_aoc_2024_16_small.txt";
    public static final String MINI_INPUT_FILENAME = "src/year_2024/day_16/input_aoc_2024_16_mini.txt";

    @Test
    public void test_part1() {
        assertEquals(7036, new Day16(MINI_INPUT_FILENAME).bestReindeerScore());
        assertEquals(11048, new Day16(SMALL_INPUT_FILENAME).bestReindeerScore());
        assertEquals(88416, new Day16(INPUT_FILENAME).bestReindeerScore());
    }

    @Test
    public void test_part2() {
        assertEquals(45, new Day16(MINI_INPUT_FILENAME).numGoodSeats());
        assertEquals(64, new Day16(SMALL_INPUT_FILENAME).numGoodSeats());
        assertEquals(442, new Day16(INPUT_FILENAME).numGoodSeats());
    }
}

package year_2024.day_09;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day9Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_09/input_aoc_2024_9.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_09/input_aoc_2024_9_small.txt";

    @Test
    public void test_part1() {
        assertEquals(1928, new Day9(SMALL_INPUT_FILENAME).resultingFilesystemChecksum());
        assertEquals(6200294120911L, new Day9(INPUT_FILENAME).resultingFilesystemChecksum());
    }
}

package year_2024.day_05;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day5Test {
    // R = num rules
    // U = num updates
    // N = number of pages in average update

    // R = 22; U=6; N ~=5
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_05/input_aoc_2024_5_small.txt";

    // R ~= 1000; U ~= 200; N ~= 20
    public static final String INPUT_FILENAME = "src/year_2024/day_05/input_aoc_2024_5.txt";




    @Test
    public void test_part1() {
        assertEquals(143, Day5.fromScanner(SMALL_INPUT_FILENAME).sumOfMiddlePagesOfCorrectlyOrderedUpdates());
        assertEquals(6051,Day5.fromScanner(INPUT_FILENAME).sumOfMiddlePagesOfCorrectlyOrderedUpdates());
    }
}


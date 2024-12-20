package year_2024.day_04;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day4Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_04/input_aoc_2024_4.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_04/input_aoc_2024_4_small.txt";

    @Test
    public void test_part1() {
        assertEquals(18, new Day4(SMALL_INPUT_FILENAME).timesXMASAppears());
        assertEquals(2500, new Day4(INPUT_FILENAME).timesXMASAppears());
    }

    @Test
    public void test_part2() {
        assertEquals(9, new Day4(SMALL_INPUT_FILENAME).timesMASAppearsInAnX());
        assertEquals(1933, new Day4(INPUT_FILENAME).timesMASAppearsInAnX());
    }

}

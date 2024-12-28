package year_2024.day_17;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day17Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_17/input_aoc_2024_17.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_17/input_aoc_2024_17_small.txt";

    @Test
    public void test_part1() {
        assertEquals("4,6,3,5,6,3,5,2,1,0", Day17.fromFile(SMALL_INPUT_FILENAME).getOutputAfterRunning());
        System.out.println(Day17.fromFile(INPUT_FILENAME).getOutputAfterRunning());
    }
}

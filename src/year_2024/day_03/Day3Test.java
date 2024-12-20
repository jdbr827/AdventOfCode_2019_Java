package year_2024.day_03;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Day3Test {

    public static final String INPUT_FILENAME = "src/year_2024/day_03/input_aoc_2024_3.txt";
    public static final String SMALL_INPUT_FILENAME = "src/year_2024/day_03/input_aoc_2024_3_small.txt";


    @Test
    public void test_part1() {
        assertEquals(161, new Day3(SMALL_INPUT_FILENAME).sumValidOps());
        assertEquals(167090022, new Day3(INPUT_FILENAME).sumValidOps());

    }

}

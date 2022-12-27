package year_2022.day_15;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {

     public static String EXAMPLE_INPUT = "src/year_2022/day_15/test_inputs/day_15_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_15/test_inputs/day_15_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        assertEquals(26, Day15.part1(EXAMPLE_INPUT, 10));
        assertEquals(5125700, Day15.part1(OFFICIAL_INPUT, 2000000));
    }

    @Test
    public void test_part2() throws FileNotFoundException {
        assertEquals(56000011, Day15.part2(EXAMPLE_INPUT, 20));
        assertEquals(11379394658764L, Day15.part2(OFFICIAL_INPUT, 4000000));
    }
}

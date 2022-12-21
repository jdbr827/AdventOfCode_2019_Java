package year_2022.day_08;

import org.junit.jupiter.api.Test;
import year_2022.day_16.Day16;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {


    public static String EXAMPLE_INPUT = "src/year_2022/day_16/test_inputs/day_16_sample_input.txt";
    //public static String OFFICIAL_INPUT = "src/year_2022/day_16/test_inputs/day_8_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        assertEquals(1651, Day16.part1(EXAMPLE_INPUT));
        //assertEquals(1792, Day8.part1(OFFICIAL_INPUT));

    }

    @Test
    public void test_part2() throws FileNotFoundException {
        //assertEquals(8, Day16.part2(EXAMPLE_INPUT));
        //assertEquals(334880, Day8.part2(OFFICIAL_INPUT));

    }
}

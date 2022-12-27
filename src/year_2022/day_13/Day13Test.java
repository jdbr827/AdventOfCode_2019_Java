package year_2022.day_13;

import org.junit.jupiter.api.Test;
import year_2022.day_13.Day13;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    public static String EXAMPLE_INPUT = "src/year_2022/day_13/test_inputs/day_13_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_13/test_inputs/day_13_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {;
        assertEquals(13, Day13.part1(EXAMPLE_INPUT));
        //assertEquals(1651, Day13.part1(EXAMPLE_INPUT, 30));
    }


//    @Test
//    public void test_part2() throws FileNotFoundException {
//        //assertEquals(8, Day16.part2(EXAMPLE_INPUT));
//        //assertEquals(334880, Day8.part2(OFFICIAL_INPUT));
//
//    }
}


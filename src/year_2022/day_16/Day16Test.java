package year_2022.day_16;

import org.junit.jupiter.api.Test;
import year_2022.day_16.Day16;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {


    public static String DUMMY_INPUT = "src/year_2022/day_16/test_inputs/day_16_dummy_input.txt";
    public static String EXAMPLE_INPUT = "src/year_2022/day_16/test_inputs/day_16_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_16/test_inputs/day_16_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        assertEquals(20, Day16.part1(DUMMY_INPUT, 3));
        assertEquals(20, Day16.part1(EXAMPLE_INPUT, 3));
        assertEquals(1651, Day16.part1(EXAMPLE_INPUT, 30));
    }

     @Test
    public void test_part1_official() throws FileNotFoundException {
        assertEquals(1737, Day16.part1(OFFICIAL_INPUT, 30));
    }

//    @Test
//    public void test_part2() throws FileNotFoundException {
//        //assertEquals(8, Day16.part2(EXAMPLE_INPUT));
//        //assertEquals(334880, Day8.part2(OFFICIAL_INPUT));
//
//    }
}

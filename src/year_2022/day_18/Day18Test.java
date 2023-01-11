package year_2022.day_18;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18Test {

    public static String DUMMY_INPUT = "src/year_2022/day_18/test_inputs/day_18_dummy_input.txt";

    public static String EXAMPLE_INPUT = "src/year_2022/day_18/test_inputs/day_18_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_18/test_inputs/day_18_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        assertEquals(10, Day18.part1(DUMMY_INPUT));
        assertEquals(64, Day18.part1(EXAMPLE_INPUT));
        assertEquals(4302, Day18.part1(OFFICIAL_INPUT));
    }

//    @Test
//    public void test_part2() throws FileNotFoundException {
//        assertEquals(29, Day12.part2(EXAMPLE_INPUT));
//        assertEquals(363, Day12.part2(OFFICIAL_INPUT));
//
//    }
}
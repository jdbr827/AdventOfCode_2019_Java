package year_2022.day_12.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import year_2022.day_12.Day12;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    public static String EXAMPLE_INPUT = "src/year_2022/day_12/test/day_12_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_12/test/day_12_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        Assertions.assertEquals(31, Day12.part1(EXAMPLE_INPUT));
        assertEquals(370, Day12.part1(OFFICIAL_INPUT));
    }

    @Test
    public void test_part2() throws FileNotFoundException {
        assertEquals(29, Day12.part2(EXAMPLE_INPUT));
        assertEquals(363, Day12.part2(OFFICIAL_INPUT));

    }
}


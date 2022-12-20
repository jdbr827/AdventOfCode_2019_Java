package year_2022.day_05;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {

    public static String EXAMPLE_INPUT = "src/year_2022/day_05/day_5_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_05/day_5_input.txt";

    @Test
    void test_part1() throws FileNotFoundException {
        assertEquals("CMZ", Day5.part1(EXAMPLE_INPUT));
        assertEquals("LJSVLTWQM",Day5.part1(OFFICIAL_INPUT));
    }

     @Test
    void test_part2() throws FileNotFoundException {
        assertEquals("MCD", Day5.part2(EXAMPLE_INPUT));
        assertEquals("BRQWDBBJM",Day5.part2(OFFICIAL_INPUT));
    }
}

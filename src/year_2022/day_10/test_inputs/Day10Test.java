package year_2022.day_10.test_inputs;

import org.junit.jupiter.api.Test;
import year_2022.day_10.Day10;

import java.awt.image.SampleModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    public static String DUMMY_INPUT = "src/year_2022/day_10/test_inputs/day_10_dummy_input.txt";
    public static String EXAMPLE_INPUT = "src/year_2022/day_10/test_inputs/day_10_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_10/test_inputs/day_10_input.txt";

    @Test
    void test_part1() {
        Day10.part1(DUMMY_INPUT);
        assertEquals(13140, Day10.part1(EXAMPLE_INPUT));
        assertEquals(15120, Day10.part1(OFFICIAL_INPUT));
    }
}

package year_2022.day_04;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {

    public static String OFFICIAL_INPUT = "src/year_2022/day_04/day_4_input.txt";
    public static String EXAMPLE_INPUT = "src/year_2022/day_04/day_4_sample_input.txt";

    @Test
    void test_part1() throws FileNotFoundException {
        assertEquals(2, Day4.part1(EXAMPLE_INPUT));
        assertEquals(530, Day4.part1(OFFICIAL_INPUT));
    }
}

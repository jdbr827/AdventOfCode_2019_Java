package year_2022.day_02;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    public static String OFFICIAL_INPUT = "src/year_2022/day_02/day_2_input.txt";
    public static String EXAMPLE_INPUT = "src/year_2022/day_02/day_2_sample_input.txt";

    @Test
    void test_2022_D2_P1() throws FileNotFoundException {
         assertEquals(15, Day2.part1(EXAMPLE_INPUT));
         assertEquals(8392, Day2.part1(OFFICIAL_INPUT));
    }

    @Test
    void test_2022_D2_P2() throws FileNotFoundException {
         assertEquals(12, Day2.part2(EXAMPLE_INPUT));
         assertEquals(10116, Day2.part2(OFFICIAL_INPUT));
    }

}

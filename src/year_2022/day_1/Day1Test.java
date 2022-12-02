package year_2022.day_1;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    public static String OFFICIAL_INPUT = "src/year_2022/day_1/day_1_input.txt";
    public static String EXAMPLE_INPUT = "src/year_2022/day_1/day_1_sample_input.txt";

    @Test
    void test_2022_D1_P1() throws FileNotFoundException {
         assertEquals(72017, Day1.part1(OFFICIAL_INPUT));
    }

    @Test
    void test_2022_D1_P2() throws FileNotFoundException {
         assertEquals(212520, Day1.part2(OFFICIAL_INPUT));
    }


}

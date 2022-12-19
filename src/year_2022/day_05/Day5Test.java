package year_2022.day_05;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {

    public static String EXAMPLE_INPUT = "src/year_2022/day_05/day_5_sample_input.txt";

    @Test
    void test_part1() throws FileNotFoundException {
        Day5.part1(EXAMPLE_INPUT);
        //assertEquals(530, Day4.part1(OFFICIAL_INPUT));
    }
}
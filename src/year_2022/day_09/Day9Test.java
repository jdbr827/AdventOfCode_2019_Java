package year_2022.day_09;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {

     public static String EXAMPLE_INPUT = "src/year_2022/day_09/day_9_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_09/day_9_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        assertEquals(13, Day9.part1(EXAMPLE_INPUT));
        assertEquals(6057,Day9.part1(OFFICIAL_INPUT));

    }

}

package year_2022.day_22;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day22Test {
    public static String EXAMPLE_INPUT = "src/year_2022/day_22/test_inputs/day_22_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_22/test_inputs/day_22_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        assertEquals(6032, Day22.part1(EXAMPLE_INPUT));
        assertEquals(162186, Day22.part1(OFFICIAL_INPUT));
    }

    @Test
    public void test_part2() throws FileNotFoundException {
        assertEquals(5031, Day22.part2(EXAMPLE_INPUT));
        //assertEquals(162186, Day22.part1(OFFICIAL_INPUT));
    }

}


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
        //assertEquals(1651, Day16.part1(EXAMPLE_INPUT, 30));
    }

}


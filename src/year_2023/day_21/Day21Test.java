package year_2023.day_21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test {

    public static final String SMALL_INPUT = "src/year_2023/day_21/day_21_2023_small_input.txt";
    public static final String INPUT = "src/year_2023/day_21/day_21_2023_input.txt";

    @Test
    public void test_part1() {
        assertEquals(16, new Day21(SMALL_INPUT).getSteps(6));
        assertEquals(3724, new Day21(INPUT).getSteps(64));
    }

}

package year_2023.day_22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day22Test {

    public static final String SMALL_INPUT = "src/year_2023/day_22/day_22_2023_small_input.txt";
    public static final String INPUT = "src/year_2023/day_22/day_22_2023_input.txt";

    @Test
    public void test_part1() {
        assertEquals(5, new Day22(SMALL_INPUT).getNumToSafelyDisintegrate());
        System.out.println(new Day22(INPUT).getNumToSafelyDisintegrate());
    }
}

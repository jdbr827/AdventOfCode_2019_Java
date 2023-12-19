package year_2023.day_11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    final static String SMALL_INPUT = "src/year_2023/day_11/day_11_2023_small_input.txt";
    final static String INPUT = "src/year_2023/day_11/day_11_2023_input.txt";

    @Test
    public void test_part1() {
        assertEquals(374, new Day11(SMALL_INPUT).sumOfShortestDistancePairs());
        assertEquals(9522407, new Day11(INPUT).sumOfShortestDistancePairs());
    }

}

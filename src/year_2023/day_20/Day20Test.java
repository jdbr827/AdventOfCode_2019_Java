package year_2023.day_20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {

    public static final String SMALL_INPUT = "src/year_2023/day_20/day_20_small_input.txt";
    public static final String MEDIUM_INPUT = "src/year_2023/day_20/day_20_medium_input.txt";
    public static final String INPUT = "src/year_2023/day_20/day_20_input.txt";

    @Test
    public void test_part1() {
        assertEquals(32000000, new Day20(SMALL_INPUT).getPulseProduct(1000));
        assertEquals(11687500, new Day20(MEDIUM_INPUT).getPulseProduct(1000));
        System.out.println(new Day20(INPUT).getPulseProduct(1000));
    }

}


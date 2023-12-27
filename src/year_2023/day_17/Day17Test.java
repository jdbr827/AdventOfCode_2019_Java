package year_2023.day_17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {

    public static final String SMALL_INPUT = "src/year_2023/day_17/day_17_small_input.txt";
    public static final String INPUT = "src/year_2023/day_17/day_17_small_input.txt";

    @Test
    public void test_part1() {
        assertEquals(102, new Day17(SMALL_INPUT).minimizeHeatLoss());
        System.out.println(new Day17(INPUT).minimizeHeatLoss());
    }

}

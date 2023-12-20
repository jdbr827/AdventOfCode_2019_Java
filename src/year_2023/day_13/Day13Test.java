package year_2023.day_13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {


    final static String SMALL_INPUT = "src/year_2023/day_13/day_13_2023_small_input.txt";
    final static String INPUT = "src/year_2023/day_13/day_13_2023_input.txt";


    @Test
    public void test_part1() {
        assertEquals(405, new Day13(SMALL_INPUT).getReflectionTotal());
        assertEquals(37561, new Day13(INPUT).getReflectionTotal());
    }


     @Test
    public void test_part2() {
        assertEquals(400, new Day13(SMALL_INPUT).getReflectionTotalWithSmudge());
        assertEquals(31108, new Day13(INPUT).getReflectionTotalWithSmudge());
    }

}
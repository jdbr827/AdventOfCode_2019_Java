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

    @Test
    public void test_part2_small_cases() {
        assertEquals(16, new Day21(SMALL_INPUT).getStepsInfiniteMap(6));
        assertEquals(50, new Day21(SMALL_INPUT).getStepsInfiniteMap(10));
        assertEquals(1594, new Day21(SMALL_INPUT).getStepsInfiniteMap(50));
        assertEquals(6536, new Day21(SMALL_INPUT).getStepsInfiniteMap(100));

        //assertEquals(3724, new Day21(INPUT).getStepsInfiniteMap(64));
    }


    @Test
    public void test_part2_large_cases() {
        assertEquals(167004, new Day21(SMALL_INPUT).getStepsInfiniteMap(500));
        assertEquals(668697, new Day21(SMALL_INPUT).getStepsInfiniteMap(1000));
        assertEquals(16733044, new Day21(SMALL_INPUT).getStepsInfiniteMap(5000));
        System.out.println(new Day21(INPUT).getStepsInfiniteMap(26501365));
    }
}

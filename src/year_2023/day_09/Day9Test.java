package year_2023.day_09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {

    static final String SMALL_INPUT = "src/year_2023/day_09/day_9_2023_small_input.txt";
    static final String INPUT = "src/year_2023/day_09/day_9_2023_input.txt";


    @Test
    public void test_part1() {
        assertEquals(114, Day9.part1(SMALL_INPUT));
        assertEquals(1916822650, Day9.part1(INPUT));
    }


     @Test
    public void test_part2() {
        assertEquals(2, Day9.part2(SMALL_INPUT));
        assertEquals(966, Day9.part2(INPUT));
    }
}

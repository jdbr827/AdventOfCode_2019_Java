package year_2023.day_04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {

    public final String SMALL_INPUT = "src/year_2023/day_04/day_4_2023_small_input.txt";
    public final String INPUT = "src/year_2023/day_04/day_4_2023_input.txt";

    @Test
    public void test_day4_part1() {
        assertEquals(13, Day4.day_4_part_1_2023(SMALL_INPUT));
        assertEquals(22488, Day4.day_4_part_1_2023(INPUT));
    }

}

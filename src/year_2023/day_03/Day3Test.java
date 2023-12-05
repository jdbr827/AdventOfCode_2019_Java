package year_2023.day_03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static year_2023.day_03.Day3.day_3_part_1_2023;

public class Day3Test {

    public final String SMALL_INPUT = "src/year_2023/day_03/day_3_2023_small_input.txt";
    public final String INPUT = "src/year_2023/day_03/day_3_2023_input.txt";

    @Test
    public void test_construct_day3() {
        assertEquals(4361, day_3_part_1_2023(SMALL_INPUT));
        assertEquals(539637, day_3_part_1_2023(INPUT));

    }

}

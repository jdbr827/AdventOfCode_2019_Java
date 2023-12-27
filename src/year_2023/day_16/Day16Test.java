package year_2023.day_16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {

    public static String SMALL_INPUT = "src/year_2023/day_16/day_16_small_input.txt";
    public static String INPUT = "src/year_2023/day_16/day_16_input.txt";



    @Test
    public void test_part1() {
        assertEquals(46, new Day16(SMALL_INPUT).count_energized_tiles());
        assertEquals(6816, new Day16(INPUT).count_energized_tiles());
    }
}

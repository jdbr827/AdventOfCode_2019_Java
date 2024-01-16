package year_2023.day_22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day22Test {

    public static final String SMALL_INPUT = "src/year_2023/day_22/day_22_2023_small_input.txt";
    public static final String INPUT = "src/year_2023/day_22/day_22_2023_input.txt";

    @Test
    public void test_part1_example() {
        assertEquals(5, new Day22(SMALL_INPUT).getNumToSafelyDisintegrate());

    }

    @Test
    public void test_part1_real() {
          assertEquals(407, new Day22(INPUT).getNumToSafelyDisintegrate());
    }

    @Test
    public void test_part2_example() {
        assertEquals(7, new Day22(SMALL_INPUT).getBiggestChainReactionNum());
        //System.out.println(new Day22(INPUT).getNumToSafelyDisintegrate());
    }

    @Test
    public void test_part2_real() {
          System.out.println(new Day22(INPUT).getBiggestChainReactionNum());
    }
}

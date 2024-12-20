package year_2023.day_14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {


    final static String SMALL_INPUT = "src/year_2023/day_14/day_14_2023_small_input.txt";
    final static String INPUT = "src/year_2023/day_14/day_14_2023_input.txt";


    @Test
    public void test_part1() {
        assertEquals(136, new Day14(SMALL_INPUT).calculateLoadOnNorthSupportBeams());
        assertEquals(109833, new Day14(INPUT).calculateLoadOnNorthSupportBeams());
    }

      @Test
    public void test_part2() {
        Day14 smallInput = new Day14(SMALL_INPUT);

        // TODO: Find Efficient Solution
        //smallInput.runCycle(1000000000);
        //assertEquals(64, smallInput.calculateLoadOnNorthSupportBeams());


        //assertEquals(109833, new Day14(INPUT).calculateLoadOnNorthSupportBeams());
    }
}

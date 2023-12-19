package year_2023.day_10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    final static String SMALL_INPUT = "src/year_2023/day_10/day_10_2023_small_input.txt";
    final static String MEDIUM_INPUT = "src/year_2023/day_10/day_10_2023_medium_input.txt";
    final static String INPUT = "src/year_2023/day_10/day_10_2023_input.txt";


    @Test
    void test_small_input() {
        assertEquals(4, Day10.furthestAwayPointInLoop(SMALL_INPUT, Pipe.SouthEast));
        assertEquals(8, Day10.furthestAwayPointInLoop(MEDIUM_INPUT, Pipe.SouthEast));
        System.out.println(Day10.furthestAwayPointInLoop(INPUT, Pipe.NorthWest));
    }
}

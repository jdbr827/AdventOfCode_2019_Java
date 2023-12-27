package year_2023.day_16;

import org.junit.jupiter.api.Test;
import viewModelUtil.CartesianPoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static year_2019.day15.model.CardinalDirection.EAST;

public class Day16Test {

    public static String SMALL_INPUT = "src/year_2023/day_16/day_16_small_input.txt";
    public static String INPUT = "src/year_2023/day_16/day_16_input.txt";



    @Test
    public void test_part1() {
        assertEquals(46, new Day16(SMALL_INPUT).runEnergyTest(new CartesianPoint(0, 0), EAST));
        assertEquals(6816, new Day16(INPUT).runEnergyTest(new CartesianPoint(0, 0), EAST));
    }


      @Test
    public void test_part2() {
        assertEquals(51, new Day16(SMALL_INPUT).maximizeEnergyOutput());
        assertEquals(8163, new Day16(INPUT).maximizeEnergyOutput());
    }
}

package year_2023.day_15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {



    final static String SMALL_INPUT = "src/year_2023/day_15/day_15_2023_small_input.txt";
    final static String INPUT = "src/year_2023/day_15/day_15_2023_input.txt";


    @Test
    public void test_part1() {
        assertEquals(1320, new Day15(SMALL_INPUT).sumOfHashResults());
        assertEquals(512797, new Day15(INPUT).sumOfHashResults());
    }


    @Test
    public void test_part2() {
        Day15 day15 = new Day15(SMALL_INPUT);
        day15.executeInitializationSequence();
        assertEquals(145, day15.computeFocusingPower());

        Day15 day15_i = new Day15(INPUT);
        day15_i.executeInitializationSequence();
        assertEquals(262454, day15_i.computeFocusingPower());


        //assertEquals(512797, new Day15(INPUT).sumOfHashResults());
    }
}


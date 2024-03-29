package year_2023.day_19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {

    public static final String SMALL_INPUT = "src/year_2023/day_19/day_19_2023_small_input.txt";
    public static final String INPUT = "src/year_2023/day_19/day_19_2023_input.txt";

    @Test
    public void test_part1() {
        assertEquals(19114, new Day19(SMALL_INPUT).sumRatingNumbersOfAcceptedMachineParts());
        assertEquals(449531, new Day19(INPUT).sumRatingNumbersOfAcceptedMachineParts());
    }


    @Test
    public void test_part2() {

       // System.out.println(new Day19.MachinePartSpace(0, 100, 0, 100, 0, 100, 0, 100, "in").applyRule(new Day19.Rule()))
        assertEquals(167409079868000L, new Day19(SMALL_INPUT).countDistinctAcceptedMachineParts());
        assertEquals(122756210763577L, new Day19(INPUT).countDistinctAcceptedMachineParts());
    }
}

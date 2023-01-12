package year_2022.day_11.test;

import org.junit.jupiter.api.Test;
import year_2022.day_11.Day11Scanner;
import year_2022.day_11.KeepAwaySimulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {
    //public static String DUMMY_INPUT = "src/year_2022/day_10/test_inputs/day_10_dummy_input.txt";
    public static String EXAMPLE_INPUT = "src/year_2022/day_11/test/day_11_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_11/test/day_11_input.txt";

    public long part1(String fileName) {
        Day11Scanner scanner = new Day11Scanner(fileName);
        KeepAwaySimulation keepAway = scanner.scanMonkeys();
        return keepAway.predictMonkeyBusiness(20);
    }

    public long part2(String fileName) {
        Day11Scanner scanner = new Day11Scanner(fileName);
        KeepAwaySimulation keepAway = scanner.scanMonkeys();
        return keepAway.predictMonkeyBusiness(10000);
    }

    @Test
    void test_part1() {
        assertEquals(10605, part1(EXAMPLE_INPUT));
        assertEquals(100345, part1(OFFICIAL_INPUT));
    }

    @Test
    void test_part2() {
        assertEquals(2713310158L, part2(EXAMPLE_INPUT));
        //assertEquals(100345, part1(OFFICIAL_INPUT));
    }
}

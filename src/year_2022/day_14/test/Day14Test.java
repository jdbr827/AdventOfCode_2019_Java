package year_2022.day_14.test;

import org.junit.jupiter.api.Test;
import year_2022.day_14.Day14Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    public static String EXAMPLE_INPUT = "src/year_2022/day_14/test/day_14_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_14/test/day_14_input.txt";

    @Test
    void test_part1() {
        assertEquals(24, Day14Model.fromCornerRocksFile(EXAMPLE_INPUT, 1).runModelOnly());
        assertEquals(817, Day14Model.fromCornerRocksFile(OFFICIAL_INPUT, 1).runModelOnly());
    }

     @Test
    void test_part2() {
        assertEquals(93, Day14Model.fromCornerRocksFile(EXAMPLE_INPUT, 2).runModelOnly());
        assertEquals(23416, Day14Model.fromCornerRocksFile(OFFICIAL_INPUT, 2).runModelOnly());
    }
}

package year_2019.day06;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {

    public static String INPUT_1 = "src/year_2019/day06/input_aoc_2019_6_small.txt";
    public static String INPUT_2 = "src/year_2019/day06/input_aoc_2019_6_2_small.txt";
    public static String OFFICIAL_INPUT = "src/year_2019/day06/input_aoc_2019_6.txt";

    @Test
    void test_D6P1() throws FileNotFoundException {
        assertEquals(621125, new Day6(OFFICIAL_INPUT).getOrbitalChecksum());
    }

    @Test
    void test_D6P2() throws FileNotFoundException {
        assertEquals(550, new Day6(OFFICIAL_INPUT).getDistanceToSanta());
    }

}

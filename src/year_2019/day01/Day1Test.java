package year_2019.day01;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static year_2019.day01.Day1.*;

public class Day1Test {
    public static final String INPUT_FILENAME = "src/year_2019/day01/input_aoc_2019_1.txt";

    @Test
    void testGetFuelRequired() {
        assertEquals(getFuelRequired(12), 2);
        assertEquals(getFuelRequired(14), 2);
        assertEquals(getFuelRequired(1969), 654);
        assertEquals(getFuelRequired(100756), 33583);
    }

    @Test
    void testPart1() throws FileNotFoundException {
        assertEquals(part1(INPUT_FILENAME), 3150224);
    }

    @Test
    void testGetMetaFuelRequired() {
        assertEquals(getMetaFuelRequired(14), 2);
        assertEquals(getMetaFuelRequired(1969), 966);
        assertEquals(getMetaFuelRequired(100756), 50346);
    }

    @Test
    void testPart2() throws FileNotFoundException {
        assertEquals(part2(INPUT_FILENAME), 4722484);
    }
}

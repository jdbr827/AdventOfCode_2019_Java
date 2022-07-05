package year_2019.day12;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static year_2019.day12.Day12.part1;
import static year_2019.day12.Day12.part2;

public class Day12Test {
    static final String SMALL_INPUT = "./src/year_2019/day12/day_12_small_input.txt";
    static final String MEDIUM_INPUT = "./src/year_2019/day12/day_12_medium_input.txt";
    static final String OFFICIAL_INPUT = "./src/year_2019/day12/day_12_input.txt";


    @Test
    void testDay12Part1() throws IOException {
         assertEquals(part1(SMALL_INPUT, 10), 179);
         assertEquals(part1(MEDIUM_INPUT, 100), 1940);
         assertEquals(part1(OFFICIAL_INPUT, 1000), 10189);
    }

    @Test
    void testDay12Part2() throws IOException {
        assertEquals(part2(SMALL_INPUT), BigInteger.valueOf(2772));
        assertEquals(part2(MEDIUM_INPUT), new BigInteger("4686774924"));
        assertEquals(part2(OFFICIAL_INPUT), new BigInteger("469671086427712"));
    }
}

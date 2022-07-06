package year_2019.day10;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static year_2019.day10.Day10.part1;

public class Day10Test {
    public static final String INPUT_1 = "src/year_2019/day10/day_10_input_1.txt";
    public static final String INPUT_2 = "src/year_2019/day10/day_10_input_2.txt";
    public static final String INPUT_3 = "src/year_2019/day10/day_10_input_3.txt";
    public static final String OFFICIAL_INPUT = "src/year_2019/day10/day_10_official_input.txt";

    @Test
    void testD10P1I1_bestPoint() throws IOException {
        assertEquals(Day10.findBestStation(INPUT_1), new Point(3, 4));
    }

     @Test
    void testD10P1I2_bestPoint() throws IOException {
        assertEquals(Day10.findBestStation(INPUT_2), new Point(5, 8));
    }

    @Test
    void testD10P1I1_numVisible() throws IOException {
        assertEquals(new Space(INPUT_1).numVisibleNeighbors(new Point(0, 1)), 7);
        assertEquals(new Space(INPUT_1).numVisibleNeighbors(new Point(3, 4)), 8);
    }

    @Test
    void testD10P1I2_numVisible() throws IOException {
        assertEquals(new Space(INPUT_2).numVisibleNeighbors(new Point(5, 8)), 33);
    }

    @Test
    void testD10P1() throws IOException {
        assertEquals(part1(OFFICIAL_INPUT), 256);
    }
}

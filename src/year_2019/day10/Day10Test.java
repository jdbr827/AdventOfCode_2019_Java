package year_2019.day10;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static year_2019.day10.Day10.part1;

public class Day10Test {
    public static final String INPUT_1 = "src/year_2019/day10/day_10_input_1.txt";
    public static final String INPUT_2 = "src/year_2019/day10/day_10_input_2.txt";
    public static final String INPUT_3 = "src/year_2019/day10/day_10_input_3.txt";
    public static final String INPUT_4 = "src/year_2019/day10/day_10_input_4.txt";
    public static final String INPUT_5 = "src/year_2019/day10/day_10_input_5.txt";

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
    void testD10P1I3_bestPoint() throws IOException {
        assertEquals(new Space(INPUT_3).findBestMonitoringStation(), new Point(1, 2));

    }

    @Test
    void testD10P1I3_numVisible() throws IOException {
        assertEquals(new Space(INPUT_3).numVisibleNeighbors(new Point(1, 2)), 35);
    }

    @Test
    void testD10P1I5_bestPoint() throws IOException {
        assertEquals(new Space(INPUT_5).findBestMonitoringStation(), new Point(11, 13));

    }

    @Test
    void testD10P1I5_numVisible() throws IOException {
        assertEquals(new Space(INPUT_5).numVisibleNeighbors(new Point(11, 13)), 210);
    }

    @Test
    void testD10P1() throws IOException {
        assertEquals(part1(OFFICIAL_INPUT), 256);
    }

     private Point whenVaporized(Integer i) throws IOException {
            return Day10.findIthVaporizedFromStation(INPUT_5, new Point(11, 13), i);
    }
    @Test
    void testD10P2() throws IOException {
        assertEquals(whenVaporized(1), new Point(11, 12));
        assertEquals(whenVaporized(2), new Point(12, 1));
        assertEquals(whenVaporized(3), new Point(12, 2));
        assertEquals(whenVaporized(10), new Point(12, 8));
        assertEquals(whenVaporized(20), new Point(16, 0));
        assertEquals(whenVaporized(50), new Point(16, 9));
        assertEquals(whenVaporized(100), new Point(10, 16));
        assertEquals(whenVaporized(199), new Point(9, 6));
        assertEquals(whenVaporized(200), new Point(8, 2));
        assertEquals(whenVaporized(201), new Point(10, 9));
        assertEquals(whenVaporized(299), new Point(11, 1));
    }
}

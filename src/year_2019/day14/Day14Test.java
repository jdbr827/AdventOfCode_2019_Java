package year_2019.day14;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    public static final String INPUT_1 = "src/year_2019/day14/day_14_input_1.txt";
    public static final String INPUT_2 = "src/year_2019/day14/day_14_input_2.txt";
    public static final String INPUT_3 = "src/year_2019/day14/day_14_input_3.txt";
    public static final String INPUT_4 = "src/year_2019/day14/day_14_input_4.txt";
    public static final String INPUT_5 = "src/year_2019/day14/day_14_input_5.txt";
    public static final String OFFICIAL_INPUT = "src/year_2019/day14/day_14_official_input.txt";

    @Test
    void testD14P1I1() throws IOException {
        assertEquals(Day14.part1(INPUT_1), 31);
    }

    @Test
    void testD14P1I2() throws IOException {
        assertEquals(Day14.part1(INPUT_2), 165);
    }

    @Test
    void testD14P1I3() throws IOException {
        assertEquals(Day14.part1(INPUT_3), 13312);
    }

    @Test
    void testD14P1I4() throws IOException {
        assertEquals(Day14.part1(INPUT_4), 180697);
    }

    @Test
    void testD14P1I5() throws IOException {
        assertEquals(Day14.part1(INPUT_5), 2210736);
    }

     @Test
    void testD14P1_official() throws IOException {
        assertEquals(Day14.part1(OFFICIAL_INPUT), 612880);
    }




}

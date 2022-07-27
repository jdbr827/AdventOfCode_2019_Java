package year_2019.day14;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    IDay14 d14 = new Day14() {};

    public static final String INPUT_1 = "src/year_2019/day14/day_14_input_1.txt";
    public static final String INPUT_2 = "src/year_2019/day14/day_14_input_2.txt";
    public static final String INPUT_3 = "src/year_2019/day14/day_14_input_3.txt";
    public static final String INPUT_4 = "src/year_2019/day14/day_14_input_4.txt";
    public static final String INPUT_5 = "src/year_2019/day14/day_14_input_5.txt";
    public static final String OFFICIAL_INPUT = "src/year_2019/day14/day_14_official_input.txt";

    @Test
    void testD14P1I1() throws IOException {
        assertEquals( 31, d14.leastOreRequiredToMakeOneFuel(INPUT_1));
    }

    @Test
    void testD14P1I2() throws IOException {
        assertEquals( 165, d14.leastOreRequiredToMakeOneFuel(INPUT_2));
    }

    @Test
    void testD14P1I3() throws IOException {
        assertEquals(13312, d14.leastOreRequiredToMakeOneFuel(INPUT_3));
    }

    @Test
    void testD14P1I4() throws IOException {
        assertEquals(180697, d14.leastOreRequiredToMakeOneFuel(INPUT_4));
    }

    @Test
    void testD14P1I5() throws IOException {
        assertEquals(2210736, d14.leastOreRequiredToMakeOneFuel(INPUT_5));
    }

    @Test
    void testD14P1_official() throws IOException {
        assertEquals(612880, d14.leastOreRequiredToMakeOneFuel(OFFICIAL_INPUT));
    }


    @Test
    void testD14P2I3() throws IOException {
        assertEquals(82892753, d14.mostFuelForOneTrillionOre(INPUT_3));
    }

    @Test
    void testD14P2I4() throws IOException {
        assertEquals(5586022, d14.mostFuelForOneTrillionOre(INPUT_4));
    }

   @Test
   void testD14P2I5() throws IOException {
        assertEquals(460664, d14.mostFuelForOneTrillionOre(INPUT_5));
    }

    @Test
   void testD14P2_official() throws IOException {
        assertEquals(2509120, d14.mostFuelForOneTrillionOre(OFFICIAL_INPUT));
    }




}

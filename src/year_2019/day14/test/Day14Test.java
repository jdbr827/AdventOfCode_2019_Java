package year_2019.day14.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import year_2019.day14.Day14;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    public static final String INPUT_1 = "src/year_2019/day14/test/day_14_input_1.txt";
    public static final String INPUT_2 = "src/year_2019/day14/test/day_14_input_2.txt";
    public static final String INPUT_3 = "src/year_2019/day14/test/day_14_input_3.txt";
    public static final String INPUT_4 = "src/year_2019/day14/test/day_14_input_4.txt";
    public static final String INPUT_5 = "src/year_2019/day14/test/day_14_input_5.txt";
    public static final String OFFICIAL_INPUT = "src/year_2019/day14/test/day_14_official_input.txt";

    @Test
    void testD14P1I1() throws IOException {
        Assertions.assertEquals( 31, Day14.fromReactionsFileName(INPUT_1).leastOreRequiredToMakeNFuel(1L));
    }

    @Test
    void testD14P1I2() throws IOException {
        assertEquals( 165, Day14.fromReactionsFileName(INPUT_2).leastOreRequiredToMakeNFuel(1L));
    }

    @Test
    void testD14P1I3() throws IOException {
        assertEquals(13312, Day14.fromReactionsFileName(INPUT_3).leastOreRequiredToMakeNFuel(1L));
    }

    @Test
    void testD14P1I4() throws IOException {
        assertEquals(180697, Day14.fromReactionsFileName(INPUT_4).leastOreRequiredToMakeNFuel(1L));
    }

    @Test
    void testD14P1I5() throws IOException {
        assertEquals(2210736, Day14.fromReactionsFileName(INPUT_5).leastOreRequiredToMakeNFuel(1L));
    }

    @Test
    void testD14P1_official() throws IOException {
        assertEquals(612880, Day14.fromReactionsFileName(OFFICIAL_INPUT).leastOreRequiredToMakeNFuel(1L));
    }

    final static long ONE_TRILLION = 1000000000000L;


    @Test
    void testD14P2I3() throws IOException {
        assertEquals(82892753, Day14.fromReactionsFileName(INPUT_3).mostFuelForNOre(ONE_TRILLION));
    }

    @Test
    void testD14P2I4() throws IOException {
        assertEquals(5586022, Day14.fromReactionsFileName(INPUT_4).mostFuelForNOre(ONE_TRILLION));
    }

   @Test
   void testD14P2I5() throws IOException {
        assertEquals(460664, Day14.fromReactionsFileName(INPUT_5).mostFuelForNOre(ONE_TRILLION));
    }

    @Test
   void testD14P2_official() throws IOException {
        assertEquals(2509120, Day14.fromReactionsFileName(OFFICIAL_INPUT).mostFuelForNOre(ONE_TRILLION));
    }




}

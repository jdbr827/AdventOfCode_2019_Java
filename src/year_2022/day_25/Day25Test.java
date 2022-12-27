package year_2022.day_25;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day25Test {

    public static String EXAMPLE_INPUT = "src/year_2022/day_25/day_25_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_25/day_25_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException {
        assertEquals(31, Day25.part1(EXAMPLE_INPUT));
        // assertEquals(370, Day25.part1(OFFICIAL_INPUT));
    }

    @Test
    public void test_convert_from_snafu() {
        assertEquals(0, SNAFUConverter.convertFromSNAFU("0"));
        assertEquals(1, SNAFUConverter.convertFromSNAFU("1"));
        assertEquals(2, SNAFUConverter.convertFromSNAFU("2"));
        assertEquals(3, SNAFUConverter.convertFromSNAFU("1="));
        assertEquals(4, SNAFUConverter.convertFromSNAFU("1-"));
        assertEquals(5, SNAFUConverter.convertFromSNAFU("10"));
        assertEquals(6, SNAFUConverter.convertFromSNAFU("11"));
        assertEquals(7, SNAFUConverter.convertFromSNAFU("12"));
        assertEquals(8, SNAFUConverter.convertFromSNAFU("2="));
        assertEquals(9, SNAFUConverter.convertFromSNAFU("2-"));
        assertEquals(10, SNAFUConverter.convertFromSNAFU("20"));
        assertEquals(15, SNAFUConverter.convertFromSNAFU("1=0"));
        assertEquals(20, SNAFUConverter.convertFromSNAFU("1-0"));
        assertEquals(2022, SNAFUConverter.convertFromSNAFU("1=11-2"));
        assertEquals(12345, SNAFUConverter.convertFromSNAFU("1-0---0"));
        assertEquals(314159265, SNAFUConverter.convertFromSNAFU("1121-1110-1=0"));


    }
}

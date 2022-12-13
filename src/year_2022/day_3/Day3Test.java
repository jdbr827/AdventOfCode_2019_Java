package year_2022.day_3;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.security.KeyException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {

    public static String EXAMPLE_INPUT = "src/year_2022/day_3/day_3_sample_input.txt";
    public static String OFFICIAL_INPUT = "src/year_2022/day_3/day_3_input.txt";

    @Test
    public void test_part1() throws FileNotFoundException, KeyException {
        assertEquals(157, Day3.part1(EXAMPLE_INPUT));
        assertEquals(7674, Day3.part1(OFFICIAL_INPUT));
    }

    @Test
    public void test_priority() throws Exception {
        assertEquals(1, Day3.getPriority('a'));
        assertEquals(10, Day3.getPriority('j'));
        assertEquals(26, Day3.getPriority('z'));
        assertEquals(27, Day3.getPriority('A'));
        assertEquals(36, Day3.getPriority('J'));
        assertEquals(52, Day3.getPriority('Z'));
    }

    @Test
    public void test_missing() throws Exception {
        assertEquals(16, Day3.getMissingPriority("vJrwpWtwJgWrhcsFMMfFFhFp"));
    }
}

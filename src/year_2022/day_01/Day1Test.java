package year_2022.day_01;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    public static String OFFICIAL_INPUT = "src/year_2022/day_01/day_1_input.txt";
    public static String EXAMPLE_INPUT = "src/year_2022/day_01/day_1_sample_input.txt";

    @Test
    void test_2022_D1_P1() {
         assertEquals(24000, Day1.part1(EXAMPLE_INPUT));
         assertEquals(72017, Day1.part1(OFFICIAL_INPUT));
    }

    @Test
    void test_2022_D1_P1_M2() {
         assertEquals(24000, Day1.part1Method2(EXAMPLE_INPUT));
         assertEquals(72017, Day1.part1Method2(OFFICIAL_INPUT));
    }

    @Test
    void test_2022_D1_P2_method1() throws IOException {
         assertEquals(45000, Day1.part2Method1(EXAMPLE_INPUT,3));
         assertEquals(212520, Day1.part2Method1(OFFICIAL_INPUT, 3));
    }

    @Test
    void test_2022_D1_P2_method2() throws IOException {
         assertEquals(45000, Day1.part2Method2(EXAMPLE_INPUT, 3));
         assertEquals(212520, Day1.part2Method2(OFFICIAL_INPUT, 3));
    }

     @Test
    void test_2022_D1_P2_method3() {
         assertEquals(45000, Day1.part2Method3(EXAMPLE_INPUT, 3));
         assertEquals(212520, Day1.part2Method3(OFFICIAL_INPUT, 3));
    }


}

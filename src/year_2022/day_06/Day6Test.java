package year_2022.day_06;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {

      public static String EXAMPLE_INPUT_1 = "src/year_2022/day_06/day_6_sample_1.txt";
      public static String EXAMPLE_INPUT_2 = "src/year_2022/day_06/day_6_sample_2.txt";
      public static String EXAMPLE_INPUT_3 = "src/year_2022/day_06/day_6_sample_3.txt";
      public static String EXAMPLE_INPUT_4 = "src/year_2022/day_06/day_6_sample_4.txt";
      public static String EXAMPLE_INPUT_5 = "src/year_2022/day_06/day_6_sample_5.txt";
      public static String OFFICIAL_INPUT = "src/year_2022/day_06/day_6_input.txt";



      @Test
      public void test_part1() throws FileNotFoundException {
          assertEquals(7, Day6.part1(EXAMPLE_INPUT_1));
          assertEquals(5, Day6.part1(EXAMPLE_INPUT_2));
          assertEquals(6, Day6.part1(EXAMPLE_INPUT_3));
          assertEquals(10, Day6.part1(EXAMPLE_INPUT_4));
          assertEquals(11, Day6.part1(EXAMPLE_INPUT_5));
          System.out.println(Day6.part1(OFFICIAL_INPUT));
      }

}

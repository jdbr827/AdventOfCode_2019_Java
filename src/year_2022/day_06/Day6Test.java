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
          assertEquals(1850, Day6.part1(OFFICIAL_INPUT));
      }


      @Test
      public void test_part2() throws FileNotFoundException {
          assertEquals(19, Day6.part2(EXAMPLE_INPUT_1));
          assertEquals(23, Day6.part2(EXAMPLE_INPUT_2));
          assertEquals(23, Day6.part2(EXAMPLE_INPUT_3));
          assertEquals(29, Day6.part2(EXAMPLE_INPUT_4));
          assertEquals(26, Day6.part2(EXAMPLE_INPUT_5));
          assertEquals(2823, Day6.part2(OFFICIAL_INPUT));
      }

      @Test
      public void test_method2() throws FileNotFoundException {
          assertEquals(7, new Day6(EXAMPLE_INPUT_1, 4).findStepsUntilLastNAllDiffMethod2());
          assertEquals(5, new Day6(EXAMPLE_INPUT_2, 4).findStepsUntilLastNAllDiffMethod2());
          assertEquals(6, new Day6(EXAMPLE_INPUT_3, 4).findStepsUntilLastNAllDiffMethod2());
          assertEquals(10, new Day6(EXAMPLE_INPUT_4,4).findStepsUntilLastNAllDiffMethod2());
          assertEquals(11, new Day6(EXAMPLE_INPUT_5, 4).findStepsUntilLastNAllDiffMethod2());
          assertEquals(1850, new Day6(OFFICIAL_INPUT, 4).findStepsUntilLastNAllDiffMethod2());

          assertEquals(19, new Day6(EXAMPLE_INPUT_1, 14).findStepsUntilLastNAllDiffMethod2());
          assertEquals(23, new Day6(EXAMPLE_INPUT_2, 14).findStepsUntilLastNAllDiffMethod2());
          assertEquals(23, new Day6(EXAMPLE_INPUT_3, 14).findStepsUntilLastNAllDiffMethod2());
          assertEquals(29, new Day6(EXAMPLE_INPUT_4, 14).findStepsUntilLastNAllDiffMethod2());
          assertEquals(26, new Day6(EXAMPLE_INPUT_5, 14).findStepsUntilLastNAllDiffMethod2());
          assertEquals(2823, new Day6(OFFICIAL_INPUT, 14).findStepsUntilLastNAllDiffMethod2());

      }


       @Test
      public void test_method3() throws FileNotFoundException {
          assertEquals(7, new Day6(EXAMPLE_INPUT_1, 4).findStepsUntilLastNAllDiffMethod3(4));
          assertEquals(5, new Day6(EXAMPLE_INPUT_2, 4).findStepsUntilLastNAllDiffMethod3(4));
          assertEquals(6, new Day6(EXAMPLE_INPUT_3, 4).findStepsUntilLastNAllDiffMethod3(4));
          assertEquals(10, new Day6(EXAMPLE_INPUT_4, 4).findStepsUntilLastNAllDiffMethod3(4));
          assertEquals(11, new Day6(EXAMPLE_INPUT_5,4 ).findStepsUntilLastNAllDiffMethod3(4));
          assertEquals(1850, new Day6(OFFICIAL_INPUT, 4).findStepsUntilLastNAllDiffMethod3( 4));

          assertEquals(19, new Day6(EXAMPLE_INPUT_1, 14).findStepsUntilLastNAllDiffMethod3( 14));
          assertEquals(23, new Day6(EXAMPLE_INPUT_2, 14).findStepsUntilLastNAllDiffMethod3( 14));
          assertEquals(23, new Day6(EXAMPLE_INPUT_3, 14).findStepsUntilLastNAllDiffMethod3( 14));
          assertEquals(29, new Day6(EXAMPLE_INPUT_4, 14).findStepsUntilLastNAllDiffMethod3( 14));
          assertEquals(26, new Day6(EXAMPLE_INPUT_5, 14).findStepsUntilLastNAllDiffMethod3( 14));
          assertEquals(2823, new Day6(OFFICIAL_INPUT, 14).findStepsUntilLastNAllDiffMethod3( 14));

      }

}

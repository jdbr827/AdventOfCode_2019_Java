package year_2022.day_06;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {

      public static String EXAMPLE_INPUT_1 = "src/year_2022/day_06/test_inputs/day_6_sample_1.txt";
      public static String EXAMPLE_INPUT_2 = "src/year_2022/day_06/test_inputs/day_6_sample_2.txt";
      public static String EXAMPLE_INPUT_3 = "src/year_2022/day_06/test_inputs/day_6_sample_3.txt";
      public static String EXAMPLE_INPUT_4 = "src/year_2022/day_06/test_inputs/day_6_sample_4.txt";
      public static String EXAMPLE_INPUT_5 = "src/year_2022/day_06/test_inputs/day_6_sample_5.txt";
      public static String OFFICIAL_INPUT = "src/year_2022/day_06/test_inputs/day_6_input.txt";

      final int HELPER_METHODS = 2;
      final int ALGO_METHODS = 3;



      @Test
      public void test_part1() throws FileNotFoundException {
          assertEquals(7, IDay6.part1(EXAMPLE_INPUT_1));
          assertEquals(5, IDay6.part1(EXAMPLE_INPUT_2));
          assertEquals(6, IDay6.part1(EXAMPLE_INPUT_3));
          assertEquals(10, IDay6.part1(EXAMPLE_INPUT_4));
          assertEquals(11, IDay6.part1(EXAMPLE_INPUT_5));
          assertEquals(1850, IDay6.part1(OFFICIAL_INPUT));
      }


      @Test
      public void test_part2() throws FileNotFoundException {
          assertEquals(19, IDay6.part2(EXAMPLE_INPUT_1));
          assertEquals(23, IDay6.part2(EXAMPLE_INPUT_2));
          assertEquals(23, IDay6.part2(EXAMPLE_INPUT_3));
          assertEquals(29, IDay6.part2(EXAMPLE_INPUT_4));
          assertEquals(26, IDay6.part2(EXAMPLE_INPUT_5));
          assertEquals(2823, IDay6.part2(OFFICIAL_INPUT));
      }

      private void test_a_helper_method(int algoMethod, int helperMethod) throws FileNotFoundException {
          assertEquals(7, IDay6.solve(EXAMPLE_INPUT_1, 4, algoMethod, helperMethod));
          assertEquals(5, IDay6.solve(EXAMPLE_INPUT_2, 4, algoMethod, helperMethod));
          assertEquals(6, IDay6.solve(EXAMPLE_INPUT_3, 4, algoMethod, helperMethod));
          assertEquals(10, IDay6.solve(EXAMPLE_INPUT_4, 4, algoMethod, helperMethod));
          assertEquals(11, IDay6.solve(EXAMPLE_INPUT_5, 4, algoMethod, helperMethod));
          assertEquals(1850, IDay6.solve(OFFICIAL_INPUT, 4, algoMethod, helperMethod));

          assertEquals(19, IDay6.solve(EXAMPLE_INPUT_1, 14, algoMethod, helperMethod));
          assertEquals(23, IDay6.solve(EXAMPLE_INPUT_2, 14, algoMethod, helperMethod));
          assertEquals(23, IDay6.solve(EXAMPLE_INPUT_3, 14, algoMethod, helperMethod));
          assertEquals(29, IDay6.solve(EXAMPLE_INPUT_4, 14, algoMethod, helperMethod));
          assertEquals(26, IDay6.solve(EXAMPLE_INPUT_5, 14, algoMethod, helperMethod));
          assertEquals(2823, IDay6.solve(OFFICIAL_INPUT, 14, algoMethod, helperMethod));
      }



      @Test
      public void test_all_solution_methods() throws FileNotFoundException {
          for (int algo=1; algo < ALGO_METHODS + 1; algo++) {
              for (int h = 1; h < HELPER_METHODS + 1; h++) {
                  test_a_helper_method(algo, h);
              }
          }
      }


}

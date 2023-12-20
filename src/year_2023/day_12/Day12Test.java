package year_2023.day_12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    final static String SMALL_INPUT = "src/year_2023/day_12/day_12_2023_small_input.txt";
    final static String INPUT = "src/year_2023/day_12/day_12_2023_input.txt";


     @Test
    void test_get_arrangement_counts() {
        assertEquals(1, new SpringRow("???.###", List.of(1,1,3)).getArrangementCount());
        assertEquals(2, new SpringRow(".??", List.of(1)).getArrangementCount());
        assertEquals(4, new SpringRow(".??..??...?##.", List.of(1, 1, 3)).getArrangementCount());
    }

    @Test
    void test_part1() {
        assertEquals(21, Day12.createNewDay12(SMALL_INPUT).sumOfArrangementCounts());
        assertEquals(7718, Day12.createNewDay12(INPUT).sumOfArrangementCounts());
    }

     @Test
    void test_part2() {
        assertEquals(525152, Day12.createDay12Part2(SMALL_INPUT).sumOfArrangementCounts());
        assertEquals(128741994134728L, Day12.createDay12Part2(INPUT).sumOfArrangementCounts());
    }
}



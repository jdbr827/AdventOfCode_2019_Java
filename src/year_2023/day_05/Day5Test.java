package year_2023.day_05;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {

    final String SMALL_INPUT = "src/year_2023/day_05/day_5_2023_small_input.txt";
    final String INPUT = "src/year_2023/day_05/day_5_2023_input.txt";

    @Test
    public void test_mapping_rules() {
        AlmanacMap almanacMap = new AlmanacMap("seed", "soil", List.of(
                new AlmanacMapRule(50L, 98L, 2L),
                new AlmanacMapRule(52L, 50L, 48L)
        ));

        assertEquals(81L, almanacMap.apply(79L));
        assertEquals(14L, almanacMap.apply(14L));
        assertEquals(57L, almanacMap.apply(55L));
        assertEquals(13L, almanacMap.apply(13L));
        assertEquals(51L, almanacMap.apply(99L));

    }




    @Test
    public void test_part_1() {
        assertEquals(35, Day5.day_5_part_1(SMALL_INPUT));
        assertEquals(1181555926, Day5.day_5_part_1(INPUT));

    }


    @Test
    public void test_part_2() {
        assertEquals(46, Day5.day_5_part_2(SMALL_INPUT));
        assertEquals(13, Day5Scanner.scan(SMALL_INPUT).findSeedNumberForLocation(35L));
        assertEquals(37806486, Day5.day_5_part_2(INPUT));
    }
}

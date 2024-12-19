package year_2023.day_08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {

    static final String SMALL_INPUT = "src/year_2023/day_08/day_8_2023_small_input.txt";
    static final String MEDIUM_INPUT = "src/year_2023/day_08/day_8_2023_medium_input.txt";
    static final String INPUT = "src/year_2023/day_08/day_8_2023_input.txt";
    static final String PART_2_SMALL_INPUT = "src/year_2023/day_08/day_8_2023_part_2_small_input.txt";

    @Test
    public void test_part_1() {
        assertEquals(2, Day8Scanner.scan(SMALL_INPUT).countStepsToZZZ());
        assertEquals(6, Day8Scanner.scan(MEDIUM_INPUT).countStepsToZZZ());
        assertEquals(19637, Day8Scanner.scan(INPUT).countStepsToZZZ());

    }


     @Test
    public void test_part_2() {
        assertEquals(6L, Day8Scanner.scan(PART_2_SMALL_INPUT).countStepsToZZZGhost());

        // TODO: More efficient solution?
        // assertEquals(8811050362409L, Day8Scanner.scan(INPUT).countStepsToZZZGhost2());

    }
}

package year_2023.day_08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {

    static final String SMALL_INPUT = "src/year_2023/day_08/day_8_2023_small_input.txt";
    static final String MEDIUM_INPUT = "src/year_2023/day_08/day_8_2023_medium_input.txt";
    static final String INPUT = "src/year_2023/day_08/day_8_2023_input.txt";

    @Test
    public void test_Scanner() {
        assertEquals(2, Day8Scanner.scan(SMALL_INPUT).countStepsToZZZ());
        assertEquals(6, Day8Scanner.scan(MEDIUM_INPUT).countStepsToZZZ());
        assertEquals(19637, Day8Scanner.scan(INPUT).countStepsToZZZ());

    }
}

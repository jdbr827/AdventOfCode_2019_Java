package year_2023.day_07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test {

    final String SMALL_INPUT = "src/year_2023/day_07/day_7_2023_small_input.txt";
    final String INPUT = "src/year_2023/day_07/day_7_2023_input.txt";


    @Test
    public void test_handType() {
        assertEquals(CamelCardsGameHandType.FIVE_OF_A_KIND, CamelCardsGame.getHandType("AAAAA"));
        assertEquals(CamelCardsGameHandType.FOUR_OF_A_KIND, CamelCardsGame.getHandType("AA8AA"));
    }


    @Test
    public void test_compare() {
        assertEquals(-1, new CamelCardsGame("33332", 0).compareTo(new CamelCardsGame("2AAAA", 0)));
    }


     @Test
    public void test_day_7_part_1() {
        assertEquals(6440, Day7.day_7_part_1(SMALL_INPUT));
        assertEquals(249390788, Day7.day_7_part_1(INPUT));
    }
}

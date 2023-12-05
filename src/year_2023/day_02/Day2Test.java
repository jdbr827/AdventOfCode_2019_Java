package year_2023.day_02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day2Test {
    final String SMALL_INPUT_FILENAME = "src/year_2023/day_02/day_2_2023_small_input.txt";
    final String INPUT_FILENAME = "src/year_2023/day_02/day_2_2023_input.txt";


    @Test
    public void test_part_1(){
        assertEquals(8, Day2.day_2_part_1_2023(SMALL_INPUT_FILENAME));
        assertEquals(2176, Day2.day_2_part_1_2023(INPUT_FILENAME));
    }


    @Test
    public void test_part_2(){
        assertEquals(2286, Day2.day_2_part_2_2023(SMALL_INPUT_FILENAME));
        assertEquals(63700, Day2.day_2_part_2_2023(INPUT_FILENAME));
    }

    @Test
    public void test_isPullPossible() {
        assertTrue(new Day2BagPull(0, 3, 4).isPossible());
        assertFalse(new Day2BagPull(8, 6, 20).isPossible());
    }


     @Test
    public void test_isGamePossible() {
        assertTrue(new Day2GameInfo(1, List.of(new Day2BagPull(0, 3, 4))).isGamePossible());
        assertFalse(new Day2GameInfo(1, List.of(new Day2BagPull(8, 6, 20))).isGamePossible());
        assertFalse(new Day2GameInfo(1, List.of(
                new Day2BagPull(0, 3, 4),
                new Day2BagPull(8, 6, 20)
        )).isGamePossible());
    }


    @Test
    public void test_minimumPossibleSetup() {
        assertEquals(
                new Day2BagPull(8, 6, 20),
                new Day2GameInfo(1, List.of(
                        new Day2BagPull(8, 3, 4),
                        new Day2BagPull(0, 6, 20)))
                        .minimumPossibleSetup()
        );
    }
}

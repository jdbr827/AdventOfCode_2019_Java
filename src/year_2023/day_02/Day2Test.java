package year_2023.day_02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day2Test {


    @Test
    public void test_parsing(){
        assertEquals(8, new Day2Scanner("src/year_2023/day_02/day_2_2023_small_input.txt").scan());
        assertEquals(2176, new Day2Scanner("src/year_2023/day_02/day_2_2023_input.txt").scan());
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
}

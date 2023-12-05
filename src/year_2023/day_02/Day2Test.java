package year_2023.day_02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day2Test {


    @Test
    public void test_isPullPossible() {
        assertTrue(Day2GameInfo.isPullPossible(0, 3, 4));
        assertFalse(Day2GameInfo.isPullPossible(8, 6, 20));
    }


     @Test
    public void test_isGamePossible() {
        assertTrue(Day2GameInfo.isGamePossible(List.of(new Day2BagPull(0, 3, 4))));
        assertFalse(Day2GameInfo.isGamePossible(List.of(new Day2BagPull(8, 6, 20))));
        assertFalse(Day2GameInfo.isGamePossible(List.of(
                new Day2BagPull(0, 3, 4),
                new Day2BagPull(8, 6, 20)
        )));
    }
}

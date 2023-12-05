package year_2023.day_01;

import org.junit.jupiter.api.Test;
import year_2020.Day1;

import static org.junit.jupiter.api.Assertions.*;

public class Day1Test {

    @Test
    public void test_char_is_int(){
        assertEquals(1, Day1Scanner.charIsNumber('1'));
        assertEquals(-1, Day1Scanner.charIsNumber('a'));
    }

    @Test
    public void test_part_1(){
        assertEquals(new Day1Scanner("src/year_2023/day_01/day_1_2023_small_input.txt").scan(), 142);
        assertEquals(new Day1Scanner("src/year_2023/day_01/day_1_2023_input.txt").scan(), 55607);
    }

    @Test
    public void test_char_starts_number() {
        assertEquals(2, Day1Scanner.charStartsNumber("two1nine", 0));
        assertEquals(-1, Day1Scanner.charStartsNumber("oone2nine", 0));
         assertEquals(1, Day1Scanner.charStartsNumber("oone2nine", 1));
    }

     @Test
    public void test_char_ends_number() {
        assertEquals(9, Day1Scanner.charEndsNumber("two1nine", 8));
        assertEquals(-1, Day1Scanner.charEndsNumber("oone2ninee", 10));
        assertEquals(9, Day1Scanner.charEndsNumber("oone2ninee", 9));
    }


    @Test
    public void test_get_calibration_value_part_2() {
        assertEquals(29, Day1Scanner.getCalibrationValue_part2("two1nine"));
    }

    @Test
    public void test_part_2(){
        assertEquals(new Day1Scanner("src/year_2023/day_01/day_1_2023_part_2_small_input.txt").scan(true), 281);
        assertEquals(new Day1Scanner("src/year_2023/day_01/day_1_2023_input.txt").scan(true), 55291);
    }
}

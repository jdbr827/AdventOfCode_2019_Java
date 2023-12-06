package year_2023.day_06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {


    @Test
    public void test_marginOfErrorToBeatRecord() {
        assertEquals(4, Day6.marginOfErrorToBeatRecord(7, 9));
        assertEquals(8, Day6.marginOfErrorToBeatRecord(15, 40));
        assertEquals(9, Day6.marginOfErrorToBeatRecord(30, 200));

        assertEquals(633080, Day6.marginOfErrorToBeatRecord(34, 204) * Day6.marginOfErrorToBeatRecord(90, 1713) * Day6.marginOfErrorToBeatRecord(89, 1210) * Day6.marginOfErrorToBeatRecord(86, 1780));
        System.out.println(Day6.marginOfErrorToBeatRecord(34908986, 204171312101780L));
    }
}

import org.junit.jupiter.api.Test;
import year_2019.IntCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntCodeTest {


    private boolean test_IntCode_one(int[] input, int[] expected_output) {
        return Arrays.equals(IntCode.createAndRun(input).getMemory(), expected_output);
    }

    @Test
    void test_IntCode_all() {
        int[] i1 = {1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        int[] o1 = {3500,9,10,70,2,3,11,0,99,30,40,50};
        assert test_IntCode_one(i1, o1);

        int[] i2 = {1,0,0,0,99};
        int[] o2 = {2,0,0,0,99};
        assert test_IntCode_one(i2, o2);

        int[] i3 = {2,3,0,3,99};
        int[] o3 = {2,3,0,6,99};
        assert test_IntCode_one(i3, o3);

        int[] i4 = {2,4,4,5,99,0};
        int[] o4 = {2,4,4,5,99,9801};
        assert test_IntCode_one(i4, o4);

        int[] i5 = {1,1,1,4,99,5,6,0,99};
        int[] o5 = {30,1,1,4,2,5,6,0,99};
        assert test_IntCode_one(i5, o5);

        (new IntCode(new int[] {3, 0, 4, 0, 99})).run(() -> 10); // should print 10

        int[] i6 = {1002,4,3,4,33};
        int[] o6 = {1002,4,3,4,99};
        assert test_IntCode_one(i6, o6);

        test_IntCode_JUMP();
        test_IntCode_LESS_THAN_EQUAL_TO();
    }

    @Test
    void test_IntCode_JUMP() {
        int[] m1 = {3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
        assert (new IntCode(m1)).run(() -> 0).equals(List.of(0)); // 0
        assert (new IntCode(m1)).run(() -> 7).equals(List.of(1)); // 1

        int[] m2 = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
        assert (new IntCode(m1)).run(() -> 0).equals(List.of(0)); // 0
        assert (new IntCode(m2)).run(() -> 7).equals(List.of(1)); // 1
    }

    @Test
    void test_IntCode_LESS_THAN_EQUAL_TO() {

        /* is equal to 8 simple */
        int[] m1 = {3,9,8,9,10,9,4,9,99,-1,8};
        assert (new IntCode(m1)).run(() -> 5).equals(List.of(0)); // should return 0
        assert (new IntCode(m1)).run(() -> 8).equals(List.of(1)); // should return 1

        /* <8 = 999; =8 = 1000; >8 = 1001 */
        int[] m2 = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};

        assert (new IntCode(m2)).run(() -> 5).equals(List.of(999)); // should print 999
        assert (new IntCode(m2)).run(() -> 8).equals(List.of(1000)); // should print 1000
        assert (new IntCode(m2)).run(() -> 10).equals(List.of(1001)); // should print 1001



    }

}

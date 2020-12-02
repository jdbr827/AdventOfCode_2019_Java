import org.junit.jupiter.api.Test;
import year_2019.IntCode;

import java.util.Arrays;

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
    }

}

package year_2019;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static year_2019.Day7.computeThrusterPower;
import static year_2019.Day7.optimizeThrusters;

class IntCodeTest {

    static final int[] DAY_7_PUZZLE_INPUT = {3,8,1001,8,10,8,105,1,0,0,21,42,51,76,101,118,199,280,361,442,99999,3,9,101,5,9,9,102,2,9,9,1001,9,4,9,102,2,9,9,4,9,99,3,9,1002,9,3,9,4,9,99,3,9,1002,9,4,9,1001,9,3,9,1002,9,5,9,101,3,9,9,1002,9,2,9,4,9,99,3,9,101,4,9,9,1002,9,2,9,1001,9,3,9,1002,9,3,9,101,4,9,9,4,9,99,3,9,101,3,9,9,1002,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99};


    private void test_IntCode_one(int[] input, int[] expected_output) {
        assertArrayEquals(IntCode.createAndRun(input).getMemory(), expected_output);
    }

    @Test
    void test_IntCode_Day2() {
        int[] i1 = {1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        int[] o1 = {3500,9,10,70,2,3,11,0,99,30,40,50};
        assertArrayEquals(IntCode.createAndRun(i1).getMemory(), o1);


        int[] i2 = {1,0,0,0,99};
        int[] o2 = {2,0,0,0,99};
        test_IntCode_one(i2, o2);

        int[] i3 = {2,3,0,3,99};
        int[] o3 = {2,3,0,6,99};
        test_IntCode_one(i3, o3);

        int[] i4 = {2,4,4,5,99,0};
        int[] o4 = {2,4,4,5,99,9801};
        test_IntCode_one(i4, o4);

        int[] i5 = {1,1,1,4,99,5,6,0,99};
        int[] o5 = {30,1,1,4,2,5,6,0,99};
        test_IntCode_one(i5, o5);

        (new IntCode(new int[] {3, 0, 4, 0, 99})).run(() -> 10); // should print 10

        int[] i6 = {1002,4,3,4,33};
        int[] o6 = {1002,4,3,4,99};
        test_IntCode_one(i6, o6);

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
    @Test
    public void testOptimizeThrusters1() {
        int[] mem = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
        List<Integer> phaseSettings = List.of(4, 3, 2, 1, 0);
        assertEquals(43210, computeThrusterPower(phaseSettings, mem));
        assertEquals(phaseSettings, optimizeThrusters(mem));
    }

    @Test
    public void testOptimizeThrusters2() {
        int[] mem = {3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0};
        List<Integer> phaseSettings = List.of(0, 1, 2, 3, 4);
        assertEquals(54321, computeThrusterPower(phaseSettings, mem));
        assertEquals(phaseSettings, optimizeThrusters(mem));
    }

    @Test
    public void testPart1(){
        List<Integer> phaseSettings = List.of(0, 3, 4, 2, 1);
        assertEquals(75228, computeThrusterPower(phaseSettings, DAY_7_PUZZLE_INPUT));
        assertEquals(phaseSettings, optimizeThrusters(DAY_7_PUZZLE_INPUT));
    }

}
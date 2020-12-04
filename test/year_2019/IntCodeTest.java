package year_2019;

import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;
import static year_2019.Day7.*;

class IntCodeTest {

    static final int[] DAY_2_PUZZLE_INPUT = {1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,6,23,2,13,23,27,1,27,13,31,1,9,31,35,1,35,9,39,1,39,5,43,2,6,43,47,1,47,6,51,2,51,9,55,2,55,13,59,1,59,6,63,1,10,63,67,2,67,9,71,2,6,71,75,1,75,5,79,2,79,10,83,1,5,83,87,2,9,87,91,1,5,91,95,2,13,95,99,1,99,10,103,1,103,2,107,1,107,6,0,99,2,14,0,0};
    static final int[] DAY_5_PUZZLE_INPUT = {3,225,1,225,6,6,1100,1,238,225,104,0,2,171,209,224,1001,224,-1040,224,4,224,102,8,223,223,1001,224,4,224,1,223,224,223,102,65,102,224,101,-3575,224,224,4,224,102,8,223,223,101,2,224,224,1,223,224,223,1102,9,82,224,1001,224,-738,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1101,52,13,224,1001,224,-65,224,4,224,1002,223,8,223,1001,224,6,224,1,223,224,223,1102,82,55,225,1001,213,67,224,1001,224,-126,224,4,224,102,8,223,223,1001,224,7,224,1,223,224,223,1,217,202,224,1001,224,-68,224,4,224,1002,223,8,223,1001,224,1,224,1,224,223,223,1002,176,17,224,101,-595,224,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,1102,20,92,225,1102,80,35,225,101,21,205,224,1001,224,-84,224,4,224,1002,223,8,223,1001,224,1,224,1,224,223,223,1101,91,45,225,1102,63,5,225,1101,52,58,225,1102,59,63,225,1101,23,14,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,677,224,1002,223,2,223,1006,224,329,101,1,223,223,1108,226,677,224,1002,223,2,223,1006,224,344,101,1,223,223,7,677,226,224,102,2,223,223,1006,224,359,1001,223,1,223,8,677,226,224,102,2,223,223,1005,224,374,1001,223,1,223,1107,677,226,224,102,2,223,223,1006,224,389,1001,223,1,223,1008,226,226,224,1002,223,2,223,1005,224,404,1001,223,1,223,7,226,677,224,102,2,223,223,1005,224,419,1001,223,1,223,1007,677,677,224,102,2,223,223,1006,224,434,1001,223,1,223,107,226,226,224,1002,223,2,223,1005,224,449,1001,223,1,223,1008,677,226,224,102,2,223,223,1006,224,464,1001,223,1,223,1007,677,226,224,1002,223,2,223,1005,224,479,1001,223,1,223,108,677,677,224,1002,223,2,223,1006,224,494,1001,223,1,223,108,226,226,224,1002,223,2,223,1006,224,509,101,1,223,223,8,226,677,224,102,2,223,223,1006,224,524,101,1,223,223,107,677,226,224,1002,223,2,223,1005,224,539,1001,223,1,223,8,226,226,224,102,2,223,223,1005,224,554,101,1,223,223,1108,677,226,224,102,2,223,223,1006,224,569,101,1,223,223,108,677,226,224,102,2,223,223,1006,224,584,1001,223,1,223,7,677,677,224,1002,223,2,223,1005,224,599,101,1,223,223,1007,226,226,224,102,2,223,223,1005,224,614,1001,223,1,223,1107,226,677,224,102,2,223,223,1006,224,629,101,1,223,223,1107,226,226,224,102,2,223,223,1005,224,644,1001,223,1,223,1108,677,677,224,1002,223,2,223,1005,224,659,101,1,223,223,107,677,677,224,1002,223,2,223,1006,224,674,1001,223,1,223,4,223,99,226};
    static final int[] DAY_7_PUZZLE_INPUT = {3,8,1001,8,10,8,105,1,0,0,21,42,51,76,101,118,199,280,361,442,99999,3,9,101,5,9,9,102,2,9,9,1001,9,4,9,102,2,9,9,4,9,99,3,9,1002,9,3,9,4,9,99,3,9,1002,9,4,9,1001,9,3,9,1002,9,5,9,101,3,9,9,1002,9,2,9,4,9,99,3,9,101,4,9,9,1002,9,2,9,1001,9,3,9,1002,9,3,9,101,4,9,9,4,9,99,3,9,101,3,9,9,1002,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99};

    @Test
    void test_IntCode_Day2() throws InterruptedException {
        int[] i1 = {1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        int[] o1 = {3500,9,10,70,2,3,11,0,99,30,40,50};
        assertArrayEquals(IntCode.createAndRun(i1).getMemory(), o1);


        int[] i2 = {1,0,0,0,99};
        int[] o2 = {2,0,0,0,99};
        assertArrayEquals(IntCode.createAndRun(i2).getMemory(), o2);

        int[] i3 = {2,3,0,3,99};
        int[] o3 = {2,3,0,6,99};
        assertArrayEquals(IntCode.createAndRun(i3).getMemory(), o3);

        int[] i4 = {2,4,4,5,99,0};
        int[] o4 = {2,4,4,5,99,9801};
        assertArrayEquals(IntCode.createAndRun(i4).getMemory(), o4);

        int[] i5 = {1,1,1,4,99,5,6,0,99};
        int[] o5 = {30,1,1,4,2,5,6,0,99};
        assertArrayEquals(IntCode.createAndRun(i5).getMemory(), o5);

        /* Day 2 Part 1 */
        int[] day_2_part_1 = DAY_2_PUZZLE_INPUT.clone();
        day_2_part_1[1] = 12;
        day_2_part_1[2] = 2;
        assertEquals(IntCode.createAndRun(day_2_part_1).getMemory()[0], 2842648);
    }

    @Test
    void test_IntCode_Day5() throws InterruptedException {
        BlockingQueue<Integer> output = new LinkedBlockingQueue<>();

        // Basic Input/Output
        int[] m1 = new int[] {3, 0, 4, 0, 99};
        IntCode.createAndRun(m1, () -> 10, output);
        assertEquals(output.take(), 10);

        // Parameter Modes
        int[] i6 = {1002,4,3,4,33};
        int[] o6 = {1002,4,3,4,99};
        assertArrayEquals(IntCode.createAndRun(i6).getMemory(), o6);

        // Part 1
        IntCode.createAndRun(DAY_5_PUZZLE_INPUT, () -> 1, output);
        // Program outputs a bunch of 0s then "diagnostic code"
        int thisOutput;
        for (thisOutput = output.take(); !output.isEmpty(); thisOutput = output.take()) {
            assertEquals(thisOutput, 0);
        }
        assertEquals(thisOutput, 9006673);

        // Position Mode Equality Check (=8)
        int[] m2 = {3,9,8,9,10,9,4,9,99,-1,8};
        IntCode.createAndRun(m2, () -> 8, output);
        assertEquals(output.take(), 1);
        IntCode.createAndRun(m2, () -> 9, output);
        assertEquals(output.take(), 0);

        // Position Mode Less Than Check (< 8)
        int[] m3 = {3,9,7,9,10,9,4,9,99,-1,8};
        IntCode.createAndRun(m3, () -> 8, output);
        assertEquals(output.take(), 0);
        IntCode.createAndRun(m3, () -> 7, output);
        assertEquals(output.take(), 1);

        // Immediate Mode Equality Check (=8)
        int[] m4 = {3,3,1108,-1,8,3,4,3,99};
        IntCode.createAndRun(m4, () -> 8, output);
        assertEquals(output.take(), 1);
        IntCode.createAndRun(m4, () -> 9, output);
        assertEquals(output.take(), 0);

        // Immediate Mode Less Than Check (< 8)
        int[] m5 = {3,3,1107,-1,8,3,4,3,99};
        IntCode.createAndRun(m5, () -> 8, output);
        assertEquals(output.take(), 0);
        IntCode.createAndRun(m5, () -> 7, output);
        assertEquals(output.take(), 1);

        // Position Mode is non-zero
        int[] m6 = {3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
        IntCode.createAndRun(m6, () -> 2, output);
        assertEquals(output.take(), 1);
        IntCode.createAndRun(m6, () -> 0, output);
        assertEquals(output.take(), 0);

        // Immediate Mode is non-zero
        int[] m7 = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
        IntCode.createAndRun(m7, () -> 2, output);
        assertEquals(output.take(), 1);
        IntCode.createAndRun(m7, () -> 0, output);
        assertEquals(output.take(), 0);

        // Returns 999 if input < 8; 1000 if input == 9; 1001 if input > 8
        int[] m8 = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        IntCode.createAndRun(m8, () -> 7, output);
        assertEquals(output.take(), 999);
        IntCode.createAndRun(m8, () -> 8, output);
        assertEquals(output.take(), 1000);
        IntCode.createAndRun(m8, () -> 9, output);
        assertEquals(output.take(), 1001);

        // Part 2
        IntCode.createAndRun(DAY_5_PUZZLE_INPUT, () -> 5, output);
        // Program outputs a bunch of 0s then "diagnostic code"
        for (thisOutput = output.take(); !output.isEmpty(); thisOutput = output.take()) {
            assertEquals(thisOutput, 0);
        }
        assertEquals(thisOutput,3629692 );
    }

    @Test
    public void testOptimizeThrusters1() throws InterruptedException {
        int[] mem1 = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
        List<Integer> phaseSettings = List.of(4, 3, 2, 1, 0);
        assertEquals(43210, computeThrusterPower(phaseSettings, mem1));
        assertEquals(phaseSettings, optimizeThrusters1(mem1));

        int[] mem2 = {3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0};
        phaseSettings = List.of(0, 1, 2, 3, 4);
        assertEquals(54321, computeThrusterPower(phaseSettings, mem2));
        assertEquals(phaseSettings, optimizeThrusters1(mem2));

        // Day 7 Part 1
        phaseSettings = List.of(0, 3, 4, 2, 1);
        assertEquals(75228, computeThrusterPower(phaseSettings, DAY_7_PUZZLE_INPUT));
        assertEquals(phaseSettings, optimizeThrusters1(DAY_7_PUZZLE_INPUT));
    }

    @Test
    public void testOptimizeThrusters2() throws InterruptedException {
        int[] mem1 = {3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5};
        List<Integer> phaseSettings = List.of(9,8,7,6,5);
        assertEquals(139629729, feedbackLoopThrusterPower(phaseSettings, mem1));
        assertEquals(phaseSettings, optimizeThrusters2(mem1));

        int[] mem2 = {3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10};
        phaseSettings = List.of(9, 7, 8, 5, 6);
        assertEquals(18216, feedbackLoopThrusterPower(phaseSettings, mem2));
        assertEquals(phaseSettings, optimizeThrusters2(mem2));

        // Day 7 Part 2
        phaseSettings = List.of(6,7,9,5,8);
        assertEquals(79846026, feedbackLoopThrusterPower(phaseSettings, DAY_7_PUZZLE_INPUT));
        assertEquals(phaseSettings, optimizeThrusters2(DAY_7_PUZZLE_INPUT));



    }

}
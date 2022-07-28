package year_2019;

import com.google.common.primitives.Longs;
import org.junit.jupiter.api.Test;
import year_2019.IntCodeComputer.IntCode;
import year_2019.IntCodeComputer.IntCodeAPI;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.*;
import static year_2019.Day7.*;

class IntCodeTest {

    static final long[] DAY_2_PUZZLE_INPUT = {1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,6,23,2,13,23,27,1,27,13,31,1,9,31,35,1,35,9,39,1,39,5,43,2,6,43,47,1,47,6,51,2,51,9,55,2,55,13,59,1,59,6,63,1,10,63,67,2,67,9,71,2,6,71,75,1,75,5,79,2,79,10,83,1,5,83,87,2,9,87,91,1,5,91,95,2,13,95,99,1,99,10,103,1,103,2,107,1,107,6,0,99,2,14,0,0};
    static final long[] DAY_5_PUZZLE_INPUT = {3,225,1,225,6,6,1100,1,238,225,104,0,2,171,209,224,1001,224,-1040,224,4,224,102,8,223,223,1001,224,4,224,1,223,224,223,102,65,102,224,101,-3575,224,224,4,224,102,8,223,223,101,2,224,224,1,223,224,223,1102,9,82,224,1001,224,-738,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1101,52,13,224,1001,224,-65,224,4,224,1002,223,8,223,1001,224,6,224,1,223,224,223,1102,82,55,225,1001,213,67,224,1001,224,-126,224,4,224,102,8,223,223,1001,224,7,224,1,223,224,223,1,217,202,224,1001,224,-68,224,4,224,1002,223,8,223,1001,224,1,224,1,224,223,223,1002,176,17,224,101,-595,224,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,1102,20,92,225,1102,80,35,225,101,21,205,224,1001,224,-84,224,4,224,1002,223,8,223,1001,224,1,224,1,224,223,223,1101,91,45,225,1102,63,5,225,1101,52,58,225,1102,59,63,225,1101,23,14,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,677,224,1002,223,2,223,1006,224,329,101,1,223,223,1108,226,677,224,1002,223,2,223,1006,224,344,101,1,223,223,7,677,226,224,102,2,223,223,1006,224,359,1001,223,1,223,8,677,226,224,102,2,223,223,1005,224,374,1001,223,1,223,1107,677,226,224,102,2,223,223,1006,224,389,1001,223,1,223,1008,226,226,224,1002,223,2,223,1005,224,404,1001,223,1,223,7,226,677,224,102,2,223,223,1005,224,419,1001,223,1,223,1007,677,677,224,102,2,223,223,1006,224,434,1001,223,1,223,107,226,226,224,1002,223,2,223,1005,224,449,1001,223,1,223,1008,677,226,224,102,2,223,223,1006,224,464,1001,223,1,223,1007,677,226,224,1002,223,2,223,1005,224,479,1001,223,1,223,108,677,677,224,1002,223,2,223,1006,224,494,1001,223,1,223,108,226,226,224,1002,223,2,223,1006,224,509,101,1,223,223,8,226,677,224,102,2,223,223,1006,224,524,101,1,223,223,107,677,226,224,1002,223,2,223,1005,224,539,1001,223,1,223,8,226,226,224,102,2,223,223,1005,224,554,101,1,223,223,1108,677,226,224,102,2,223,223,1006,224,569,101,1,223,223,108,677,226,224,102,2,223,223,1006,224,584,1001,223,1,223,7,677,677,224,1002,223,2,223,1005,224,599,101,1,223,223,1007,226,226,224,102,2,223,223,1005,224,614,1001,223,1,223,1107,226,677,224,102,2,223,223,1006,224,629,101,1,223,223,1107,226,226,224,102,2,223,223,1005,224,644,1001,223,1,223,1108,677,677,224,1002,223,2,223,1005,224,659,101,1,223,223,107,677,677,224,1002,223,2,223,1006,224,674,1001,223,1,223,4,223,99,226};
    static final long[] DAY_7_PUZZLE_INPUT = {3,8,1001,8,10,8,105,1,0,0,21,42,51,76,101,118,199,280,361,442,99999,3,9,101,5,9,9,102,2,9,9,1001,9,4,9,102,2,9,9,4,9,99,3,9,1002,9,3,9,4,9,99,3,9,1002,9,4,9,1001,9,3,9,1002,9,5,9,101,3,9,9,1002,9,2,9,4,9,99,3,9,101,4,9,9,1002,9,2,9,1001,9,3,9,1002,9,3,9,101,4,9,9,4,9,99,3,9,101,3,9,9,1002,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99};
    static final long[] DAY_9_PUZZLE_INPUT = {1102,34463338,34463338,63,1007,63,34463338,63,1005,63,53,1101,3,0,1000,109,988,209,12,9,1000,209,6,209,3,203,0,1008,1000,1,63,1005,63,65,1008,1000,2,63,1005,63,904,1008,1000,0,63,1005,63,58,4,25,104,0,99,4,0,104,0,99,4,17,104,0,99,0,0,1101,0,708,1029,1101,1,0,1021,1102,38,1,1015,1101,25,0,1004,1101,21,0,1018,1102,1,34,1016,1101,0,713,1028,1101,735,0,1024,1101,31,0,1003,1102,1,24,1010,1101,20,0,1011,1101,0,27,1005,1102,726,1,1025,1101,426,0,1027,1101,0,777,1022,1102,1,32,1001,1101,37,0,1009,1101,429,0,1026,1102,1,36,1019,1101,0,0,1020,1101,0,30,1012,1101,0,770,1023,1101,0,35,1014,1101,0,33,1007,1102,23,1,1002,1101,0,28,1017,1102,1,22,1013,1102,39,1,1006,1101,0,26,1000,1101,29,0,1008,109,6,2102,1,-1,63,1008,63,27,63,1005,63,203,4,187,1106,0,207,1001,64,1,64,1002,64,2,64,109,-15,2108,26,9,63,1005,63,225,4,213,1106,0,229,1001,64,1,64,1002,64,2,64,109,9,21101,40,0,10,1008,1010,40,63,1005,63,251,4,235,1106,0,255,1001,64,1,64,1002,64,2,64,109,11,21108,41,40,0,1005,1011,271,1106,0,277,4,261,1001,64,1,64,1002,64,2,64,109,-7,1207,3,32,63,1005,63,297,1001,64,1,64,1105,1,299,4,283,1002,64,2,64,109,3,1201,-1,0,63,1008,63,42,63,1005,63,323,1001,64,1,64,1105,1,325,4,305,1002,64,2,64,109,2,2102,1,-7,63,1008,63,24,63,1005,63,345,1106,0,351,4,331,1001,64,1,64,1002,64,2,64,109,-6,21107,42,43,8,1005,1011,369,4,357,1106,0,373,1001,64,1,64,1002,64,2,64,109,-7,2108,30,7,63,1005,63,393,1001,64,1,64,1106,0,395,4,379,1002,64,2,64,109,18,21108,43,43,-3,1005,1011,413,4,401,1106,0,417,1001,64,1,64,1002,64,2,64,109,17,2106,0,-4,1105,1,435,4,423,1001,64,1,64,1002,64,2,64,109,-29,2107,26,2,63,1005,63,451,1105,1,457,4,441,1001,64,1,64,1002,64,2,64,109,20,1206,-2,471,4,463,1105,1,475,1001,64,1,64,1002,64,2,64,109,-9,1205,8,489,4,481,1105,1,493,1001,64,1,64,1002,64,2,64,109,-12,1202,-1,1,63,1008,63,26,63,1005,63,515,4,499,1105,1,519,1001,64,1,64,1002,64,2,64,109,25,1205,-6,531,1106,0,537,4,525,1001,64,1,64,1002,64,2,64,109,-31,1208,8,31,63,1005,63,555,4,543,1106,0,559,1001,64,1,64,1002,64,2,64,109,13,1207,1,38,63,1005,63,577,4,565,1106,0,581,1001,64,1,64,1002,64,2,64,109,4,21101,44,0,1,1008,1013,47,63,1005,63,605,1001,64,1,64,1106,0,607,4,587,1002,64,2,64,109,-6,2107,38,0,63,1005,63,629,4,613,1001,64,1,64,1106,0,629,1002,64,2,64,109,13,21102,45,1,-7,1008,1012,45,63,1005,63,655,4,635,1001,64,1,64,1105,1,655,1002,64,2,64,109,9,1206,-7,667,1106,0,673,4,661,1001,64,1,64,1002,64,2,64,109,-27,2101,0,7,63,1008,63,29,63,1005,63,699,4,679,1001,64,1,64,1106,0,699,1002,64,2,64,109,17,2106,0,10,4,705,1106,0,717,1001,64,1,64,1002,64,2,64,109,14,2105,1,-8,4,723,1001,64,1,64,1106,0,735,1002,64,2,64,109,-21,1202,-8,1,63,1008,63,34,63,1005,63,755,1105,1,761,4,741,1001,64,1,64,1002,64,2,64,109,18,2105,1,-6,1001,64,1,64,1106,0,779,4,767,1002,64,2,64,109,-15,1201,-6,0,63,1008,63,29,63,1005,63,801,4,785,1105,1,805,1001,64,1,64,1002,64,2,64,109,-14,1208,0,24,63,1005,63,825,1001,64,1,64,1106,0,827,4,811,1002,64,2,64,109,15,21102,46,1,-2,1008,1013,49,63,1005,63,847,1106,0,853,4,833,1001,64,1,64,1002,64,2,64,109,-17,2101,0,2,63,1008,63,23,63,1005,63,873,1106,0,879,4,859,1001,64,1,64,1002,64,2,64,109,16,21107,47,46,2,1005,1016,899,1001,64,1,64,1105,1,901,4,885,4,64,99,21101,0,27,1,21101,0,915,0,1106,0,922,21201,1,55486,1,204,1,99,109,3,1207,-2,3,63,1005,63,964,21201,-2,-1,1,21102,942,1,0,1105,1,922,22102,1,1,-1,21201,-2,-3,1,21101,0,957,0,1105,1,922,22201,1,-1,-2,1105,1,968,22101,0,-2,-2,109,-3,2106,0,0};

    @Test
    void test_IntCode_Day2() throws InterruptedException {
        long[] i1 = {1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        long[] o1 = {3500,9,10,70,2,3,11,0,99,30,40,50};
        assertArrayEquals(IntCode.createAndRun(i1).getMemory(), o1);


        long[] i2 = {1,0,0,0,99};
        long[] o2 = {2,0,0,0,99};
        assertArrayEquals(IntCode.createAndRun(i2).getMemory(), o2);

        long[] i3 = {2,3,0,3,99};
        long[] o3 = {2,3,0,6,99};
        assertArrayEquals(IntCode.createAndRun(i3).getMemory(), o3);

        long[] i4 = {2,4,4,5,99,0};
        long[] o4 = {2,4,4,5,99,9801};
        assertArrayEquals(IntCode.createAndRun(i4).getMemory(), o4);

        long[] i5 = {1,1,1,4,99,5,6,0,99};
        long[] o5 = {30,1,1,4,2,5,6,0,99};
        assertArrayEquals(IntCode.createAndRun(i5).getMemory(), o5);

        /* Day 2 Part 1 */
        long[] day_2_part_1 = DAY_2_PUZZLE_INPUT.clone();
        day_2_part_1[1] = 12;
        day_2_part_1[2] = 2;
        assertEquals(IntCode.createAndRun(day_2_part_1).getMemory()[0], 2842648);
    }

    @Test
    void test_IntCode_Day5() throws InterruptedException {
        BlockingQueue<Long> output = new LinkedBlockingQueue<>();

        // Basic Input/Output
        long[] m1 = new long[] {3, 0, 4, 0, 99};
        IntCode.createAndRun(m1, () -> 10L, output);
        assertEquals(output.take(), 10);

        // Parameter Modes
        long[] i6 = {1002,4,3,4,33};
        long[] o6 = {1002,4,3,4,99};
        assertArrayEquals(IntCode.createAndRun(i6).getMemory(), o6);

        // Part 1
        IntCode.createAndRun(DAY_5_PUZZLE_INPUT, () -> 1L, output);
        // Program outputs a bunch of 0s then "diagnostic code"
        long thisOutput;
        for (thisOutput = output.take(); !output.isEmpty(); thisOutput = output.take()) {
            assertEquals(thisOutput, 0);
        }
        assertEquals(thisOutput, 9006673);

        // Position Mode Equality Check (=8)
        long[] m2 = {3,9,8,9,10,9,4,9,99,-1,8};
        IntCodeAPI intCodeAPI = new IntCodeAPI(m2);
        intCodeAPI.startProgram();
        intCodeAPI.sendInput(8L);
        assertEquals(intCodeAPI.waitForOutputKnown(), 1);
        IntCode.createAndRun(m2, () -> 9L, output);
        assertEquals(output.take(), 0);

        // Position Mode Less Than Check (< 8)
        long[] m3 = {3,9,7,9,10,9,4,9,99,-1,8};
        IntCode.createAndRun(m3, () -> 8L, output);
        assertEquals(output.take(), 0);
        IntCode.createAndRun(m3, () -> 7L, output);
        assertEquals(output.take(), 1);

        // Immediate Mode Equality Check (=8)
        long[] m4 = {3,3,1108,-1,8,3,4,3,99};
        IntCode.createAndRun(m4, () -> 8L, output);
        assertEquals(output.take(), 1);
        IntCode.createAndRun(m4, () -> 9L, output);
        assertEquals(output.take(), 0);

        // Immediate Mode Less Than Check (< 8)
        long[] m5 = {3,3,1107,-1,8,3,4,3,99};
        IntCode.createAndRun(m5, () -> 8L, output);
        assertEquals(output.take(), 0);
        IntCode.createAndRun(m5, () -> 7L, output);
        assertEquals(output.take(), 1);

        // Position Mode is non-zero
        long[] m6 = {3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
        IntCode.createAndRun(m6, () -> 2L, output);
        assertEquals(output.take(), 1);
        IntCode.createAndRun(m6, () -> 0L, output);
        assertEquals(output.take(), 0);

        // Immediate Mode is non-zero
        long[] m7 = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
        IntCode.createAndRun(m7, () -> 2L, output);
        assertEquals(output.take(), 1);
        IntCode.createAndRun(m7, () -> 0L, output);
        assertEquals(output.take(), 0);

        // Returns 999 if input < 8; 1000 if input == 9; 1001 if input > 8
        long[] m8 = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        IntCode.createAndRun(m8, () -> 7L, output);
        assertEquals(output.take(), 999);
        IntCode.createAndRun(m8, () -> 8L, output);
        assertEquals(output.take(), 1000);
        IntCode.createAndRun(m8, () -> 9L, output);
        assertEquals(output.take(), 1001);

        // Part 2
        IntCode.createAndRun(DAY_5_PUZZLE_INPUT, () -> 5L, output);
        // Program outputs a bunch of 0s then "diagnostic code"
        for (thisOutput = output.take(); !output.isEmpty(); thisOutput = output.take()) {
            assertEquals(thisOutput, 0);
        }
        assertEquals(thisOutput,3629692 );
    }

    @Test
    public void testOptimizeThrusters1() throws InterruptedException {
        long[] mem1 = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
        List<Long> phaseSettings = List.of(4L, 3L, 2L, 1L, 0L);
        assertEquals(43210, computeThrusterPower(phaseSettings, mem1));
        assertEquals(phaseSettings, optimizeThrusters1(mem1));

        long[] mem2 = {3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0};
        phaseSettings = List.of(0L, 1L, 2L, 3L, 4L);
        assertEquals(54321, computeThrusterPower(phaseSettings, mem2));
        assertEquals(phaseSettings, optimizeThrusters1(mem2));

        // Day 7 Part 1
        phaseSettings = List.of(0L, 3L, 4L, 2L, 1L);
        assertEquals(75228, computeThrusterPower(phaseSettings, DAY_7_PUZZLE_INPUT));
        assertEquals(phaseSettings, optimizeThrusters1(DAY_7_PUZZLE_INPUT));
    }

    @Test
    public void testOptimizeThrusters2() throws InterruptedException {
        long[] mem1 = {3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5};
        List<Long> phaseSettings = List.of(9L,8L,7L,6L,5L);
        assertEquals(139629729, feedbackLoopThrusterPower(phaseSettings, mem1));
        assertEquals(phaseSettings, optimizeThrusters2(mem1));

        long[] mem2 = {3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10};
        phaseSettings = List.of(9L, 7L, 8L, 5L, 6L);
        assertEquals(18216, feedbackLoopThrusterPower(phaseSettings, mem2));
        assertEquals(phaseSettings, optimizeThrusters2(mem2));

        // Day 7 Part 2
        phaseSettings = List.of(6L,7L,9L,5L,8L);
        assertEquals(79846026, feedbackLoopThrusterPower(phaseSettings, DAY_7_PUZZLE_INPUT));
        assertEquals(phaseSettings, optimizeThrusters2(DAY_7_PUZZLE_INPUT));
    }

    @Test
    void test_IntCode_Day9() throws InterruptedException {

        long[] mem1 = {109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99};
        BlockingQueue<Long> output = new LinkedBlockingQueue<>();
        IntCode.createAndRun(mem1, (BlockingQueue<Long>) null, output);
        List<Long> outputFull = new ArrayList<>();
        System.out.println(output);
        output.drainTo(outputFull);
        System.out.println(outputFull.size());
        assertArrayEquals(mem1, Longs.toArray(outputFull));

        long[] mem2 = {1102,34915192,34915192,7,4,7,99,0};
        output = new LinkedBlockingQueue<>();
        IntCode.createAndRun(mem2, (BlockingQueue<Long>) null, output);
        outputFull = new ArrayList<>();
        output.drainTo(outputFull);
        System.out.println(outputFull);
        assertTrue(outputFull.get(0) > pow(10, 15));

        long[] mem3 = {104,1125899906842624L ,99};
        output = new LinkedBlockingQueue<>();
        IntCode.createAndRun(mem3, (BlockingQueue<Long>) null, output);
        outputFull = new ArrayList<>();
        output.drainTo(outputFull);
        System.out.println(outputFull);
        assertEquals((long) outputFull.get(0), 1125899906842624L);

        // Day 9 Part 1
        output = new LinkedBlockingQueue<>();
        IntCode.createAndRun(DAY_9_PUZZLE_INPUT, () -> 1L, output);
        outputFull = new ArrayList<>();
        output.drainTo(outputFull);
        assertEquals((long) outputFull.get(0), 3989758265L);

        // Day 9 Part 2
        output = new LinkedBlockingQueue<>();
        IntCode.createAndRun(DAY_9_PUZZLE_INPUT, () -> 2L, output);
        outputFull = new ArrayList<>();
        output.drainTo(outputFull);
        assertEquals((long) outputFull.get(0), 76791L);




    }

}
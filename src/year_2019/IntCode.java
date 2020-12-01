package year_2019;

import java.util.Arrays;

public class IntCode {

    public class Instruction {
        int opcode;
        Integer[] params;

        public Instruction (int opcode, Integer... params) {
            this.opcode = opcode;
            this.params = params;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntCode intCode = (IntCode) o;
        return Arrays.equals(getMemory(), intCode.getMemory());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getMemory());
    }

    int[] memory;
    int instructionPointer = 0;

    public IntCode(int[] memory) {
        this.memory = memory.clone();
    }

    @Override
    public String toString() {
        return "IntCode{" +
                "memory=" + Arrays.toString(memory) +
                '}';
    }

    public int[] getMemory() {
        return memory;
    }



    public static IntCode createAndRun(int[] startingMemory) {
        IntCode program = new IntCode(startingMemory);
        program.run();
        return program;
    }

    /**
     * Runs the Intcode program
     */
    public void run() {
        instructionPointer = 0;
        int opcode;
        while ((opcode = memory[instructionPointer]) != 99) {
            switch (opcode) {
                case 1:
                    int addend1 = memory[memory[instructionPointer + 1]];
                    int addend2 = memory[memory[instructionPointer + 2]];
                    memory[memory[instructionPointer + 3]] = addend1 + addend2;
                    instructionPointer += 4;
                    break;
                case 2:
                    int mult1 = memory[memory[instructionPointer + 1]];
                    int mult2 = memory[memory[instructionPointer + 2]];
                    memory[memory[instructionPointer + 3]] = mult1 * mult2;
                    instructionPointer += 4;
                    break;
                default:
                    throw new Error("Unexpected Opcode");
            }
        }
    }

    public static boolean test_IntCode_one(int[] input, int[] expected_output) {
        return IntCode.createAndRun(input).getMemory() == expected_output;
    }

    public static void test_IntCode_all() {
        int[] i1 = {1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        int[] o1 = {3500,9,10,70,2,3,11,0,99,30,40,50};
        System.out.println(test_IntCode_one(i1, o1));

        int[] i2 = {1,0,0,0,99};
        int[] o2 = {2,0,0,0,99};
        System.out.println(test_IntCode_one(i2, o2));

        int[] i3 = {2,3,0,3,99};
        int[] o3 = {2,3,0,6,99};
        System.out.println(test_IntCode_one(i3, o3));

        int[] i4 = {2,4,4,5,99,0};
        int[] o4 = {2,4,4,5,99,9801};
        System.out.println(test_IntCode_one(i4, o4));

        int[] i5 = {1,1,1,4,99,5,6,0,99};
        int[] o5 = {30,1,1,4,2,5,6,0,99};
        System.out.println(test_IntCode_one(i5, o5));
    }
}

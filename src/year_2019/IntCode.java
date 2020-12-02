package year_2019;

import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;


public class IntCode {

    public static final int ADD_INSTRUCTION_CODE = 1;
    public static final int MULTIPLY_INSTRUCTION_CODE = 2;
    public static final int INPUT_INSTRUCTION_CODE = 3;
    public static final int OUTPUT_INSTRUCTION_CODE = 4;
    public static final int HALT_INSTRUCTION_CODE = 99;

//    private interface IntCodeInstruction {
//
//        IntCodeInstruction make(int opcode);
//
//        int[] execute(int[] memory);
//
//
//        public class AddIntCodeInstruction implements IntCodeInstruction {
//
//            public AddIntCodeInstruction(int opcode, Integer... params) {
//
//            }
//
//            @Override
//            public IntCodeInstruction make(int opcode, Integer... params) {
//                return null;
//            }
//
//            @Override
//            public int[] execute(int[] memory) {
//                return new int[0];
//            }
//        }
//    }


    int[] memory;
    int instructionPointer = 0;

    public IntCode(int[] memory) {
        this.memory = memory.clone();
    }

    public int[] getMemory() {
        return memory;
    }



    public static IntCode createAndRun(int[] startingMemory) {
        IntCode program = new IntCode(startingMemory);
        program.run();
        return program;
    }

    public void run() {
        run(null);
    }

    /**
     * Runs the Intcode program MUTATES MEMORY
     */
    public void run(int[] input) {
        int inputPointer = 0;
        instructionPointer = 0;
        int opcode;
        while ((opcode = memory[instructionPointer]) != HALT_INSTRUCTION_CODE) {
            switch (opcode) {
                case ADD_INSTRUCTION_CODE:
                    int addend1 = memory[memory[instructionPointer + 1]];
                    int addend2 = memory[memory[instructionPointer + 2]];
                    memory[memory[instructionPointer + 3]] = addend1 + addend2;
                    instructionPointer += 4;
                    break;
                case MULTIPLY_INSTRUCTION_CODE:
                    int mult1 = memory[memory[instructionPointer + 1]];
                    int mult2 = memory[memory[instructionPointer + 2]];
                    memory[memory[instructionPointer + 3]] = mult1 * mult2;
                    instructionPointer += 4;
                    break;
                case INPUT_INSTRUCTION_CODE:
                    int writeAddr = memory[instructionPointer + 1];
                    memory[writeAddr] = input[inputPointer]; // or some form of a getInput
                    instructionPointer += 2;
                    break;
                case OUTPUT_INSTRUCTION_CODE:
                    int readAddr = memory[instructionPointer + 1];
                    System.out.println(memory[readAddr]); // Or some other form of output
                    instructionPointer += 2;
                    break;
                default:
                    throw new Error("Unexpected Opcode: " + opcode);
            }
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

    @Override
    public String toString() {
        return "IntCode{" +
                "memory=" + Arrays.toString(memory) +
                '}';
    }
}

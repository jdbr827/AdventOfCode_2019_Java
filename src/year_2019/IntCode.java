package year_2019;

import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.function.Supplier;


public class IntCode {

    public static final int ADD_INSTRUCTION_CODE = 1;
    public static final int MULTIPLY_INSTRUCTION_CODE = 2;
    public static final int INPUT_INSTRUCTION_CODE = 3;
    public static final int OUTPUT_INSTRUCTION_CODE = 4;
    public static final int HALT_INSTRUCTION_CODE = 99;


    public int[] memory;
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

    public static final int PARAMETER_MODE = 0;
    public static final int IMMEDIATE_MODE = 1;

    private int memRead(int addr, int mode) {
        return (mode == PARAMETER_MODE)
                ? memory[memory[addr]]
                : memory[addr];
    }

    public void run() {
        run(null);
    }
     /**
     * Runs the Intcode program MUTATES MEMORY
     */
    public void run(Supplier<Integer> input) {
        int inputPointer = 0;
        instructionPointer = 0;
        int opcode;
        while ((opcode = memory[instructionPointer++]) != HALT_INSTRUCTION_CODE) {
            int instructionCode = opcode % 100;
            switch (instructionCode) {
                case ADD_INSTRUCTION_CODE:
                    int addend1 = memRead(instructionPointer++, (opcode/100) % 10);
                    int addend2 = memRead(instructionPointer++, (opcode/1000) % 10);
                    memory[memory[instructionPointer++]] = addend1 + addend2;
                    break;
                case MULTIPLY_INSTRUCTION_CODE:
                    int mult1 = memRead(instructionPointer++, (opcode/100) % 10);
                    int mult2 = memRead(instructionPointer++, (opcode/1000) % 10);
                    memory[memory[instructionPointer++]] = mult1 * mult2;
                    break;
                case INPUT_INSTRUCTION_CODE:
                    int writeAddr = memory[instructionPointer++];
                    memory[writeAddr] = input.get(); // or some form of a getInput
                    break;
                case OUTPUT_INSTRUCTION_CODE:
                    System.out.println(memRead(instructionPointer++, (opcode/100) % 10)); // Or some other form of output
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

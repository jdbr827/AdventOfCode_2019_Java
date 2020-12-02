package year_2019;

import java.util.Arrays;
import java.util.function.Supplier;



public class IntCode {

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

    public static final int POSITION_MODE = 0;
    public static final int IMMEDIATE_MODE = 1;

    /**
     * Returns the parameter mode of the paramNum^th parameter given the opcode
     * @param opcode the opcode for the instruction
     * @param paramNum denotes which parameter we are looking at [1-indexed]
     * @return The parameter mode for the operation
     */
    private static int getParameterMode(int opcode, int paramNum) {
        return ((opcode / (int) Math.pow(10, paramNum+1)) % 10);
    }

    /**
     * Performs a read into memory based on the pararmeter mode and desired address
     * @param addr the address of the read
     * @param mode the parameter mode of the read
     * @return the output of the read
     */
    private int memRead(int addr, int mode) {
        return (mode == POSITION_MODE)
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
        instructionPointer = 0;
        int opcode;
        while ((opcode = memory[instructionPointer++]) != 99) {
            InstructionCode instructionCode = InstructionCode.valueOf(opcode % 100);
            switch (instructionCode) {
                case ADD_INSTRUCTION_CODE:
                    int addend1 = memRead(instructionPointer++, getParameterMode(opcode, 1));
                    int addend2 = memRead(instructionPointer++, getParameterMode(opcode, 2));
                    memory[memory[instructionPointer++]] = addend1 + addend2;
                    break;
                case MULTIPLY_INSTRUCTION_CODE:
                    int mult1 = memRead(instructionPointer++, getParameterMode(opcode, 1));
                    int mult2 = memRead(instructionPointer++, getParameterMode(opcode, 2));
                    memory[memory[instructionPointer++]] = mult1 * mult2;
                    break;
                case INPUT_INSTRUCTION_CODE:
                    int writeAddr = memory[instructionPointer++];
                    memory[writeAddr] = input.get();
                    break;
                case OUTPUT_INSTRUCTION_CODE:
                    System.out.println(memRead(instructionPointer++, getParameterMode(opcode, 1))); // Or some other form of output
                    break;
                case HALT_INSTRUCTION_CODE:
                    return;
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

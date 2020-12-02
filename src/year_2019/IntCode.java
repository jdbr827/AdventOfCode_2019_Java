package year_2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    enum ParameterMode {
        POSITION_MODE(0),
        IMMEDIATE_MODE(1);

        private final static Map<Integer, ParameterMode> map = new HashMap<>();
        private final int pCode;

        ParameterMode(int pCode) {
            this.pCode = pCode;
        }

         static {
            for (ParameterMode mode : ParameterMode.values()) {
                map.put(mode.pCode, mode);
            }
        }

        public static ParameterMode valueOf(int pCode) {
            return map.get(pCode);
        }
    }

    /**
     * Returns the ith parameter of the instruction
     * @param paramNum 1-indexed parameter of the current instruction
     * @return the value of the parameter (accounting for ParameterMode)
     */
    int readParameter(int paramNum) {
        return memRead(instructionPointer + paramNum, getParameterMode(memory[instructionPointer], paramNum));
    }

    /**
     * Returns the parameter mode of the paramNum^th parameter given the opcode
     * @param opcode the opcode for the instruction
     * @param paramNum denotes which parameter we are looking at [1-indexed]
     * @return The parameter mode for the operation
     */
    private static ParameterMode getParameterMode(int opcode, int paramNum) {
        return ParameterMode.valueOf((opcode / (int) Math.pow(10, paramNum+1)) % 10);
    }

    /**
     * Performs a read into memory based on the parameter mode and desired address
     * @param addr the address of the read
     * @param mode the parameter mode of the read
     * @return the output of the read
     */
    private int memRead(int addr, ParameterMode mode) {
        switch (mode) {
            case POSITION_MODE:
                return memory[memory[addr]];
            case IMMEDIATE_MODE:
                return memory[addr];
            default:
                throw new Error("Unrecognized ParameterMode");
        }
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
        while ((opcode = memory[instructionPointer]) != 99) {
            InstructionCode instructionCode = InstructionCode.valueOf(opcode % 100);
            switch (instructionCode) {
                case ADD:
                    int addend1 = readParameter(1);
                    int addend2 = readParameter(2);
                    memory[memory[instructionPointer + 3]] = addend1 + addend2;
                    instructionPointer += 4;
                    break;
                case MULTIPLY:
                    int mult1 = readParameter(1);
                    int mult2 = readParameter(2);
                    memory[memory[instructionPointer + 3]] = mult1 * mult2;
                    instructionPointer += 4;
                    break;
                case INPUT:
                    int writeAddr = memory[instructionPointer + 1];
                    memory[writeAddr] = input.get();
                    instructionPointer += 2;
                    break;
                case OUTPUT:
                    System.out.println(readParameter(1)); // Or some other form of output
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

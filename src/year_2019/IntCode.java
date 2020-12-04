package year_2019;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class IntCode {

    public int[] memory;
    int instructionPointer = 0;
    BlockingQueue<Integer> input;
    BlockingQueue<Integer> output;

    public IntCode(int[] memory, BlockingQueue<Integer> input, BlockingQueue<Integer> output) {
        this.memory = memory.clone();
        this.input = input;
        this.output = output;
    }
    public IntCode(int[] memory) {
        this(memory, null, null);
    }


    public int[] getMemory() {
        return memory;
    }

    public void setInput(Supplier<Integer> supplier) {
        input = new SupplierQueue<>(supplier);
    }

    public void setInput(BlockingQueue<Integer> input) {
        this.input = input;
    }

    public void setOutput(BlockingQueue<Integer> output) {
        this.output = output;
    }


    public static IntCode createAndRun(int[] startingMemory) throws InterruptedException {
        return createAndRun(startingMemory, (BlockingQueue<Integer>) null, null);
    }

    /**
     * Uses a supplier to implement the blockingqueue "take" functionality
     * @param <V>
     */
    static class SupplierQueue<V> extends ArrayBlockingQueue<V> {

        private Supplier<V> supplier;

        public SupplierQueue(Supplier<V> supplier) {
            super(1);
            this.supplier = supplier;
        }

        @Override
        public V take() throws InterruptedException {
            return supplier.get();
        }
    }

    public static IntCode createAndRun(int[] startingMemory, Supplier<Integer> input) throws InterruptedException {
        return createAndRun(startingMemory, new SupplierQueue<Integer>(input), null);

    }

    public static IntCode createAndRun(int[] startingMemory, Supplier<Integer> input, BlockingQueue<Integer> output)
          throws InterruptedException {
      return createAndRun(startingMemory, new SupplierQueue<>(input), output);
    }

    public static IntCode createAndRun(int[] startingMemory, BlockingQueue<Integer> input, BlockingQueue<Integer> output)
            throws InterruptedException {
        IntCode program = new IntCode(startingMemory, input, output);
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


    /**
     * Runs the Intcode program MUTATES MEMORY
     */
    public void run() throws InterruptedException {
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
                    memory[writeAddr] = input.take();
                    instructionPointer += 2;
                    break;
                case OUTPUT:
                    output.add(readParameter(1));
                    //System.out.println(readParameter(1)); // Or some other form of output
                    instructionPointer += 2;
                    break;
                case JUMP_IF_TRUE:
                    instructionPointer = (readParameter(1) != 0)
                            ? readParameter(2)
                            : instructionPointer + 3;
                    break;
                case JUMP_IF_FALSE:
                    instructionPointer = (readParameter(1) == 0)
                            ? readParameter(2)
                            : instructionPointer + 3;
                    break;
                case LESS_THAN:
                    memory[memory[instructionPointer+3]] = (readParameter(1) < readParameter(2)) ? 1 : 0;
                    instructionPointer += 4;
                    break;
                case EQUALS:
                    memory[memory[instructionPointer+3]] = (readParameter(1) == readParameter(2)) ? 1 : 0;
                    instructionPointer += 4;
                    break;
                default:
                    throw new Error("Unexpected Opcode: " + opcode);
            }
        }
        return;
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

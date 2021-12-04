package year_2019;

import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

import static year_2019.ParameterMode.getParameterMode;

public class IntCode extends Thread {

    public Memory memory;
    int instructionPointer = 0;
    int relativeBase = 0;
    BlockingQueue<Integer> input;
    BlockingQueue<Integer> output;

    public IntCode(int[] memory, BlockingQueue<Integer> input, BlockingQueue<Integer> output) {
        this.memory = new Memory(memory.clone());
        this.input = input;
        this.output = output;
    }
    public IntCode(int[] memory) {
        this(memory, null, null);
    }


    public int[] getMemory() {
        return memory.toArray();
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

    public static IntCode createAndRun(int[] startingMemory, Supplier<Integer> input, BlockingQueue<Integer> output) throws InterruptedException {
      return createAndRun(startingMemory, new SupplierQueue<>(input), output);
    }

    public static IntCode createAndRun(int[] startingMemory, BlockingQueue<Integer> input, BlockingQueue<Integer> output) throws InterruptedException {
        IntCode program = new IntCode(startingMemory, input, output);
        program.start();
        program.join();
        return program;
    }

    /**
     * Returns the ith parameter of the instruction
     * @param paramNum 1-indexed parameter of the current instruction
     * @return the value of the parameter (accounting for ParameterMode)
     */
    int readParameter(int paramNum) {
        return memRead(instructionPointer + paramNum, getParameterMode(memory.read(instructionPointer), paramNum));
    }

    /**
     * Writes to the ith parameter of the instruction
     * @param paramNum 1-indexed parameter of the current instruction
     * @param value the value being written
     */
    void writeParameter(int paramNum, int value) {
        memWrite(instructionPointer + paramNum, getParameterMode(memory.read(instructionPointer), paramNum), value);
    }

    /**
     * Determines the "true" address in memory to use based on the parameter mode
     * @param addr the "given" address
     * @param mode the parameter mode
     * @return the "true" address in memory to use
     */
    private int getMemoryAddressFromParameterMode(int addr, ParameterMode mode) {
        switch (mode) {
            case POSITION_MODE:
                return memory.read(addr);
            case IMMEDIATE_MODE:
                return addr;
            case RELATIVE_MODE:
                return memory.read(addr) +  relativeBase;
            default:
                throw new Error("Unrecognized ParameterMode");
        }
    }

    /**
     * Performs a read into memory based on the parameter mode and desired address
     * @param addr the address of the read
     * @param mode the parameter mode of the read
     * @return the output of the read
     */
    private int memRead(int addr, ParameterMode mode) {
        return memory.read(getMemoryAddressFromParameterMode(addr, mode));
    }

    /**
     * Performs a write into memory based on the parameter mode and desired addres
     * @param addr the address of the write
     * @param mode the parameter mode of the write
     * @param val the value being written
     */
    private void memWrite(int addr, ParameterMode mode, int val) {
        memory.write(getMemoryAddressFromParameterMode(addr, mode), val);
    }


    /**
     * Runs the Intcode program MUTATES MEMORY
     */
    public void run() {
        instructionPointer = 0;
        int opcode;
        while ((opcode = memory.read(instructionPointer)) != 99) {
            InstructionCode instructionCode = InstructionCode.valueOf(opcode % 100);
            switch (instructionCode) {
                case ADD:
                    int addend1 = readParameter(1);
                    int addend2 = readParameter(2);
                    writeParameter(3, addend1 + addend2);
                    instructionPointer += 4;
                    break;
                case MULTIPLY:
                    int mult1 = readParameter(1);
                    int mult2 = readParameter(2);
                    writeParameter(3, mult1 * mult2);
                    instructionPointer += 4;
                    break;
                case INPUT:
                    try {
                        writeParameter(1, input.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new Error("Interrupted during input.take()");
                    }
                    instructionPointer += 2;
                    break;
                case OUTPUT:
                    int toOutput = readParameter(1);
                    output.add(toOutput);
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
                    writeParameter(3, readParameter(1) < readParameter(2) ? 1 : 0);
                    instructionPointer += 4;
                    break;
                case EQUALS:
                    writeParameter(3, readParameter(1) == readParameter(2) ? 1 : 0);
                    instructionPointer += 4;
                    break;
                case ADJUST_RELATIVE_BASE:
                    relativeBase += readParameter(1);
                    instructionPointer += 2;
                    break;
                default:
                    throw new Error("Unexpected Opcode: " + opcode);
            }
        }
    }
}

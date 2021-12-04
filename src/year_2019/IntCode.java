package year_2019;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

import static java.util.Map.entry;
import static year_2019.ParameterMode.getParameterMode;

public class IntCode extends Thread {

    public Memory memory;
    long instructionPointer = 0;
    int relativeBase = 0;
    BlockingQueue<Long> input;
    BlockingQueue<Long> output;

    private final Map<Integer, Runnable> instructionMap = Map.ofEntries(
            entry(1, this::add),
            entry(2, this::multiply),
            entry(3, this::input),
            entry(4, this::output),
            entry(5, this::jumpIfTrue),
            entry(6, this::jumpIfFalse),
            entry(7, this::lessThan),
            entry(8, this::equals),
            entry(9, this::adjustRelativeBase)
    );


    public IntCode(long[] memory, BlockingQueue<Long> input, BlockingQueue<Long> output) {
        this.memory = new Memory(memory.clone());
        this.input = input;
        this.output = output;
    }
    public IntCode(long[] memory) {
        this(memory, null, null);
    }


    public long[] getMemory() {
        return memory.toArray();
    }

    public void setInput(BlockingQueue<Long> input) {
        this.input = input;
    }

    public void setOutput(BlockingQueue<Long> output) {
        this.output = output;
    }


    public static IntCode createAndRun(long[] startingMemory) throws InterruptedException {
        return createAndRun(startingMemory, (BlockingQueue<Long>) null, null);
    }

    public static IntCode createAndRun(long[] startingMemory, Supplier<Long> input, BlockingQueue<Long> output) throws InterruptedException {
      return createAndRun(startingMemory, new SupplierQueue<>(input), output);
    }

    public static IntCode createAndRun(long[] startingMemory, BlockingQueue<Long> input, BlockingQueue<Long> output) throws InterruptedException {
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
    long readParameter(int paramNum) {
        return memRead(instructionPointer + paramNum, getParameterMode(memory.read(instructionPointer), paramNum));
    }

    /**
     * Writes to the ith parameter of the instruction
     * @param paramNum 1-indexed parameter of the current instruction
     * @param value the value being written
     */
    void writeParameter(int paramNum, long value) {
        memWrite(instructionPointer + paramNum, getParameterMode(memory.read(instructionPointer), paramNum), value);
    }

    /**
     * Determines the "true" address in memory to use based on the parameter mode
     * @param addr the "given" address
     * @param mode the parameter mode
     * @return the "true" address in memory to use
     */
    private long getMemoryAddressFromParameterMode(long addr, ParameterMode mode) {
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
    private long memRead(long addr, ParameterMode mode) {
        return memory.read(getMemoryAddressFromParameterMode(addr, mode));
    }

    /**
     * Performs a write into memory based on the parameter mode and desired addres
     * @param addr the address of the write
     * @param mode the parameter mode of the write
     * @param val the value being written
     */
    private void memWrite(long addr, ParameterMode mode, long val) {
        memory.write(getMemoryAddressFromParameterMode(addr, mode), val);
    }


    /**
     * Runs the Intcode program MUTATES MEMORY
     */
    public void run() {
        instructionPointer = 0;
        long opcode;
        while ((opcode = memory.read(instructionPointer)) != 99) {
            int instructionCode = (int) opcode % 100;
            Runnable instruction = instructionMap.getOrDefault(instructionCode, this::throw_error_unrecognized_opcode);
            instruction.run();
        }
    }

    private void throw_error_unrecognized_opcode() {
        throw new Error("Unexpected Opcode: " + memory.read(instructionPointer));
    }

    private void adjustRelativeBase() {
        relativeBase += readParameter(1);
        instructionPointer += 2;
    }

    private void equals() {
        writeParameter(3, readParameter(1) == readParameter(2) ? 1 : 0);
        instructionPointer += 4;
    }

    private void lessThan() {
        writeParameter(3, readParameter(1) < readParameter(2) ? 1 : 0);
        instructionPointer += 4;
    }

    private void jumpIfFalse() {
        instructionPointer = (readParameter(1) == 0)
                ? readParameter(2)
                : instructionPointer + 3;
    }

    private void jumpIfTrue() {
        instructionPointer = (readParameter(1) != 0)
                ? readParameter(2)
                : instructionPointer + 3;
    }

    private void output() {
        long toOutput = readParameter(1);
        output.add(toOutput);
        instructionPointer += 2;
    }

    private void input() {
        try {
            writeParameter(1, input.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new Error("Interrupted during input.take()");
        }
        instructionPointer += 2;
    }

    private void multiply() {
        long mult1 = readParameter(1);
        long mult2 = readParameter(2);
        writeParameter(3, mult1 * mult2);
        instructionPointer += 4;
    }

    private void add() {
        long addend1 = readParameter(1);
        long addend2 = readParameter(2);
        writeParameter(3, addend1 + addend2);
        instructionPointer += 4;
    }
}

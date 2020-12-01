package year_2019;

import java.util.Arrays;

public class IntCode {


    int[] memory;

    public IntCode(int[] memory) {
        this.memory = memory;
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
        int curr_idx = 0;
        int opcode;
        while ((opcode = memory[curr_idx]) != 99) {
            System.out.println(this);
            switch (opcode) {
                case 1:
                    int addend1 = memory[memory[curr_idx + 1]];
                    int addend2 = memory[memory[curr_idx + 2]];
                    memory[memory[curr_idx + 3]] = addend1 + addend2;
                    curr_idx += 4;
                    break;
                case 2:
                    int mult1 = memory[memory[curr_idx + 1]];
                    int mult2 = memory[memory[curr_idx + 2]];
                    memory[memory[curr_idx + 3]] = mult1 * mult2;
                    curr_idx += 4;
                    break;
                default:
                    throw new Error("Unexpected Opcode");
            }
        }
    }
}

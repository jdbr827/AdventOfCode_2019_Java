package year_2019.IntCodeComputer;


interface IntCodeInstruction {

    IntCodeInstruction make(int opcode);

    int[] execute(int[] memory);

}

package year_2019;


interface IntCodeInstruction {

    IntCodeInstruction make(int opcode);

    int[] execute(int[] memory);

}

package year_2022.day_09;

import java.io.FileNotFoundException;

public class Day9 {

    static int part1(String fileName) throws FileNotFoundException {
        Rope myRope = new Rope(2);
        Day9Scanner myScanner = new Day9Scanner(fileName);
        Day9InstructionPair instructionPair;
        while ((instructionPair = myScanner.getNextInstruction()) != null) {
            for (int i=0; i<instructionPair.numTimes; i++) {
                myRope.moveRope(instructionPair.direction);
            }
        }
        return myRope.numVisited();
    }

    public static int part2(String fileName) throws FileNotFoundException {
        Rope myRope = new Rope(10);
        Day9Scanner myScanner = new Day9Scanner(fileName);
        Day9InstructionPair instructionPair;
        while ((instructionPair = myScanner.getNextInstruction()) != null) {
            for (int i=0; i<instructionPair.numTimes; i++) {
                myRope.moveRope(instructionPair.direction);
            }
        }
        return myRope.numVisited();
    }
}

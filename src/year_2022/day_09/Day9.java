package year_2022.day_09;

import java.io.FileNotFoundException;

public class Day9 {

    public static int part1(String fileName) throws FileNotFoundException {
        Rope myRope = new Rope(2);
        Day9Scanner myScanner = new Day9Scanner(fileName);
        myScanner.forEachRemaining(day9InstructionPair -> day9InstructionPair.accept(myRope));
        return myRope.numTailVisited();
    }


    public static int part2(String fileName) throws FileNotFoundException {
        Rope myRope = new Rope(10);
        Day9Scanner myScanner = new Day9Scanner(fileName);
        myScanner.forEachRemaining(day9InstructionPair -> day9InstructionPair.accept(myRope));
        return myRope.numTailVisited();
    }
}

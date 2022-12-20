package year_2022.day_06;

import java.io.FileNotFoundException;

public interface IDay6 {

    static int part1(String fileName) throws FileNotFoundException {
        return new Day6(fileName, 4).findStepsUntilLastNAllDiffMethod1();
    }

    static int part2(String fileName) throws FileNotFoundException {
        return new Day6(fileName, 14).findStepsUntilLastNAllDiffMethod2();
    }
}

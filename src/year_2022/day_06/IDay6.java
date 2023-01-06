package year_2022.day_06;

import java.io.FileNotFoundException;

public interface IDay6 {

    static int part1(String fileName) throws FileNotFoundException {
        return solve(fileName, 4, 3,1);
    }

    static int part2(String fileName) throws FileNotFoundException {
        return solve(fileName, 14, 3,1);
    }

    static Day6 create(String fileName, int N, int algoMethod, int helperMethod) throws FileNotFoundException {
        switch (algoMethod) {
            case 1:
                return new Day6Method1(Day6DataStream.of(fileName, N, helperMethod), N);
            case 2:
                return new Day6Method2(Day6DataStream.of(fileName, N, helperMethod), N);
            case 3:
            default:
                return new Day6Method3(Day6DataStream.of(fileName, N, helperMethod), N);
        }
    }

    static int solve(String fileName, int N, int algoMethod, int helperMethod) throws FileNotFoundException {
        return create(fileName, N, algoMethod, helperMethod).findStepsUntilLastNAllDiff();
    }

}

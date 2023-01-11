package year_2022.day_06;

import java.io.FileNotFoundException;

public interface IDay6 {

    static int part1(String fileName) throws FileNotFoundException {
        return solve(fileName, 4, 3,1);
    }

    static int part2(String fileName) throws FileNotFoundException {
        return solve(fileName, 14, 3,1);
    }



    static int solve(String fileName, int N, int algoMethod, int helperMethod) throws FileNotFoundException {
        return createDay6Solver(algoMethod).findStepsUntilLastNAllDiff(createDataStream(fileName, N, helperMethod), N);
    }

    static Day6Solver createDay6Solver(int algoMethod) throws FileNotFoundException {
        switch (algoMethod) {
            case 1:
                return new Day6SolverMethod1();
            case 2:
                return new Day6SolverMethod2();
            case 3:
            default:
                return new Day6SolverMethod3();
        }
    }


    static Day6DataStream createDataStream(String fileName, int N, int method) throws FileNotFoundException {
        Day6Reader scanner = new Day6Reader(fileName);
        if (method == 1) {
            return new Day6DataStream1(scanner, N);
        }
        return new Day6DataStream2(scanner, N);
    }
}

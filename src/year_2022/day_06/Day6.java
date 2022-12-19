package year_2022.day_06;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Day6 {


    public static int part1(String fileName) throws FileNotFoundException {
        return findStepsUntilLastNAllDiff(fileName, 4);
    }

    public static int part2(String fileName) throws FileNotFoundException {
        return findStepsUntilLastNAllDiff(fileName, 14);
    }

    protected static int findStepsUntilLastNAllDiff(String fileName, int N) throws FileNotFoundException {
        Day6Scanner scanner = new Day6Scanner(fileName);

        Character[] previous = new Character[N];
        for (int i = 0; i < N; i++) {
            previous[N-1 - i] = scanner.getNextChar();
        }

        while (true) {
            boolean allDiff = true;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (previous[i].equals(previous[j])) {
                        allDiff = false;
                        break;
                    }
                }
            }
            if (allDiff) {
                return scanner.getNumScanned();
            }

            for (int i = N-1; i > 0; i--) {
                previous[i] = previous[i - 1];
            }

            previous[0] = scanner.getNextChar();
        }
    }

    protected static int findStepsUntilLastNAllDiffMethod2(String fileName, int N) throws FileNotFoundException {
        Day6Scanner scanner = new Day6Scanner(fileName);


        Character[] previous = new Character[N];
        for (int i = 0; i < N; i++) {
            previous[N - 1 - i] = scanner.getNextChar();
        }

        int minToScan = getMinToScan(previous);

        while (minToScan > 0) {
            for (int i = N - 1; i > 0; i--) {
                previous[i] = previous[i - 1];
            }
            previous[0] = scanner.getNextChar();
            minToScan -= 1;

            minToScan = Math.max(minToScan, getMinToScan(previous));
        }

        return scanner.getNumScanned();
    }

    private static int getMinToScan(Character[] p) {
        int N = p.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (p[i].equals(p[j])) {
                    return N-i;
                }
            }
        }
        return 0;



    }


}

package year_2022.day_06;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Day6 {
    Day6Scanner scanner;
    int N;
    Character[] previous;

    public Day6(String fileName, int N) throws FileNotFoundException {
        scanner = new Day6Scanner(fileName);
        this.N = N;
        this.previous = new Character[N];



    }

    public static int part1(String fileName) throws FileNotFoundException {
        return findStepsUntilLastNAllDiffStatic(fileName, 4);
    }

    public static int part2(String fileName) throws FileNotFoundException {
        return findStepsUntilLastNAllDiffStatic(fileName, 14);
    }

    protected static int findStepsUntilLastNAllDiffStatic(String fileName, int N) throws FileNotFoundException {
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

    protected int findStepsUntilLastNAllDiffMethod2() {


        for (int i = 0; i < N; i++) {
            previous[N - 1 - i] = scanner.getNextChar();
        }


        /*
         * LOOP INVARIANT:
         * - previous is the N most recently scanned chars, previous[0] most recent and previous[N-1] least recent.
         * - nearestLater is the smallest j such that there exists i<j such that previous[i] = previous[j]
         */


        int nearestLater = getMinToScan(previous);


        while (nearestLater < N) {
            cyclePrevious(N, previous);
            nearestLater += 1;

            for (int i=1; i<nearestLater; i++) {
                if (previous[i].equals(previous[0])) {
                    nearestLater = i;
                }
            }
        }

        return scanner.getNumScanned();
    }

    protected int findStepsUntilLastNAllDiffMethod3(int N) {
        int minToScan = N;

        while (minToScan > 0) {
            cyclePrevious(N, previous);
            minToScan -= 1;

            for (int i=1; i<N-minToScan; i++) {
                if (previous[i].equals(previous[0])) {
                    minToScan = N-i;
                }
            }
        }

        return scanner.getNumScanned();
    }

    private void cyclePrevious(int N, Character[] previous) {
        for (int i = N - 1; i > 0; i--) {
            previous[i] = previous[i - 1];
        }
        previous[0] = scanner.getNextChar();
    }

    private static int getMinToScan(Character[] p) {
        int N = p.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (p[i].equals(p[j])) {
                    return i;
                }
            }
        }
        return 0;



    }


}

package year_2022.day_06;

import lombok.Getter;

import java.io.FileNotFoundException;

/**
 * PROBLEM STATEMENT:
 * INPUTS:
 * - Stream S of Characters
 * - Integer N > 0
 * OUTPUT:
 * C := The number of characters streamed before the first time that the last N characters streamed from S are all different
 */
public class Day6 {
    IDay6Helper helper;
    int N;
    int numScanned = 0;

    public Day6(String fileName, int N) throws FileNotFoundException {
        this.N = N;
        helper = new Day6Helper1(fileName);

    }

    /* O(CxN^2) bottle-necked at checking for all diff for each new char */
    public int findStepsUntilLastNAllDiffMethod1() {


        for (int i = 0; i < N; i++) {                           // O(N)
            scanNextChar();                                     // O(N)
        }

        while (true) {                                          // O(C)
            boolean allDiff = true;
            for (int i = 0; i < N; i++) {                           // O(N)
                for (int j = i + 1; j < N; j++) {                       // O(N)
                    if (getCharScannedXAgo(i).equals(getCharScannedXAgo(j))) {
                        allDiff = false;
                        break;
                    }
                }
            }
            if (allDiff) {
                return numScanned;
            } else {
                scanNextChar();                          // O(N)
            }
        }
    }


    protected int findStepsUntilLastNAllDiffMethod2() {

        /*
         * LOOP INVARIANT:
         * nearestLater is the smallest j such that there exists i<j such that previous[i] = previous[j]
         *      and as a base case, assume the "-1"th element scanned is equal to all other characters.
         */
        int nearestLater = 0;
        while (nearestLater < N) {                          // O(C)
            scanNextChar();                          // O(N)
            nearestLater += 1;
            for (int i = 1; i < nearestLater; i++) {            // O(N)
                if (getCharScannedXAgo(i).equals(getCharScannedXAgo(0))) {
                    nearestLater = i;
                    break;
                }
            }
        }

        return numScanned;
    }


    protected int findStepsUntilLastNAllDiffMethod3() {

        int minToScan = N;
        while (minToScan > 0) {
            scanNextChar();
            minToScan -= 1;

            for (int i = 1; i < N - minToScan; i++) {
                if (getCharScannedXAgo(i).equals(getCharScannedXAgo(0))) {
                    minToScan = N - i;
                }
            }
        }

        return numScanned;
    }

    private void scanNextChar() {
        numScanned++;
        helper.scanNextChar();
    }

    private Character getCharScannedXAgo(int x) {
        return helper.getCharScannedXAgo(x);
    }


    /**
     * Encapsulates the scanner and the "previous N" part of the problem.
     * With access control, we can abstract away how previous is updated.
     */
    interface IDay6Helper {

        void scanNextChar();

        /**
         * previous is the N most recently scanned chars, previous[0] most recent and previous[N-1] least recent.
         * @param x integer between 0 and N-1 inclusive.
         * @return the character that was scanned x scans ago (0 for most recently scanned)
         */
        Character getCharScannedXAgo(int x);
    }


    /* Linear time update, Constant time get */
    class Day6Helper1 implements IDay6Helper {
        private final Day6Scanner scanner;
        @Getter private final Character[] previous;

        Day6Helper1(String fileName) throws FileNotFoundException {
            scanner = new Day6Scanner(fileName);
            this.previous = new Character[N];
        }

        public void scanNextChar() {
            for (int i = N - 1; i > 0; i--) {
                previous[i] = previous[i - 1];
            }
            previous[0] = scanner.getNextChar();
        }

        @Override
        public Character getCharScannedXAgo(int x) {
            return previous[x];
        }
    }



    /* Constant time update and get using "clock-face" implementation */
    class Day6Helper2 implements IDay6Helper {
        private final Day6Scanner scanner;
        private int head_idx = N-1;
        @Getter private final Character[] previous;

        Day6Helper2(String fileName) throws FileNotFoundException {
            scanner = new Day6Scanner(fileName);
            this.previous = new Character[N];
        }

        public void scanNextChar() {
            head_idx++;
            head_idx %= N;
            previous[head_idx] = scanner.getNextChar();
        }

        @Override
        public Character getCharScannedXAgo(int x) {
            return previous[(head_idx - x + N) % N];
        }
    }

}

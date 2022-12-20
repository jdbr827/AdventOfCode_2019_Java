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
public abstract class Day6 {
    IDay6Helper helper;
    int N;
    int numScanned = 0;

    public Day6(String fileName, int N, int helperMethod) throws FileNotFoundException {
        this.N = N;

        switch (helperMethod) {
            case 1:
                helper = new Day6Helper1(fileName, N);
            case 2:
                helper = new Day6Helper2(fileName, N);
            default:
                helper = new Day6Helper1(fileName, N);
        }
    }

    public abstract int findStepsUntilLastNAllDiff();

    protected void scanNextChar() {
        numScanned++;
        helper.scanNextChar();
    }

    protected Character getCharScannedXAgo(int x) {
        return helper.getCharScannedXAgo(x);
    }

};

class Day6Method1 extends Day6 {


    public Day6Method1(String fileName, int N, int helperMethod) throws FileNotFoundException {
        super(fileName, N, helperMethod);
    }

    /* O(CxN^2) bottle-necked at checking for all diff for each new char */
    public int findStepsUntilLastNAllDiff() {


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
};

class Day6Method2 extends Day6 {

    public Day6Method2(String fileName, int N, int helperMethod) throws FileNotFoundException {
        super(fileName, N, helperMethod);
    }

    public int findStepsUntilLastNAllDiff() {

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

};

class Day6Method3 extends Day6 {

    public Day6Method3(String fileName, int N, int helperMethod) throws FileNotFoundException {
        super(fileName, N, helperMethod);
    }

    public int findStepsUntilLastNAllDiff() {

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
}






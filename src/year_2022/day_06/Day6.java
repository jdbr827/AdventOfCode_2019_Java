package year_2022.day_06;


import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * PROBLEM STATEMENT:
 * INPUTS:
 * - Stream S of Characters
 * - Integer N > 0
 * OUTPUT:
 * C := The number of characters streamed before the first time that the last N characters streamed from S are all different
 *
 *
 * COMPLEXITY PARAMETERS:
 * C (as output),
 * N (as input), 4 and 14 in the problem
 * U := time it takes to scan a new char and update the information about previous scans (scanNextChar())
 * G := time it takes to get the char that was scanned x scans ago (getCharScannedXAgo())
 *
 * we know N is O(C)
 */
@RequiredArgsConstructor
public abstract class Day6 {
    @NotNull Day6DataStream helper;
    @NotNull Integer N;
    Integer numScanned = 0;

    public abstract int findStepsUntilLastNAllDiff();

    protected void scanNextChar() {
        numScanned++;
        helper.scanNextChar();
    }

    protected Character getCharScannedXAgo(int x) {
        if (numScanned <= x) {
            throw new Error("We haven't scanned enough to go back that far yet.");
        }
        return helper.getCharScannedXAgo(x);
    }

}

class Day6Method1 extends Day6 {
    public Day6Method1(Day6DataStream of, int n) {
        super(of, n);
    }

    /* O(NU + GC(N^2) + CU) = O(CU + C(N^2)G) bottle-necked at checking for all diff for each new char */
    public int findStepsUntilLastNAllDiff() {


        for (int i = 0; i < N; i++) {                           // O(N)
            scanNextChar();                                     // O(U)
        }

        while (true) {                                          // O(C)
            boolean allDiff = true;
            for (int i = 0; i < N; i++) {                           // O(N)
                for (int j = i + 1; j < N; j++) {                       // O(N)
                    if (getCharScannedXAgo(i).equals(getCharScannedXAgo(j))) { // O(G)
                        allDiff = false;
                        break;
                    }
                }
            }
            if (allDiff) {
                return numScanned;
            } else {
                scanNextChar();                                     // O(U)
            }
        }
    }
}


class Day6Method2 extends Day6 {

    public Day6Method2(Day6DataStream of, int n) {
        super(of, n);
    }

    /* O(C(U + NG)), strictly better than method 1 */
    public int findStepsUntilLastNAllDiff() {

        /*
         * LOOP INVARIANT:
         * nearestLater is the smallest j such that there exists i<j such that previous[i] = previous[j]
         *      as a base case, and to ensure we scan the minimum required number of elements,
         *      assume the "-1"th element scanned is equal to all other characters.
         */
        int nearestLater = 0;
        while (nearestLater < N) {                                          // O(C)
            scanNextChar();                                                      // O(U)
            nearestLater += 1;

            /*
             * Because of the loop invariant, any pair not involving
             * the most recently scanned char would have been
             * found on a previous iteration
             */
            Character mostRecentlyScannedChar = getCharScannedXAgo(0);
            for (int j = 1; j < nearestLater; j++) {                            // O(N) worst case but will usually be less
                if (getCharScannedXAgo(j).equals(mostRecentlyScannedChar)) {        // O(G)
                    nearestLater = j;
                    break; // because nearestLater is smallest such j
                }
            }
        }

        return numScanned;
    }

}

class Day6Method3 extends Day6 {
    public Day6Method3(Day6DataStream of, int n) {
        super(of, n);
    }

    /* Also O(CU + CNG), equivalent to Method 2 */
    public int findStepsUntilLastNAllDiff() {

        int minToScan = N;
        while (minToScan > 0) {                                                 // O(C)
            scanNextChar();                                                     // O(U)
            minToScan -= 1;

            for (int i = 1; i < N - minToScan; i++) {                           // O(N)
                if (getCharScannedXAgo(i).equals(getCharScannedXAgo(0))) {      // O(G)
                    minToScan = N - i;
                }
            }
        }

        return numScanned;
    }
}

/*
Fastest Solution is Method 2 or 3 with Helper 2

O(CU + CNG) => O(C + CN) = O(CN) worst case

 */






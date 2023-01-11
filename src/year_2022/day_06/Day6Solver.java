package year_2022.day_06;


import lombok.NoArgsConstructor;
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
public interface Day6Solver {
    int findStepsUntilLastNAllDiff(@NotNull Day6DataStream dataStream,  @NotNull Integer N);
}

@NoArgsConstructor
class Day6SolverMethod1 implements Day6Solver {

    /* O(NU + GC(N^2) + CU) = O(CU + C(N^2)G) bottle-necked at checking for all diff for each new char */
    public int findStepsUntilLastNAllDiff(@NotNull Day6DataStream dataStream, @NotNull Integer N) {


        for (int i = 0; i < N; i++) {                           // O(N)
            // O(U)
            dataStream.scanNextChar();
        }

        while (true) {                                          // O(C)
            boolean allDiff = true;
            for (int i = 0; i < N; i++) {                           // O(N)
                for (int j = i + 1; j < N; j++) {                       // O(N)
                    if (dataStream.getCharScannedXAgo(i).equals(dataStream.getCharScannedXAgo(j))) { // O(G)
                        allDiff = false;
                        break;
                    }
                }
            }
            if (allDiff) {
                return dataStream.getNumScanned();
            } else {
                // O(U)
                dataStream.scanNextChar();
            }
        }
    }
}


class Day6SolverMethod2 implements Day6Solver {

    /* O(C(U + NG)), strictly better than method 1 */
    public int findStepsUntilLastNAllDiff(@NotNull Day6DataStream dataStream, @NotNull Integer N) {

        /*
         * LOOP INVARIANT:
         * nearestLater is the smallest j such that there exists i<j such that previous[i] = previous[j]
         *      as a base case, and to ensure we scan the minimum required number of elements,
         *      assume the "-1"th element scanned is equal to all other characters.
         */
        int nearestLater = 0;
        while (nearestLater < N) {                                          // O(C)

            dataStream.scanNextChar();                                          // O(U)
            nearestLater += 1;

            /*
             * Because of the loop invariant, any pair not involving
             * the most recently scanned char would have been
             * found on a previous iteration
             */
            Character mostRecentlyScannedChar = dataStream.getCharScannedXAgo(0);
            for (int j = 1; j < nearestLater; j++) {                            // O(N) worst case but will usually be less
                if (dataStream.getCharScannedXAgo(j).equals(mostRecentlyScannedChar)) {        // O(G)
                    nearestLater = j;
                    break; // because nearestLater is smallest such j
                }
            }
        }

        return dataStream.getNumScanned();
    }

}

class Day6SolverMethod3 implements Day6Solver {

    /* Also O(CU + CNG), equivalent to Method 2 */
    public int findStepsUntilLastNAllDiff(@NotNull Day6DataStream dataStream, @NotNull Integer N) {

        int minToScan = N;
        while (minToScan > 0) {                                                 // O(C)
            // O(U)
            dataStream.scanNextChar();
            minToScan -= 1;

            for (int i = 1; i < N - minToScan; i++) {                           // O(N)
                if (dataStream.getCharScannedXAgo(i).equals(dataStream.getCharScannedXAgo(0))) {      // O(G)
                    minToScan = N - i;
                }
            }
        }

        return dataStream.getNumScanned();
    }
}

/*
Fastest Solution is Method 2 or 3 with Helper 2

O(CU + CNG) => O(C + CN) = O(CN) worst case

 */






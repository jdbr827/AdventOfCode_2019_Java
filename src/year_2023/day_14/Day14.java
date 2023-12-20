package year_2023.day_14;

import utils.AOCScanner;

import java.util.Arrays;

public class Day14 {
    Character[][] rockPlatform;

    int numRows() {
        return rockPlatform.length;
    }

    int numCols() {
        return rockPlatform[0].length;
    }


    public Day14(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        rockPlatform = scanner.scanAsChar2DArray();
    }

    public int calculateLoadOnNorthSupportBeams() {
        int total = 0;
        tiltNorth();
         for (int i=0; i<numRows(); i++) {
            for (int j=0; j<numCols(); j++) {
                if (rockPlatform[i][j] == 'O') {
                    total += numRows() - i;
                }
            }
        }
         return total;

    }

    private void tiltNorth() {
        int[] lastRock = new int[numCols()];
        Arrays.fill(lastRock, -1);


        for (int i=0; i<numRows(); i++) {
            for (int j=0; j<numCols(); j++) {
                if (rockPlatform[i][j] == 'O') {
                    int newI = lastRock[j] + 1;
                    rockPlatform[i][j] = '.';
                    rockPlatform[newI][j] = 'O';
                    lastRock[j] = newI;
                }
                if (rockPlatform[i][j] == '#') {
                    lastRock[j] = i;
                }
            }
        }
    }
}

package year_2023.day_14;

import utils.AOCScanner;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

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

    public void runCycle(int nTimes) {
        for (int i=0; i<nTimes; i++) {
            tiltNorth();
            tiltWest();
            tiltSouth();
            tiltEast();
        }

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

    private void tiltWest() {
        int[] lastRock = new int[numRows()];
        Arrays.fill(lastRock, -1);

        for (int j=0; j<numCols(); j++) {
            for (int i=0; i<numRows(); i++) {
                if (rockPlatform[i][j] == 'O') {
                    int newJ = lastRock[i] + 1;
                    rockPlatform[i][j] = '.';
                    rockPlatform[i][newJ] = 'O';
                    lastRock[i] = newJ;
                }
                if (rockPlatform[i][j] == '#') {
                    lastRock[i] = j;
                }
            }
        }
    }

    private void tiltEast() {
        int[] lastRock = new int[numRows()];
        Arrays.fill(lastRock, numCols());

        for (int j=numCols()-1; j>=0; j--) {
            for (int i=0; i<numRows(); i++) {
                if (rockPlatform[i][j] == 'O') {
                    int newJ = lastRock[i] - 1;
                    rockPlatform[i][j] = '.';
                    rockPlatform[i][newJ] = 'O';
                    lastRock[i] = newJ;
                }
                if (rockPlatform[i][j] == '#') {
                    lastRock[i] = j;
                }
            }
        }
    }

    private void tiltSouth() {
        int[] lastRock = new int[numCols()];
        Arrays.fill(lastRock, numRows());

        for (int i=numRows()-1; i>=0; i--) {
            for (int j=0; j<numCols(); j++) {
                if (rockPlatform[i][j] == 'O') {
                    int newI = lastRock[j] - 1;
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


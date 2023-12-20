package year_2023.day_14;

import utils.AOCScanner;

public class Day14 {
    Character[][] rockPlatform;


    public Day14(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        rockPlatform = scanner.scanAsChar2DArray();
    }

    public int calculateLoadOnNorthSupportBeams() {
        int total = 0;
        for (int col=0; col<rockPlatform.length; col++) {
            total += calculateLoadOnNorthSupportBeamsFromColumn(col);
        }
        return total;
    }

    private int calculateLoadOnNorthSupportBeamsFromColumn(int col) {
        int total = 0;
        int numRows = rockPlatform[0].length;
        int lastCube = numRows + 1;
        int roundRocksFoundSinceLastCubeRock = 0;
        for (int i=0; i<numRows; i++) {
            if (rockPlatform[i][col] == 'O') {
                roundRocksFoundSinceLastCubeRock++;
                total += lastCube - roundRocksFoundSinceLastCubeRock;
            }
            if (rockPlatform[i][col] == '#') {
                lastCube = numRows - i;
                roundRocksFoundSinceLastCubeRock = 0;
            }
        }


        return total;
    }
}

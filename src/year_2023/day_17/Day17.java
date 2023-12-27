package year_2023.day_17;

import utils.AOCScanner;


public class Day17 {
    Integer[][] heat;
    int N;
    int M;

    public Day17(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Character[][] heatChars = scanner.scanAsChar2DArray();
        N = heatChars.length;
        M = heatChars[0].length;
        heat = new Integer[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                heat[i][j] = Integer.parseInt(heatChars[i][j].toString());
            }
        }
    }

    public int minimizeHeatLoss() {
        return 0;
    }
}

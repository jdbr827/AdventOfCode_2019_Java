package year_2024.day_04;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;

import java.util.List;

public class Day4 {
    public static final List<Pair<Integer, Integer>> velocities = List.of(Pair.of(1, 0), Pair.of(1, 1), Pair.of(0, 1), Pair.of(-1, 1), Pair.of(-1, 0), Pair.of(-1, -1), Pair.of(0, -1), Pair.of(1, -1));
    public static final String XMAS = "XMAS";

    Character[][] grid;
    int n;
    int m;

    public Day4(String smallInputFilename) {
        grid = new AOCScanner(smallInputFilename).scanAsChar2DArray();
        n = grid.length;
        m = grid[0].length;
    }


    public int timesXMASAppears() {
        int appearances = 0;
        for (int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if (grid[i][j] == 'X') {
                    for (Pair<Integer, Integer> velocity: velocities) {
                        int i_star = i;
                        int j_star = j;
                        int k;
                        for(k=1; k<4; k++) {
                            i_star += velocity.first();
                            j_star += velocity.second();
                            if (i_star < 0 || i_star >= n || j_star < 0 || j_star >= m || grid[i_star][j_star] != XMAS.charAt(k)) {
                                break;
                            }
                        }
                        if (k == 4) {appearances += 1;}
                    }
                }
            }
        }
        return appearances;
    }

    public int timesMASAppearsInAnX() {
        int appearances = 0;
        for (int i=1; i<n-1; i++) {
            for(int j=1; j<m-1; j++) {
                if (grid[i][j] == 'A') {
                    if (
                            (
                                    (grid[i-1][j-1] == 'M' && grid[i+1][j+1] == 'S') || (grid[i-1][j-1] == 'S' && grid[i+1][j+1] == 'M')
                            ) && (
                                    (grid[i-1][j+1] == 'M' && grid[i+1][j-1] == 'S') || (grid[i-1][j+1] == 'S' && grid[i+1][j-1] == 'M')
                            )
                    ) {appearances += 1;}
                }
            }
        }
        return appearances;
    }
}

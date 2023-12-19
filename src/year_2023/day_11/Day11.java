package year_2023.day_11;

import utils.AOCScanner;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    List<Point> galaxyLocations;
    List<Integer> emptyRows;
    List<Integer> emptyColumns;
    int emptyDistance;

    public Day11(String fileName, int emptyDistance) {
        this.emptyDistance = emptyDistance;
        AOCScanner scanner = new AOCScanner(fileName);
        galaxyLocations = new ArrayList<>();
        emptyRows = new ArrayList<>();
        emptyColumns = new ArrayList<>();

        int N = 0; int M = 0;
        for (int i=0; scanner.hasNextLine(); i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') {
                    galaxyLocations.add(new Point(i, j));
                }
            }
            N = i;
            M = line.length();
        }
        N++;

        for (int i=0; i<N; i++) {
            int finalI = i;
            if (galaxyLocations.stream().noneMatch(location -> location.getX() == finalI)) {
                emptyRows.add(i);
            }
        }

        for (int j=0; j<M; j++) {
            int finalJ = j;
            if (galaxyLocations.stream().noneMatch(location -> location.getY() == finalJ)) {
                emptyColumns.add(j);
            }
        }
    }

    public long sumOfShortestDistancePairs() {
        long total = 0L;
        for (int idx1=0; idx1<galaxyLocations.size(); idx1++) {
            for (int idx2 = idx1+1; idx2 < galaxyLocations.size(); idx2 ++) {
                total += shortestDistance(galaxyLocations.get(idx1), galaxyLocations.get(idx2));
            }
        }
        return total;
    }

    private long shortestDistance(Point point1, Point point2) {
        long distance = 0L;
        for (int i=Math.min(point1.x, point2.x)+1; i<=Math.max(point1.x, point2.x); i++) {
            if (isEmptyRow(i)) {
                distance += emptyDistance;
            } else {
                distance += 1;
            }
        }


        for (int j=Math.min(point1.y, point2.y)+1; j<=Math.max(point1.y, point2.y); j++) {
            if (isEmptyCol(j)) {
                distance += emptyDistance;
            } else {
                distance += 1;
            }
        }

        return distance;
    }

    private boolean isEmptyCol(int j) {
        return emptyColumns.contains(j);
    }

    private boolean isEmptyRow(int i) {
        return emptyRows.contains(i);
    }
}

package year_2024.day_10;

import utils.AOCScanner;
import utils.BFSUtil;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    Character[][] grid;
    int N;
    int M;

    public Day10(String smallInputFilename) {
        grid = new AOCScanner(smallInputFilename).scanAsChar2DArray();
        N = grid.length;
        M = grid[0].length;
    }

    public int trailheadScoreSum() {
        List<CartesianPoint> trailHeads = new ArrayList<>();
        List<CartesianPoint> destinations = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j=0; j< grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    trailHeads.add(new CartesianPoint(i, j));
                } else if (grid[i][j] == '9') {
                    destinations.add(new CartesianPoint(i, j));
                }
            }
        }


        int totScore = 0;
        for (CartesianPoint origin: trailHeads) {
            totScore += (int) BFSUtil.doBFSAnyDestination(origin, destinations, (p ->
                Arrays.stream(CardinalDirection.values())
                        .map(d -> p.add(d.velocity))
                        .filter((nbr) -> nbr.isInBoundariesInclusive(0, N-1, 0, M-1) && Integer.parseInt(String.valueOf(grid[p.x][p.y])) + 1 == Integer.parseInt(String.valueOf(grid[nbr.x][nbr.y])))
                        .toList()
            ))
                    .values().stream()
                    .filter(d -> d == 9)
                    .count();
        }
        return totScore;
    }
}

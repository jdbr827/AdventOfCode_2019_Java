package year_2024.day_10;

import utils.AOCScanner;
import utils.BFSUtil;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.*;
import java.util.List;

public class Day10 {
    Character[][] grid;
    int N;
    int M;
    List<CartesianPoint> trailHeads;
    List<CartesianPoint> destinations;

    public Day10(String smallInputFilename) {
        grid = new AOCScanner(smallInputFilename).scanAsChar2DArray();
        N = grid.length;
        M = grid[0].length;
        trailHeads = new ArrayList<>();
        destinations = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j=0; j< grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    trailHeads.add(new CartesianPoint(i, j));
                } else if (grid[i][j] == '9') {
                    destinations.add(new CartesianPoint(i, j));
                }
            }
        }
    }


    public int trailheadScoreSum() {
        return trailHeads.stream().map(this::getTrailHeadScore).reduce(0, Math::addExact);
    }

    public int gridValue(CartesianPoint p) {
        return Integer.parseInt(String.valueOf(grid[p.x][p.y]));
    }
    private int getTrailHeadScore(CartesianPoint trailHead) {
        return (int) BFSUtil.doBFSAnyDestination(trailHead, destinations, (p ->
                        Arrays.stream(CardinalDirection.values())
                                .map(d -> p.add(d.velocity))
                                .filter((nbr) -> nbr.isInBoundariesInclusive(0, N - 1, 0, M - 1) && Integer.parseInt(String.valueOf(grid[p.x][p.y])) + 1 == Integer.parseInt(String.valueOf(grid[nbr.x][nbr.y])))
                                .toList()
                ))
                .values().stream()
                .filter(d -> d == 9)
                .count();
    }

    public int trailheadRatingSum() {
        return trailHeads.stream().map(this::getTrailHeadRating).reduce(0, Math::addExact);
    }

    private int getTrailHeadRating(CartesianPoint trailHead) {
        Stack<CartesianPoint> stack = new Stack<>();
        int rating = 0;

        stack.push(trailHead);
        while (!stack.isEmpty()) {
            CartesianPoint p = stack.pop();
            if (gridValue(p) == 9) {
                rating += 1;
            }
            stack.addAll(Arrays.stream(CardinalDirection.values())
                    .map(d -> p.add(d.velocity))
                    .filter((nbr) -> nbr.isInBoundariesInclusive(0, N - 1, 0, M - 1) && Integer.parseInt(String.valueOf(grid[p.x][p.y])) + 1 == Integer.parseInt(String.valueOf(grid[nbr.x][nbr.y])))
                    .toList()
            );
        }
        return rating;
    }
}


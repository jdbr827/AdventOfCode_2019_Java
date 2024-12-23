package year_2024.day_12;

import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class Day12 {
    Character[][] grid;
    int N;
    int M;
    int[][] perimeter;
    Map<CartesianPoint, CartesianPoint> parent = new HashMap<>();



    public Day12(String inputFilename) {
        grid = new AOCScanner(inputFilename).scanAsChar2DArray();
        N = grid.length;
        M = grid[0].length;
        perimeter = new int[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                perimeter[i][j] = 0;
            }
        }
    }

    public int totalFencingCost() {
        for (int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                for (CardinalDirection dir : CardinalDirection.values()) {
                    CartesianPoint nbr = new CartesianPoint(i, j).add(dir.velocity);
                    if (nbr.isInBoundariesInclusive(0, N-1, 0, M-1) && grid[i][j] == grid[nbr.x][nbr.y]) {
                        union(new CartesianPoint(i, j), nbr);
                    } else {
                        perimeter[i][j]++;
                    }
                }
            }
        }

        HashMap<CartesianPoint, Integer> area = new HashMap<>();
        HashMap<CartesianPoint, Integer> perimeterMap = new HashMap<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                CartesianPoint region = findParent(new CartesianPoint(i, j));
                area.merge(region, 1, Integer::sum);
                perimeterMap.merge(region, perimeter[i][j], Integer::sum);
            }
        }

        return area.keySet().stream()
                .map(r -> area.get(r) * perimeterMap.getOrDefault(r,0))
                .reduce(0, Math::addExact);
    }

    private void union(CartesianPoint p1, CartesianPoint p2) {
        CartesianPoint r1 = findParent(p1);
        CartesianPoint r2 = findParent(p2);
        if (!r1.equals(r2)) {
            CartesianPoint smaller = min(List.of(r1, r2), Comparator.comparing(cp -> (cp.getX() * N) + cp.getY()));
            CartesianPoint larger = max(List.of(r1, r2), Comparator.comparing(cp -> (cp.getX() * N) + cp.getY()));
            parent.put(p1, smaller);
            parent.put(larger, smaller);
            parent.put(p2, smaller);
        }
    }

    private CartesianPoint findParent(CartesianPoint p1) {
        CartesianPoint parent1 = parent.getOrDefault(p1, p1);
        if (!parent1.equals(p1)) {
            parent.put(p1, findParent(parent1));
            return parent.get(p1);
        }
        return p1;
    }

    private boolean sharesPlantType(CartesianPoint p, CartesianPoint nbr) {
        return nbr.isInBoundariesInclusive(0, N-1, 0, M-1) && grid[p.x][p.y] == grid[nbr.x][nbr.y];

    }

    public int totalFencingCostBulk() {
        for (int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                CartesianPoint p = new CartesianPoint(i, j);
                for (CardinalDirection dir : CardinalDirection.values()) {
                    CartesianPoint nbr = p.add(dir.velocity);
                    if (sharesPlantType(p, nbr)) {
                        union(p, nbr);
                    } else {
                        // Idea: we only want to count the perimeter on the top/right most element of the side.
                        perimeter[i][j]++;
                        if (dir.equals(CardinalDirection.NORTH) || dir.equals(CardinalDirection.SOUTH)) {
                            CartesianPoint westNbr = p.add(CardinalDirection.WEST.velocity);
                             if (sharesPlantType(p, westNbr) && !sharesPlantType(westNbr, westNbr.add(dir.velocity))) {
                                 perimeter[i][j]--;
                             }
                        } else {
                            CartesianPoint northNbr = p.add(CardinalDirection.NORTH.velocity);
                            if (sharesPlantType(p, northNbr) && !sharesPlantType(northNbr, northNbr.add(dir.velocity))) {
                                perimeter[i][j]--;
                            }
                        }
                    }
                }
            }
        }

        HashMap<CartesianPoint, Integer> area = new HashMap<>();
        HashMap<CartesianPoint, Integer> perimeterMap = new HashMap<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                CartesianPoint region = findParent(new CartesianPoint(i, j));
                area.merge(region, 1, Integer::sum);
                perimeterMap.merge(region, perimeter[i][j], Integer::sum);
            }
        }

        return area.keySet().stream()
                .map(r -> area.get(r) * perimeterMap.getOrDefault(r,0))
                .reduce(0, Math::addExact);
    }
}

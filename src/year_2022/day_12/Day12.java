package year_2022.day_12;

import utils.BFSUtil;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    static Long part1(String fileName) throws FileNotFoundException {
        Day12Matrix matrix = Day12Scanner.readInMatrix(fileName);

        // scan to find start and goal O(NM)
        CartesianPoint start = null; CartesianPoint goal = null;
        for (int i = 0; i< matrix.getXSize(); i++) {
            for (int j = 0; j< matrix.getYSize(); j++) {
                if (matrix.getValue(i, j).equals('S')) {start = new CartesianPoint(i, j);}
                else if (matrix.getValue(i, j).equals('E')) {goal = new CartesianPoint(i, j);}

                if (start != null && goal != null) {
                    break;
                }
            }
        }

        Function<CartesianPoint, Collection<CartesianPoint>> neighborFunction = (p ->
                Arrays.stream(CardinalDirection.values())
                        .map(d -> p.add(d.velocity))
                        .filter((nbr) -> matrix.containsPoint(nbr) && matrix.getRelativeHeight(nbr) - (matrix.getRelativeHeight(p)) <= 1)
                .collect(Collectors.toList()));


        return BFSUtil.doBFSToPoint(start, goal, neighborFunction);
    }


    public static long part2(String fileName) throws FileNotFoundException {
        Day12Matrix matrix = Day12Scanner.readInMatrix(fileName);
        
        
        CartesianPoint goal = null;
        Collection<CartesianPoint> elevationA = new ArrayList<>();
        // scan to find goal and all points with elevation a  - O(NM)
        for (int i = 0; i< matrix.getXSize(); i++) {
            for (int j = 0; j< matrix.getYSize(); j++) {
                if (matrix.getElevation(i, j).equals('a')) {
                    elevationA.add(new CartesianPoint(i, j));
                }
                else if (matrix.getValue(i, j).equals('E')) {
                    goal = new CartesianPoint(i, j);
                }
            }
        }

        Function<CartesianPoint, Collection<CartesianPoint>> neighborFunction = p ->
            Arrays.stream(CardinalDirection.values())
                    .map(d -> p.add(d.velocity))
                    .filter((nbr) -> matrix.containsPoint(nbr) && matrix.getRelativeHeight(nbr) - matrix.getRelativeHeight(p) >= -1)
                    .collect(Collectors.toList());

        Map<CartesianPoint, Long> bfsResult = BFSUtil.doBFSAnyDestination(goal, elevationA, neighborFunction);


        return elevationA.stream()
                .map((a) -> bfsResult.getOrDefault(a, Long.MAX_VALUE))
                .min(Comparator.naturalOrder())
                .get();

    }
}

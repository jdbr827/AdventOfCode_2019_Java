package year_2022.day_12;

import utils.BFSUtil;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    static Long part1(String fileName) throws FileNotFoundException {
        Day12Scanner scanner = new Day12Scanner(fileName);
        List<List<Character>> matrix = scanner.readInMatrix();
        //System.out.println(matrix.toString());
        int N = matrix.size();
        int M = matrix.get(0).size();
        Point start = null; Point goal = null;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (matrix.get(i).get(j).equals('S')) {
                    start = new Point(i, j);
                    matrix.get(i).set(j, 'a');
                }
                else if (matrix.get(i).get(j).equals('E')) {
                    goal = new Point(i, j);
                    matrix.get(i).set(j, 'z');
                }
            }
        }

        Function<Point, Collection<Point>> neighborFunction = (p -> Stream.of(
                        new Point(p.x + 1, p.y),
                        new Point(p.x - 1, p.y),
                        new Point(p.x, p.y + 1),
                        new Point(p.x, p.y - 1)
                ).filter((nbr) ->
                        nbr.getX() >= 0 && nbr.getX() < N && nbr.getY() >= 0 && nbr.getY() < M &&
                                (int) matrix.get(nbr.x).get(nbr.y) - (int) (matrix.get(p.x).get(p.y)) <= 1)
                .collect(Collectors.toList()));

        return BFSUtil.doBFS(start, goal, neighborFunction);
    }


    public static long part2(String fileName) throws FileNotFoundException {
        Day12Scanner scanner = new Day12Scanner(fileName);
        List<List<Character>> matrix = scanner.readInMatrix();
        int N = matrix.size();
        int M = matrix.get(0).size();
        Point start = null; Point goal = null;
        Collection<Point> elevationA = new java.util.ArrayList<>(List.of());
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (matrix.get(i).get(j).equals('a')) {
                    elevationA.add(new Point(i, j));
                }
                if (matrix.get(i).get(j).equals('S')) {
                    start = new Point(i, j);
                    matrix.get(i).set(j, 'a');
                    elevationA.add(start);
                }
                else if (matrix.get(i).get(j).equals('E')) {
                    goal = new Point(i, j);
                    matrix.get(i).set(j, 'z');
                }
            }
        }

        Function<Point, Collection<Point>> neighborFunction = (p -> {
            return Stream.of(
                            new Point(p.x + 1, p.y),
                            new Point(p.x - 1, p.y),
                            new Point(p.x, p.y + 1),
                            new Point(p.x, p.y - 1)
                    ).filter((nbr) -> nbr.getX() >= 0 && nbr.getX() < N && nbr.getY() >= 0 && nbr.getY() < M &&
                            (int) matrix.get(nbr.x).get(nbr.y) - (int) (matrix.get(p.x).get(p.y)) >= -1)
                    .collect(Collectors.toList());
        });

        Map<Point, Long> bfsResult = BFSUtil.doBFSAnyDestination(goal, elevationA, neighborFunction);

        long lowestSteps = Long.MAX_VALUE;
        for (Point a : elevationA) {
            lowestSteps = Math.min(bfsResult.getOrDefault(a, Long.MAX_VALUE), lowestSteps);
        }
        return lowestSteps;
    }
}

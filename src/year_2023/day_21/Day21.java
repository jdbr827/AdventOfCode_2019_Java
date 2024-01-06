package year_2023.day_21;

import javafx.util.Pair;
import utils.AOCScanner;
import utils.BFSUtil;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day21 {
    Character[][] garden;
    int N;
    int M;


    public Day21(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        garden = scanner.scanAsChar2DArray();
        N = garden.length;
        M = garden[0].length;
    }


    private CartesianPoint findOrigin() {
        int N = garden.length;
        int M = garden[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (garden[i][j] == 'S') {
                    return new CartesianPoint(i, j);
                }
            }
        }
        return null;
    }


    public long getSteps(long numSteps) {
        int N = garden.length;
        int M = garden[0].length;

        //find origin
        CartesianPoint origin = findOrigin();

        Function<CartesianPoint, Collection<CartesianPoint>> neighborFunction = (p ->
                Arrays.stream(CardinalDirection.values())
                        .map(d -> p.add(d.velocity))
                        .filter((nbr) -> nbr.isInBoundariesInclusive(0, N - 1, 0, M - 1) && garden[nbr.x][nbr.y] != '#')
                        .collect(Collectors.toList()));

        List<CartesianPoint> pointsIStepsAway = List.of(origin);
        List<CartesianPoint> pointsIPlusOneStepsAway = new ArrayList<>();
        for (int i = 0; i < numSteps; i++) {
            pointsIPlusOneStepsAway = new ArrayList<>(pointsIStepsAway.stream()
                    .map(neighborFunction)
                    .reduce(new HashSet<>(), (acc, elm) -> {
                        acc.addAll(elm);
                        return acc;
                    }));

            pointsIStepsAway = pointsIPlusOneStepsAway;
        }

        return pointsIStepsAway.size();
    }

    public int getStepsInfiniteMap(int numSteps) {

        //find origin
        CartesianPoint origin = findOrigin();


        Function<CartesianPoint, Collection<CartesianPoint>> neighborFunction = (p ->
                Arrays.stream(CardinalDirection.values())
                        .map(d -> p.add(d.velocity))
                        .filter((nbr) -> garden[positiveModulus(nbr.x, N)][positiveModulus(nbr.y, M)] != '#')
                        .collect(Collectors.toList()));

        List<CartesianPoint> pointsIStepsAway = List.of(origin);
        List<CartesianPoint> pointsIPlusOneStepsAway;
        Map<CartesianPoint, Integer> shortestOddLengthDistance = new HashMap<>();
        Map<CartesianPoint, Integer> shortestEvenLengthDistance = new HashMap<>();
        shortestEvenLengthDistance.put(origin, 0);
        for (int i = 1; i <= numSteps; i++) {
            pointsIPlusOneStepsAway = new ArrayList<>();
            for (CartesianPoint v : pointsIStepsAway) {
                for (CartesianPoint nbr : neighborFunction.apply(v)) {
                    if (i % 2 == 1) {
                        if (!shortestOddLengthDistance.containsKey(nbr)) {
                            shortestOddLengthDistance.put(nbr, i);
                            pointsIPlusOneStepsAway.add(nbr);
                        }
                    } else {
                        if (!shortestEvenLengthDistance.containsKey(nbr)) {
                            shortestEvenLengthDistance.put(nbr, i);
                            pointsIPlusOneStepsAway.add(nbr);
                        }
                    }
                }
            }
            pointsIStepsAway = pointsIPlusOneStepsAway;
        }
        return shortestEvenLengthDistance.size();
    }

    int positiveModulus(int num, int mod) {
        int remainder = num % mod;
        if (remainder < 0) {
            remainder += mod;
        }
        return remainder;
    }

}


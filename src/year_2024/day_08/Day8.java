package year_2024.day_08;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;

import java.util.*;

public class Day8 {
    int N;
    int M;
    Map<Character, List<Pair<Integer, Integer>>> antennaLocations = new HashMap<>();

    public Day8(String inputFilename) {
        Character[][] grid = new AOCScanner(inputFilename).scanAsChar2DArray();
        N = grid.length;
        M = grid[0].length;

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (grid[i][j] != '.') {
                    antennaLocations.putIfAbsent(grid[i][j], new ArrayList<>());
                    antennaLocations.get(grid[i][j]).add(Pair.of(i, j));
                }
            }
        }
    }

    public int numLocationsWithAntiNodes() {
        Set<Pair<Integer,Integer>> locationsWithAntiNodes = new HashSet<>();
        for (List<Pair<Integer, Integer>> antennaGroup: antennaLocations.values()) {
            for(int i=0; i < antennaGroup.size(); i++) {
                for (int j=i+1; j<antennaGroup.size(); j++) {
                    Pair<Integer, Integer> antiNode = getAntiNodeLocation(antennaGroup.get(i), antennaGroup.get(j));
                    if (isInBounds(antiNode)) {
                        locationsWithAntiNodes.add(antiNode);
                    }
                    antiNode = getAntiNodeLocation(antennaGroup.get(j), antennaGroup.get(i));
                    if (isInBounds(antiNode)) {
                        locationsWithAntiNodes.add(antiNode);
                    }
                }
            }
        }
        return locationsWithAntiNodes.size();
    }

    private Pair<Integer, Integer> getAntiNodeLocation(Pair<Integer, Integer> closerAntenna, Pair<Integer, Integer> furtherAntenna) {
        return Pair.of((closerAntenna.first() * 2) - furtherAntenna.first(), (closerAntenna.second() * 2) - furtherAntenna.second());
    }

    private boolean isInBounds(Pair<Integer, Integer> antiNodeLocation) {
        return antiNodeLocation.first() >= 0 && antiNodeLocation.first() < N && antiNodeLocation.second() >= 0 && antiNodeLocation.second() < M;
    }

}

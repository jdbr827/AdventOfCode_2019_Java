package year_2022.day_16;

import com.google.common.collect.Sets;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day16 {
    public static final int TIME_AVAILABLE = 30;

    public static String part1(String fileName) throws FileNotFoundException {
        Day16Scanner myScanner = new Day16Scanner(fileName);

        Collection<Valve> valveGraph = myScanner.scanAll();

        // We know we never want to close an open pipe, and we never want to waste time opening a pipe with flow 0
        Set<Valve> nonzeroFlowValves = valveGraph.stream()
                .filter(valve -> valve.getFlowValue() > 0)
                .collect(Collectors.toSet());

        List<Set<Valve>> valvePowerSet = new ArrayList<>(Sets.powerSet(nonzeroFlowValves));
        //System.out.println(valvePowerSet);

        /*
        DpMatrix[C, t, s] := max flow available given C are only nonzeroflow valves that are closed, you have t time left, and you are at valve s
        */

        Map<Set<Valve>, Map<Integer, Map<Valve, Integer>>> dpMatrix = new HashMap<>();

        for (Set<Valve> set : valvePowerSet) {
            dpMatrix.put(set, new HashMap<Integer, Map<Valve, Integer>>());
            for (int t = 0; t < TIME_AVAILABLE; t++) {
                dpMatrix.get(set).put(t, new HashMap<Valve, Integer>());
            }
        }

        for (Set<Valve> set : valvePowerSet) {
            for (Valve v : valveGraph) {
                dpMatrix.get(set).get(0).put(v, 0);
            }
        }


        Set<Valve> emptySet = valvePowerSet.stream().filter(Set::isEmpty).findAny().get();

        for (int t=0; t<TIME_AVAILABLE; t++) {
            for (Valve currentPosition: valveGraph) {
                Map<Integer, Map<Valve, Integer>> emptySetMap = dpMatrix.get(emptySet);
                emptySetMap.get(t).put(currentPosition, 0);
            }
        }

        List<Set<Valve>> sortedValvePowerSet = valvePowerSet.stream().sorted((set1, set2) -> {
            if (set1.size() - set2.size() == 0) {
                return 0;
            }
            return set1.size() > set2.size() ? 1 : -1;
        }).collect(Collectors.toList());
        System.out.println(sortedValvePowerSet.toString());








        return valvePowerSet.toString();

    }
}

package year_2022.day_16;

import com.google.common.collect.Sets;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static year_2022.day_16.Valve.lookupByName;

public class Day16 {

    public static Integer part1(String fileName, int time_available) throws FileNotFoundException {
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
            for (int t = 1; t <= time_available; t++) {
                dpMatrix.get(set).put(t, new HashMap<Valve, Integer>());
            }
        }

        for (Set<Valve> set : valvePowerSet) {
            for (Valve v : valveGraph) {
                dpMatrix.get(set).get(1).put(v, 0);
            }
        }


        Set<Valve> emptySet = valvePowerSet.stream().filter(Set::isEmpty).findAny().get();

        for (int t=1; t<=time_available; t++) {
            for (Valve currentPosition: valveGraph) {
                Map<Integer, Map<Valve, Integer>> emptySetMap = dpMatrix.get(emptySet);
                emptySetMap.get(t).put(currentPosition, 0);
            }
        }

//        List<Set<Valve>> sortedValvePowerSet = valvePowerSet.stream()..sorted((set1, set2) -> {
//            if (set1.size() - set2.size() == 0) {
//                return 0;
//            }
//            return set1.size() > set2.size() ? 1 : -1;
//        }).collect(Collectors.toList());

        Map<Integer, List<Set<Valve>>> valvePowerSetBySize = valvePowerSet.stream().collect(Collectors.groupingBy(Set::size));

        for (int time_remaining=2; time_remaining<=time_available; time_remaining++) {
            for (int setSize=0; setSize<nonzeroFlowValves.size() + 1; setSize++) {
                for (Set<Valve> s : valvePowerSetBySize.get(setSize)) {
                    for (Valve currentPosition : valveGraph) {
                        int res = 0;
                        if (s.contains(currentPosition)) {
                            Set<Valve> setWithoutCurrentPosition = Sets.difference(s, Set.of(currentPosition));
                            res = dpMatrix.get(setWithoutCurrentPosition).get(time_remaining-1).get(currentPosition) + (time_remaining-1) * currentPosition.getFlowValue();
                        }

                        for (Valve neighbor : currentPosition.getNeighbors()) {
                            res = Math.max(res, dpMatrix.get(s).get(time_remaining-1).get(neighbor));
                        }

                        dpMatrix.get(s).get(time_remaining).put(currentPosition, res);
                    }
                }
            }
        }

        return dpMatrix.get(nonzeroFlowValves).get(time_available).get(lookupByName.get("AA"));

    }
}

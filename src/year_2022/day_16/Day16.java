package year_2022.day_16;

import com.google.common.collect.Sets;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static year_2022.day_16.Valve.lookupByName;

public class Day16 {
    List<Valve> valveGraph;
    int time_available;

    private Integer[][][] dpMatrix;

    Day16(List<Valve> valveGraph, int time_available) {
        this.valveGraph = valveGraph;
        this.time_available = time_available;
    }


//    private Integer dpMatrixGet(Set<Valve> unOpenedNonZeroFlowValves, int time_remaining, Valve currentPosition) {
//        if (time_remaining == 1) {
//            return 0;
//        } if (unOpenedNonZeroFlowValves.size() == 0) {
//            return 0;
//        }
//        return dpMatrix[]
//    }
//
//    private void dpMatrixPut(Set<Valve> unOpenedNonZeroFlowValves, int time_remaining, Valve currentPosition, int maxFlowAvailable) {
//
//        dpMatrix.putIfAbsent(unOpenedNonZeroFlowValves, new HashMap<>());
//        dpMatrix.get(unOpenedNonZeroFlowValves).putIfAbsent(time_remaining, new HashMap<>());
//        dpMatrix.get(unOpenedNonZeroFlowValves).get(time_remaining).put(currentPosition, maxFlowAvailable);
//    }




    public static Integer part1(String fileName, int time_available) throws FileNotFoundException {
        Day16Scanner myScanner = new Day16Scanner(fileName);
        List<Valve> valveGraph = myScanner.scanAll();
        return new Day16(valveGraph, time_available).findMaxFlowInTime();
    }



    Integer findMaxFlowInTime() {
        // We know we never want to close an open pipe, and we never want to waste time opening a pipe with flow 0
        List<Valve> sortedValveGraph = valveGraph.stream()
                .sorted(Comparator.comparing(Valve::getFlowValue).reversed())
                .collect(Collectors.toList());
        for (int i=0 ; i< sortedValveGraph.size(); i++) {
            sortedValveGraph.get(i).index = i;
        }

        System.out.println(sortedValveGraph);

        List<Valve> nonzeroFlowValves = sortedValveGraph.stream().takeWhile(valve -> valve.getFlowValue() > 0).collect(Collectors.toList());

        /*
        DpMatrix[C, t, s] := max flow available given C are only nonzeroflow valves that are closed, you have t time left, and you are at valve s
        */
        int powersetSize = (int)(Math.pow(2, nonzeroFlowValves.size()));

        initializeDPMatrix(powersetSize);

//        System.out.println(Sets.powerSet(nonzeroFlowValves));


//        Map<Integer, List<Set<Valve>>> valvePowerSetBySize = Sets.powerSet(nonzeroFlowValves).stream()
//                        .collect(Collectors.groupingBy(Set::size));

        System.out.println("Hi");

        for (int time_remaining=2; time_remaining<=time_available; time_remaining++) {
//            for (int setSize=0; setSize<nonzeroFlowValves.size() + 1; setSize++) {
//                for (Set<Valve> s : valvePowerSetBySize.get(setSize)) {
            for (int c=1; c<Math.pow(2, nonzeroFlowValves.size()); c++) {
                StringBuilder binaryStringBuilder = new StringBuilder(Integer.toBinaryString(c));
                while(binaryStringBuilder.length() < powersetSize) {
                    binaryStringBuilder.insert(0, '0');
                }
                String binaryString = binaryStringBuilder.toString();
                for (int i=0; i<valveGraph.size(); i++) {
                    int powVal = (int) Math.pow(2, i);
                    Valve currentPosition = sortedValveGraph.get(i);
                    if (binaryString.charAt(powersetSize - i - 1) == '1') {
                        dpMatrix[c][time_remaining][i] = dpMatrix[c - powVal][time_remaining-1][i] + (time_remaining-1)*currentPosition.getFlowValue();
                    }
                    Integer[][] subMatrix = dpMatrix[c];
                    Integer[] subsubMatrix = subMatrix[time_remaining];
                    //System.out.println(Arrays.toString(subsubMatrix));
                    int res = subsubMatrix[i];
                    for (Valve neighbor : currentPosition.getNeighbors()) {
                        res = Math.max(res, dpMatrix[c][time_remaining-1][neighbor.index]);
                    }
                    dpMatrix[c][time_remaining][i] = res;




//                    for (Valve closedPosition : s) {
//                        Set<Valve> setWithoutCurrentPosition = Sets.difference(s, Set.of(closedPosition));
//                        int ifIOpenedIt = dpMatrixGet(setWithoutCurrentPosition, time_remaining-1, closedPosition) + (time_remaining-1) * closedPosition.getFlowValue();
//                        dpMatrixPut(s, time_remaining, closedPosition, ifIOpenedIt);
//                    }

//                    for (Valve currentPosition : valveGraph) {
//                        int res = dpMatrixGet(s, time_remaining, currentPosition);
//                        for (Valve neighbor : currentPosition.getNeighbors()) {
//                            res = Math.max(res, dpMatrixGet(s, time_remaining-1, neighbor));
//                        }
//
//                        dpMatrixPut(s, time_remaining, currentPosition, res);
//
//                    }
                }
            }
        }

        return dpMatrix[powersetSize - 1][time_available][lookupByName.get("AA").index];
    }

    // watch out for int/long problems
    private void initializeDPMatrix(int powerSetSize) {
        dpMatrix = new Integer[powerSetSize][time_available+1][valveGraph.size()];
        for (int i=0; i<powerSetSize; i++) {
            for (int j=0; j<time_available+1; j++) {
                for (int k=0; k<valveGraph.size(); k++) {
                    dpMatrix[i][j][k] = 0;
                }
            }
        }
    }

}

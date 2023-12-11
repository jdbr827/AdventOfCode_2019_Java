package year_2023.day_08;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Day8 {
    Map<String, Map<Character, String>> desertMap;
    String instructions;

    int countStepsToZZZ() {
        String location = "AAA";
        int instructionIdx = 0;
        int nSteps = 0;
        while (!(location.equals("ZZZ"))) {
            location = desertMap.get(location).get(instructions.charAt(instructionIdx));
            instructionIdx = (instructionIdx + 1) % instructions.length();
            nSteps++;
        }
        return nSteps;
    }


     int countStepsToZZZGhost2() {
        List<String> location = desertMap.keySet().stream().filter(key -> key.charAt(2) == 'A').collect(Collectors.toList());
        int instructionIdx = 0;
        int nSteps = 0;
        Predicate<String> endCondition = key -> key.charAt(2) == 'Z';
        while (!location.stream().allMatch(endCondition)) {
            Character instruction = instructions.charAt(instructionIdx);
            location = location.stream().map(l -> desertMap.get(l).get(instruction)).collect(Collectors.toList());
            instructionIdx = (instructionIdx + 1) % instructions.length();
            nSteps++;
        }
        return nSteps;
    }


    class DesertGhost {
        String startLocation;
        int period;
        List<Integer> offsetsWhereYouEndAtZ = new ArrayList<>();
        int cycleStartStep;

        @Getter
        long lowestPossibleSolution = 0;


        int offsetPointer = 0;
        int timesPeriodUsed = 0;

        DesertGhost(String startLocation) {
            this.startLocation = startLocation;
            determineCycleInfo();
        }

        void determineCycleInfo() {
            List<String> locationsVisited = new java.util.ArrayList<>();
            String currentLocation = startLocation;
            Map<String, Map<Integer, Integer>> visitedLocationOnInstructionAtStep = new HashMap<>();
            int instructionIdx = 0;
            int nSteps = 0;
            while (!visitedLocationOnInstructionAtStep.getOrDefault(currentLocation, new HashMap<>()).containsKey(instructionIdx)) {
                visitedLocationOnInstructionAtStep.putIfAbsent(currentLocation, new HashMap<>());
                visitedLocationOnInstructionAtStep.get(currentLocation).put(instructionIdx, nSteps);
                locationsVisited.add(currentLocation);
                currentLocation = desertMap.get(currentLocation).get(instructions.charAt(instructionIdx));
                instructionIdx++;
                if (instructionIdx >= instructions.length()) {
                    instructionIdx = 0;
                }
                nSteps++;
            }

            this.cycleStartStep = visitedLocationOnInstructionAtStep.get(currentLocation).get(instructionIdx);
            this.period = nSteps - cycleStartStep;

            List<Boolean> isOffsetLocationEndWithZ = locationsVisited.subList(cycleStartStep, cycleStartStep + period).stream().map(key -> key.charAt(2) == 'Z').collect(Collectors.toList());
            for (int i=0; i<period; i++) {
                if (isOffsetLocationEndWithZ.get(i)) {
                    offsetsWhereYouEndAtZ.add(i);
                }
            }

            lowestPossibleSolution = cycleStartStep + (long) timesPeriodUsed * period + offsetsWhereYouEndAtZ.get(offsetPointer);
        }


        void computeLowestPossibleSolution() {
          lowestPossibleSolution = cycleStartStep + (long) timesPeriodUsed * period + offsetsWhereYouEndAtZ.get(offsetPointer);
        }

        void updateLowestPossibleSolution() {
            offsetPointer++;
            if (offsetPointer >= offsetsWhereYouEndAtZ.size()) {
                timesPeriodUsed += 1;
                offsetPointer = 0;
            }
            computeLowestPossibleSolution();
        }
    }


       long countStepsToZZZGhost() {
        Collection<DesertGhost> ghosts = desertMap.keySet().stream()
                .filter(key -> key.charAt(2) == 'A')
                .map(DesertGhost::new)
                .collect(Collectors.toList());

        while (true) {
            DesertGhost smallestGhost = ghosts.stream().min(Comparator.comparing(DesertGhost::getLowestPossibleSolution)).get();
            long largestLowest = ghosts.stream().map(DesertGhost::getLowestPossibleSolution).max(Comparator.naturalOrder()).get();
            if (smallestGhost.getLowestPossibleSolution() == largestLowest) {
                return smallestGhost.getLowestPossibleSolution();
            }
            while (smallestGhost.getLowestPossibleSolution() < largestLowest) {
                smallestGhost.updateLowestPossibleSolution();
            }
        }
    }
}

package year_2024.day_07;

import org.testng.internal.collections.Pair;

import java.util.*;
import java.util.function.BiFunction;

public class Day7 {
    Collection<Pair<Long, List<Long>>> equations;
    public Day7(String inputFilename) {
        equations = new Day7Scanner(inputFilename).scan();
    }

    public long totalCalibrationResult() {
        return equations.stream().filter(this::isSolvable).map(Pair::first).reduce(0L, Math::addExact);
    }

    private boolean isSolvable(Pair<Long, List<Long>> longListPair) {
        long desiredResult = longListPair.first();
        List<Long> parameters = longListPair.second();
        int numOps = parameters.size()-1;
        for (long i=0; i<Math.pow(2, numOps); i++) {
            long tot = parameters.getFirst();
            for (int j=0; j<numOps; j++) {
                if ((((i>>j)&1) == 1)){
                    tot *= parameters.get(j+1);
                } else {
                    tot += parameters.get(j+1);
                }
            }
            if (tot == desiredResult) {
                return true;
            }
        }
        return false;


    }

    public long totalCalibrationResultWithConcat() {
        return equations.stream().filter(this::isSolvableWithConcat).map(Pair::first).reduce(0L, Math::addExact);
    }

    private boolean isSolvableWithConcat(Pair<Long, List<Long>> longListPair) {
        long desiredResult = longListPair.first();
        List<Long> parameters = longListPair.second();
        int numOps = parameters.size()-1;

        List<Character> operators = new ArrayList<Character>(Collections.nCopies(numOps, '+'));
        for (long i=0; i < Math.pow(3, numOps); i++) {
            long tot = parameters.getFirst();
            for (int j=0; j<numOps; j++) {
                if (operators.get(j).equals('+')) {
                    tot += parameters.get(j+1);
                } else if (operators.get(j).equals('*')) {
                    tot *= parameters.get(j+1);
                } else {
                    tot = Long.parseLong(tot + String.valueOf(parameters.get(j+1)));
                }
            }
            if (tot == desiredResult) {
                return true;
            }
            for(int j=numOps-1; j>=0; j--) {
                if (operators.get(j).equals('+')) {
                    operators.set(j, '*');
                    break;
                } else if (operators.get(j).equals('*')) {
                    operators.set(j, '|');
                    break;
                } else {
                    operators.set(j, '+');
                }
            }
        }
        return false;
    }
}

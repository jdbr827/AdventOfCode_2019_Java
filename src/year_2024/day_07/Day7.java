package year_2024.day_07;

import org.testng.internal.collections.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class Day7 {
    Collection<Pair<Long, List<Long>>> equations;
    public Day7(String inputFilename) {
        equations = new Day7Scanner(inputFilename).scan();
    }

//    public static final Map<Character, BiFunction<Long, Long, Long>> operationMap = Map.of(
//            '+', Math::addExact,
//            '*', Math::multiplyExact,
//    );

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
}

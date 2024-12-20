package year_2024.day_03;

import org.testng.internal.collections.Pair;

import java.util.List;

import static year_2024.day_03.Day3Scanner.DONT_OPERATION;
import static year_2024.day_03.Day3Scanner.DO_OPERATION;

public class Day3 {

    List<Pair<Integer, Integer>> operations;

    public Day3(String smallInputFilename) {
        operations = new Day3Scanner(smallInputFilename).scan();
    }

    public int sumValidOps() {
        return operations.stream()
                .filter(pr -> pr.first() > 0) // remove all DO and DON'T operations
                .map(pr -> pr.first() * pr.second())
                .reduce(0, Math::addExact);
    }

    public int sumAllowedValidOps() {
        boolean enabled = true;
        int total = 0;
        for (Pair<Integer, Integer> operation : operations) {
            if (operation.equals(DO_OPERATION)) {
                enabled = true;
            } else if (operation.equals(DONT_OPERATION)) {
                enabled = false;
            } else if (enabled) {
                total += operation.first() * operation.second();
            }
        }
        return total;
    }
}

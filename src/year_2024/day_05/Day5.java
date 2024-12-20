package year_2024.day_05;

import lombok.AllArgsConstructor;
import org.testng.internal.collections.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Day5 {
    List<Pair<Integer, Integer>> rules;
    List<List<Integer>> proposedUpdates;

    public static Day5 fromScanner(String inputFilename) {
        return new Day5Scanner(inputFilename).scan();
    }

    // Overall Part 1 = O(N^2 * R * U). Yuck
    public int sumOfMiddlePagesOfCorrectlyOrderedUpdates() {
        return proposedUpdates.stream()
                .filter(this::isUpdateCorrectlyOrdered) // O(U)
                .map(this::getMiddleElement)
                .reduce(0, Math::addExact);
    }

    private Pair<Integer, Integer> getFirstViolatedRule(List<Integer> proposedUpdate) {
        for (int i=0; i< proposedUpdate.size(); i++) { // O(N)
            for (int j=i+1; j< proposedUpdate.size(); j++) { // O(N)
                for (Pair<Integer, Integer> rule : rules) { // O(R)
                    if (rule.equals(Pair.of(proposedUpdate.get(j), proposedUpdate.get(i)))) {
                        return Pair.of(i, j);
                    }
                }
            }
        }
        return null;
    }

    private boolean isUpdateCorrectlyOrdered(List<Integer> proposedUpdate) {
        return getFirstViolatedRule((proposedUpdate)) == null;
    }

    private Integer getMiddleElement(List<Integer> proposedUpdate) {
        int n = proposedUpdate.size();
        return proposedUpdate.get((n-1)/ 2);
    }

    public int sumOfMiddlePagesOfCorrectedUpdates() {
        int tot = 0;
        for (List<Integer> update : proposedUpdates) {
            ArrayList<Integer> mutableUpdate = new ArrayList<>(update);
            Pair<Integer, Integer> violation;
            if ((violation = getFirstViolatedRule(mutableUpdate)) != null) {
                while (violation != null) {
                    int i = violation.first();
                    int j = violation.second();
                    Collections.swap(mutableUpdate, i, j);
                    violation = getFirstViolatedRule(mutableUpdate);
                }
                tot += getMiddleElement(mutableUpdate);
            }
        }
        return tot;
    }
}

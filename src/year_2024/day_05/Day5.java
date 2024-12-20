package year_2024.day_05;

import lombok.AllArgsConstructor;
import org.testng.internal.collections.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class Day5 {
    Set<Pair<Integer, Integer>> rules;
    List<List<Integer>> proposedUpdates;

    public static Day5 fromScanner(String inputFilename) {
        return new Day5Scanner(inputFilename).scan();
    }

    // Overall Part 1 = O(N^2 * U).
    public int sumOfMiddlePagesOfCorrectlyOrderedUpdates() {
        return proposedUpdates.stream()
                .filter(this::isUpdateCorrectlyOrdered) // O(U)
                .map(this::getMiddleElement)
                .reduce(0, Math::addExact);
    }

    private Pair<Integer, Integer> getFirstViolatedRule(List<Integer> proposedUpdate) {
        for (int i=0; i< proposedUpdate.size(); i++) { // O(N)
            for (int j=i+1; j< proposedUpdate.size(); j++) { // O(N)
                if (rules.contains(Pair.of(proposedUpdate.get(j), proposedUpdate.get(i)))) { // O(1)
                        return Pair.of(i, j);
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


    // Assuming I incorrect updates with average of E errors, part 2 becomes O(I*E*N^2)
    public int sumOfMiddlePagesOfCorrectedUpdates() {
        int tot = 0;
        for (List<Integer> update : proposedUpdates) {
            ArrayList<Integer> mutableUpdate = new ArrayList<>(update);
            Pair<Integer, Integer> violation;
            if ((violation = getFirstViolatedRule(mutableUpdate)) != null) {
                while (violation != null) {
                    Collections.swap(mutableUpdate, violation.first(), violation.second());
                    violation = getFirstViolatedRule(mutableUpdate);
                }
                tot += getMiddleElement(mutableUpdate);
            }
        }
        return tot;
    }
}

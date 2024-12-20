package year_2024.day_05;

import lombok.AllArgsConstructor;
import org.testng.internal.collections.Pair;

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

    private boolean isUpdateCorrectlyOrdered(List<Integer> proposedUpdate) {
        for (int i=0; i< proposedUpdate.size(); i++) { // O(N)
            for (int j=i+1; j< proposedUpdate.size(); j++) { // O(N)
                for (Pair<Integer, Integer> rule : rules) { // O(R)
                    if (rule.equals(Pair.of(proposedUpdate.get(j), proposedUpdate.get(i)))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Integer getMiddleElement(List<Integer> proposedUpdate) {
        int n = proposedUpdate.size();
        return proposedUpdate.get((n-1)/ 2);
    }
}

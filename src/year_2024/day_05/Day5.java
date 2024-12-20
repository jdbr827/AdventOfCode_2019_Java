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

    public int sumOfMiddlePagesOfCorrectlyOrderedUpdates() {
        return proposedUpdates.stream()
                .filter(this::isUpdateCorrectlyOrdered)
                .map(this::getMiddleElement)
                .reduce(0, Math::addExact);
    }

    private boolean isUpdateCorrectlyOrdered(List<Integer> proposedUpdate) {
    }

    private Integer getMiddleElement(List<Integer> proposedUpdate) {
        int n = proposedUpdate.size();
        return proposedUpdate.get((n-1)/ 2);
    }
}

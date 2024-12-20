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
        System.out.println(rules);
        return 0;
    }
}

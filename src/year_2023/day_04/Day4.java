package year_2023.day_04;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Day4 {
    List<Day4Scratchcard> scratchcards;

    static int day_4_part_1_2023(String filename) {
        return new Day4Scanner(filename).scan().determineTotalScratchCardValue();
    }

    public static int day_4_part_2_2023(String filename) {
        return new Day4Scanner(filename).scan().determineTotalNumberOfScratchcards();
    }

    int determineTotalScratchCardValue() {
        return scratchcards.stream().map(Day4Scratchcard::findValue).reduce(0, Math::addExact);
    }

    private int determineTotalNumberOfScratchcards() {
        int n = scratchcards.size();
        int[] cardsGeneratedByCard = new int[n];                                    // O(N) space
        for (int i=n-1; i>=0; i--) {                                                // O(N) time
            cardsGeneratedByCard[i] = 1;
            for (int m=1; m <= scratchcards.get(i).findNumMatches(); m++) {         // O(N) time + time for findNumMatches worst case, way less practical
                cardsGeneratedByCard[i] += cardsGeneratedByCard[i+m];
            }
        }
        return Arrays.stream(cardsGeneratedByCard).sum();
    }

}


class Day4Scanner extends AOCScanner {

    public Day4Scanner(String fileName) {
        super(fileName);
    }

    Day4 scan() {
        List<Day4Scratchcard> scratchcards = new ArrayList<>();
        forEachLine(line -> scratchcards.add(scanLine(line)));
        return new Day4(scratchcards);
    }

    private Day4Scratchcard scanLine(String line) {
        String[] numbers = line.split(":\\s+")[1].split("\\s+\\|\\s+");

        List<Integer> winningNumbers = Arrays.stream(numbers[0].split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> yourNumbers = Arrays.stream(numbers[1].split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        return new Day4Scratchcard(winningNumbers, yourNumbers);
    }
}

@RequiredArgsConstructor
class Day4Scratchcard {
    @NonNull List<Integer> winningNumbers;
    @NonNull List<Integer> yourNumbers;

    int findNumMatches() {
        int matches = 0;
        for (Integer number : yourNumbers) {
            if (winningNumbers.contains(number)) {
                matches += 1;
            }
        }
        return matches;
    }

    int findValue() {
        int matches = findNumMatches();
        if (matches == 0) {
            return 0;
        }
        return (int) Math.pow(2, matches - 1);
    }


}
package year_2023.day_04;

import lombok.AllArgsConstructor;
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

    int determineTotalScratchCardValue() {
        return scratchcards.stream().map(Day4Scratchcard::findValue).reduce(0, Math::addExact);
    }
}


class Day4Scanner extends AOCScanner {

    public Day4Scanner(String fileName) {
        super(fileName);
    }

    Day4 scan() {
        List<Day4Scratchcard> scratchcards = new ArrayList<>();
        while(scanner.hasNextLine()) {
            scratchcards.add(scanLine(scanner.nextLine()));
        }
        return new Day4(scratchcards);
    }

    private Day4Scratchcard scanLine(String line) {
        String[] numbers = line.split(":\\s+")[1].split("\\s+\\|\\s+");

        List<Integer> winningNumbers = Arrays.stream(numbers[0].split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> yourNumbers = Arrays.stream(numbers[1].split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        return new Day4Scratchcard(winningNumbers, yourNumbers);
    }
}

@AllArgsConstructor
class Day4Scratchcard {
    List<Integer> winningNumbers;
    List<Integer> yourNumbers;

    int findMatches() {
        int matches = 0;
        for (Integer number : yourNumbers) {
            if (winningNumbers.contains(number)) {
                matches += 1;
            }
        }
        return matches;
    }

    int findValue() {
        int matches = findMatches();
        if (matches == 0) {
            return 0;
        }
        return (int) Math.pow(2, matches - 1);
    }


}
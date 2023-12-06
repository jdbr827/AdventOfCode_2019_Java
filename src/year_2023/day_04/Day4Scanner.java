package year_2023.day_04;

import utils.AOCScanner;
import utils.AOCScanner_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day4Scanner {

    public static Day4 scan(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        List<Day4Scratchcard> scratchcards = new ArrayList<>();
        scanner.forEachLine(line -> scratchcards.add(scanLine(line)));
        return new Day4(scratchcards);
    }

    private static Day4Scratchcard scanLine(String line) {
        String[] numbers = line.split(":\\s+")[1].split("\\s+\\|\\s+");

        List<Integer> winningNumbers = Arrays.stream(numbers[0].split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> yourNumbers = Arrays.stream(numbers[1].split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        return new Day4Scratchcard(winningNumbers, yourNumbers);
    }
}

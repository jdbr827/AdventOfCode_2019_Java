package year_2023.day_07;

import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Collection;

public class Day7Scanner {


    public static Collection<CamelCardsGame> scan(String filename, boolean jokers) {
        AOCScanner scanner = new AOCScanner(filename);

        Collection<CamelCardsGame> games = new ArrayList<>();
        scanner.forEachLine((line) -> {
            String[] splitLine = line.split(" ");

            String hand = splitLine[0];
            int bid = Integer.parseInt(splitLine[1]);
            games.add(new CamelCardsGame(hand, bid, jokers));
        });

        return games;

    }

}

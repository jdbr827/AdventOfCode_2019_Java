package year_2023.day_09;

import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Day9Scanner {


    public static Day9 scan(String filename) {

        AOCScanner scanner = new AOCScanner(filename);

        Collection<Day9Line> day9LineCollection = new ArrayList<>();
        scanner.forEachLine((line) -> {

            day9LineCollection.add(new Day9Line(
                    Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList())));
        });

        return new Day9(day9LineCollection);
    }
}

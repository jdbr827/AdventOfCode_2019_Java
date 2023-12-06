package year_2023.day_05;

import utils.AOCScanner;
import utils.AOCScanner_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day5Scanner {


    public static Day5 scan(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        String seedsLine = scanner.nextLine();

        List<Long> seeds = Arrays.stream(seedsLine.split(": ")[1].split("\\s+")).map(Long::parseLong).collect(Collectors.toList());
        scanner.nextLine(); // empty;

        String line;

        List<AlmanacMap> almanac = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String title_line = scanner.nextLine();
            String[] dehyphenated = title_line.split("\\s+")[0].split("-");
            assert Objects.equals(dehyphenated[1], "to");
            String sourceCategory = dehyphenated[0];
            String destinationCategory = dehyphenated[2];
            List<AlmanacMapRule> rules = new ArrayList<>();

            while (scanner.hasNextLine() && !Objects.equals(line = scanner.nextLine(), "")) {
                List<Long> thisRule = Arrays.stream(line.split("\\s+")).map(Long::parseLong).collect(Collectors.toList());
                AlmanacMapRule rule = new AlmanacMapRule(thisRule.get(0), thisRule.get(1), thisRule.get(2));
                rules.add(rule);
            }

            AlmanacMap map = new AlmanacMap(sourceCategory, destinationCategory, rules);
            almanac.add(map);
        }

        return new Day5(seeds, almanac);
    }
}

package year_2024.day_05;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day5Scanner extends AOCScanner  {

    public Day5Scanner(String fileName) {
        super(fileName);
    }

    public Day5 scan() {
        List<Pair<Integer, Integer>> rules = new LinkedList<>();
        List<List<Integer>> proposedUpdates = new LinkedList<>();

        String thisLine;
        while (!(thisLine =scanner.nextLine()).isEmpty()) {
            String[] splt = thisLine.split("\\|");
            rules.add(Pair.of(Integer.parseInt(splt[0]), Integer.parseInt(splt[1])));
        }
        while (scanner.hasNextLine()) {
            proposedUpdates.add(Arrays.stream(scanner.nextLine().split(",")).map(Integer::parseInt).toList());
        }
        return new Day5(rules, proposedUpdates);

    }
}

package year_2024.day_02;

import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Scanner extends AOCScanner {

    public Day2Scanner(String fileName) {
        super(fileName);
    }

    public List<List<Integer>> scan() {
        List<List<Integer>> reports = new ArrayList<>();
        while (scanner.hasNextLine()) {
            reports.add(Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).toList());
        }
        return reports;
    }
}

package year_2024.day_07;

import org.testng.internal.collections.Pair;

import java.util.Collection;
import java.util.List;

public class Day7 {
    Collection<Pair<Long, List<Long>>> equations;
    public Day7(String inputFilename) {
        equations = new Day7Scanner(inputFilename).scan();

    }

    public int totalCalibrationResult() {
        return 0;
    }
}

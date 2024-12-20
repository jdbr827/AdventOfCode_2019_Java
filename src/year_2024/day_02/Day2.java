package year_2024.day_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Day2 {
    List<List<Integer>> reports;

    public Day2(String smallInputFilename) {

        reports = new Day2Scanner(smallInputFilename).scan();
    }

    public int numSafeReports() {
        return (int) reports.stream().filter(Day2::isReportSafe).count();
    }

    private static boolean isReportSafe(List<Integer> levels) {
        boolean isIncreasing = levels.get(0) < levels.get(1);
        if (isIncreasing) {
            for (int i = 0; i < levels.size() - 1; i++) {
                if (levels.get(i + 1) - levels.get(i) < 1 || levels.get(i + 1) - levels.get(i) > 3) {
                    return false;
                }
            }
            return true;
        }
        for (int i=0; i < levels.size() - 1; i++) {
            if (levels.get(i) - levels.get(i+1) < 1 || levels.get(i) - levels.get(i+1) > 3) {
                return false;
            }
        }
        return true;
    }


    public int numDampenableReports() {
        return (int) reports.stream().filter(Day2::isReportDampenable).count();
    }

    private static boolean isReportDampenable(List<Integer> levels) {
        if (isReportSafe(levels)) {
            return true;
        }

        for (int removed_idx=0; removed_idx < levels.size(); removed_idx++) {
            List<Integer> removed_levels = new LinkedList();
            for (int i=0; i< levels.size(); i++) {
                if (i != removed_idx) {
                    removed_levels.add(levels.get(i));
                }
            }
            if (isReportSafe(removed_levels)) {
                return true;
            }
        }
        return false;
        // TODO: O(n) solution
    }
}

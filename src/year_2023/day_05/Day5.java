package year_2023.day_05;

import lombok.AllArgsConstructor;

import java.util.List;

public class Day5 {
}

@AllArgsConstructor
class AlmanacMap {
    String sourceCategory;
    String destinationCategory;
    List<AlmanacMapRule> rules;


    int apply(int sourceNumber) {
        for (AlmanacMapRule rule : rules) {
            if (sourceNumber < rule.sourceRangeStart) {
                continue;
            }
            int delta = sourceNumber - rule.sourceRangeStart;
            if (delta < rule.rangeLength) {
                return rule.destinationRangeStart + delta;
            }
        }

        return sourceNumber;
    }
}


@AllArgsConstructor
class AlmanacMapRule {
    int destinationRangeStart;
    int sourceRangeStart;
    int rangeLength;
}

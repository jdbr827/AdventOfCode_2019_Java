package year_2023.day_05;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class Day5 {
    List<Long> seeds;
    List<AlmanacMap> almanac;

    static long day_5_part_1(String filename) {
        Day5 day5 = new Day5Scanner(filename).scan();
        return day5.findLowestLocationNumber(day5.seeds);
    }

    public static long day_5_part_2(String filename) {
        Day5 day5 = new Day5Scanner(filename).scan();
        List<Long> candidateSeeds = day5.createCandidateSeedsList();
        return day5.findLowestLocationNumber(candidateSeeds);
    }

    private List<Long> createCandidateSeedsList() {
        List<Long> candidateSeeds = new LinkedList<>();
        for (int i=0; i<seeds.size(); i+=2) {
            for (int s=0; s<seeds.get(i + 1); s++) {
                candidateSeeds.add(seeds.get(i) + s);
            }
        }
        return candidateSeeds;
    }

    public long findLowestLocationNumber(List<Long> candidateSeeds) {
        return candidateSeeds.stream().mapToLong(this::findLocationNumberForSeed).min().getAsLong();
    }

    // only works because almanac happens to be in perfect order
    public long findLocationNumberForSeed(Long seed) {
        Long sourceVal = seed;
        for (AlmanacMap map : almanac) {
            sourceVal = map.apply(sourceVal);
        }
        return sourceVal;
    }

}


@AllArgsConstructor
@RequiredArgsConstructor
class AlmanacMap {
    String sourceCategory;
    String destinationCategory;
    @NotNull List<AlmanacMapRule> rules;


    long apply(long sourceNumber) {
        for (AlmanacMapRule rule : rules) {
            if (sourceNumber < rule.sourceRangeStart) {
                continue;
            }
            long delta = sourceNumber - rule.sourceRangeStart;
            if (delta < rule.rangeLength) {
                return rule.destinationRangeStart + delta;
            }
        }

        return sourceNumber;
    }
}


@AllArgsConstructor
class AlmanacMapRule {
    long destinationRangeStart;
    long sourceRangeStart;
    long rangeLength;
}

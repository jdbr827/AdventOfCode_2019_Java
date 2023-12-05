package year_2023.day_05;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

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
        for (long l=0L; true; l++) {
            if (day5.isCandidateSeed(day5.findSeedNumberForLocation(l))) {
                return l;
            }
        }
    }

    private boolean isCandidateSeed(long num) {
        for (int i=0; i<seeds.size(); i+=2) {
            if (num < seeds.get(i)) {
                continue;
            } if (num - seeds.get(i) < seeds.get(i+1)) {
                return true;
            }
        }
        return false;
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

    // only works because almanac happens to be in perfect order
    public long findSeedNumberForLocation(Long location) {
        Long sourceVal = location;
        for (int i=almanac.size()-1; i>=0 ; i--) {
            sourceVal = almanac.get(i).invert(sourceVal);
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

     long invert(long destinationNumber) {
        for (AlmanacMapRule rule : rules) {
            if (destinationNumber < rule.destinationRangeStart) {
                continue;
            }
            long delta = destinationNumber - rule.destinationRangeStart;
            if (delta < rule.rangeLength) {
                return rule.sourceRangeStart + delta;
            }
        }
        return destinationNumber;
    }
}


@AllArgsConstructor
class AlmanacMapRule {
    long destinationRangeStart;
    long sourceRangeStart;
    long rangeLength;
}

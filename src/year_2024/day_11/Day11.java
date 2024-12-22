package year_2024.day_11;

import org.jetbrains.annotations.NotNull;
import utils.AOCScanner;
import utils.FrequencyTableUtil;
import java.util.*;

public class Day11 {

    public static List<Long> readIn(String inputFilename) {
        return Arrays.stream(new AOCScanner(inputFilename).nextLine().split("\\s")).map(Long::parseLong).toList();
    }

    public static long numStonesAfterBlinkingNTimes(String inputFilename, int nTimes) {
        return numStonesAfterBlinkingNTimes(Day11.readIn(inputFilename), nTimes);
    }

    public static long numStonesAfterBlinkingNTimes(List<Long> initialStones, int nTimes) {
        Map<Long, Long> stones = FrequencyTableUtil.createFrequencyTableLong(initialStones);
        for (int i=0; i<nTimes; i++) {
            Map<Long, Long> newStones = new HashMap<>();
            for (Long stone : stones.keySet()) {
                for (Long newVal: blink(stone)) {
                    newStones.put(newVal, newStones.getOrDefault(newVal, 0L) + stones.get(stone));
                }
            }
            stones = newStones;
        }
        return stones.values().stream().reduce(0L, Math::addExact);
    }

    private static @NotNull List<Long> blink(Long stone) {
        if (stone == 0L) {
            return List.of(1L);
        }
        String s = stone.toString();
        if (s.length() % 2 == 0L) {
            return List.of(
                    Long.parseLong(s.substring(0, s.length() / 2)),
                    Long.parseLong(s.substring(s.length() / 2))
            );
        } else {
            return List.of(stone * 2024);
        }
    }
}

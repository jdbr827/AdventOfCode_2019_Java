package year_2024.day_11;

import org.jetbrains.annotations.NotNull;
import utils.AOCScanner;
import utils.FrequencyTableUtil;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Day11 {
    Map<Long, Long> stones = new HashMap<>();

    public Day11(String inputFilename) {
        List<Long> stonesList = Arrays.stream(new AOCScanner(inputFilename).nextLine().split("\\s")).map(Long::parseLong).toList();
        stones = FrequencyTableUtil.createFrequencyTableLong(stonesList);
    }
    public static List<Long> readIn(String inputFilename) {
        return Arrays.stream(new AOCScanner(inputFilename).nextLine().split("\\s")).map(Long::parseLong).toList();
    }

    public static long numStonesAfterBlinkingNTimes(String inputFilename, int nTimes) {
        return new Day11(inputFilename).numStonesAfterBlinkingNTimes(nTimes);
    }

    public long numStonesAfterBlinkingNTimes(int nTimes) {
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


    public static long numStonesAfterBlinkingNTimes(Long stone, int nTimes) throws ExecutionException, InterruptedException {
        long tot = 1L;
        List<CompletableFuture<Long>> awaiting = new ArrayList<>();
        //System.out.println(stone + " " + nTimes);
        for (int i=0; i<nTimes; i++) {
            if (stone == 0L) {
                stone=1L;
                continue;
            }
            String s = stone.toString();
            if (s.length() % 2 == 0L) {
                long leftNum = Long.parseLong(s.substring(0, s.length() / 2));
                long rightNum = Long.parseLong(s.substring(s.length() / 2));
                stone = leftNum;
                int finalI = i;
                awaiting.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        return numStonesAfterBlinkingNTimes(rightNum, nTimes - finalI -1);
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }));
            } else {
                stone *= 2024;
            }
        }
        //System.out.println(stone);
        for (CompletableFuture<Long> cf : awaiting) {
            tot += cf.get();
        }
        return tot;
    }


    private static List<Long> blinkLst(List<Long> stones) {
        return stones.stream().map(Day11::blink).reduce(new LinkedList<>(), (ag, l) -> {ag.addAll(l); return ag;});
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

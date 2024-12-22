package year_2024.day_11;

import com.ea.async.Async;
import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Day11 {

    List<Long> stones = new LinkedList<>();

    public Day11(String inputFilename) {
        stones = Arrays.stream(new AOCScanner(inputFilename).nextLine().split("\\s")).map(Long::parseLong).toList();
    }
    public static List<Long> readIn(String inputFilename) {
        return Arrays.stream(new AOCScanner(inputFilename).nextLine().split("\\s")).map(Long::parseLong).toList();
    }

    public static long numStonesAfterBlinkingNTimes(List<Long> stones, int nTimes) {
      return stones.stream().parallel().map(stone ->
          numStonesAfterBlinkingNTimes(stone, nTimes)
      ).reduce(0L, Math::addExact);
    }

    public static long numStonesAfterBlinkingNTimes(Long stone, int nTimes) {
        if (nTimes == 0L) {
            return 1L;
        }
        if (stone == 0L) {
            return numStonesAfterBlinkingNTimes(1L, nTimes - 1);
        }
        String s = stone.toString();
        if (s.length() % 2 == 0L) {
            long left = numStonesAfterBlinkingNTimes(Long.parseLong(s.substring(0, s.length()/2)), nTimes - 1);
            long right = numStonesAfterBlinkingNTimes(Long.parseLong(s.substring(s.length() / 2)), nTimes - 1);
            return left + right;
        } else {
            return numStonesAfterBlinkingNTimes(stone * 2024, nTimes - 1);
        }
    }


    private static List<Long> blink(List<Long> stones) {
        return stones.stream().map((Long stone) -> {
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
        }).reduce(new LinkedList<>(), (ag, l) -> {ag.addAll(l); return ag;});
    }
}

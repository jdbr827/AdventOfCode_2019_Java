package year_2024.day_11;

import utils.AOCScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day11 {

    List<Long> stones = new LinkedList<>();

    public Day11(String inputFilename) {
        stones = Arrays.stream(new AOCScanner(inputFilename).nextLine().split("\\s")).map(Long::parseLong).toList();
    }

    public long numStonesAfterBlinkingNTimes(int nTimes) {
        for (int i=0; i<nTimes; i++) {
            blink();
        }
        return stones.size();
    }

    private void blink() {
        List<Long> newStones = new LinkedList<>();
        for (Long stone : stones) {
            if (stone == 0L) {
                newStones.add(1L);
                continue;
            }
            String s = stone.toString();
            if (s.length() % 2 == 0L) {
                newStones.add(Long.parseLong(s.substring(0, s.length() / 2)));
                newStones.add(Long.parseLong(s.substring(s.length() / 2)));
            } else {
                newStones.add(stone * 2024);
            }
        }
        stones = newStones;
    }
}

package year_2019;


import com.google.common.collect.Collections2;
import year_2019.IntCodeComputer.IntCode;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Day7 {

    public static long computeThrusterPower(List<Long> phaseSettings, long[] mem) {
        BlockingQueue<Long> rollingInputs = new LinkedBlockingQueue<>();
        long rollingResult = 0;
        for (int i=0; i<5; i++) {
            rollingInputs.add(phaseSettings.get(i));
            rollingInputs.add(rollingResult);
            try {
                IntCode.createAndRun(mem, rollingInputs, rollingInputs);
                rollingResult = rollingInputs.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }

        }
        return rollingResult;
    }

    public static long feedbackLoopThrusterPower(List<Long> phaseSettings, long[] mem) throws InterruptedException {
        List<BlockingQueue<Long>> wires = new ArrayList<>();
        List<IntCode> amps = new ArrayList<>();
        for (int i=0; i<5; i++) {
            wires.add(new LinkedBlockingQueue<>());
            amps.add(new IntCode(mem));
        }
        for (int i=0; i<5; i++) {
            amps.get(i).setInput(wires.get(i));
            amps.get(i).setOutput(wires.get((i+1) % 5));
            wires.get(i).add(phaseSettings.get(i));
            amps.get(i).start();
        }
        wires.get(0).add(0L); // Starting input

        for (IntCode amp : amps) { amp.join(); } // Wait for all the amps to finish

        return wires.get(0).take();

    }

    @SuppressWarnings("UnstableApiUsage")
    public static List<Long> optimizeThrusters1(long[] mem) {
        //noinspection OptionalGetWithoutIsPresent
        return Collections2.orderedPermutations(List.of(0L, 1L, 2L, 3L, 4L)).stream()
                .max(Comparator.comparing((l) -> computeThrusterPower(l, mem)))
                .get();

    }

    public static List<Long> optimizeThrusters2(long[] mem) {
        //noinspection OptionalGetWithoutIsPresent
        return Collections2.orderedPermutations(List.of(5L, 6L, 7L, 8L, 9L)).stream()
                .max(Comparator.comparing((l) -> {
                    try {
                        return feedbackLoopThrusterPower(l, mem);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return 0L;
                    }
                }))
                .get();

    }

}

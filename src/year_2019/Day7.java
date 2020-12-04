package year_2019;


import com.google.common.collect.Collections2;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

public class Day7 {

    public static int computeThrusterPower(List<Integer> phaseSettings, int[] mem) {
        BlockingQueue<Integer> rollingInputs = new LinkedBlockingQueue<>();
        int rollingResult = 0;
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

    public static int feedbackLoopThrusterPower(List<Integer> phaseSettings, int[] mem) throws InterruptedException {
        List<BlockingQueue<Integer>> wires = new ArrayList<>();
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
        wires.get(0).add(0); // Starting input

        for (IntCode amp : amps) { amp.join(); } // Wait for all the amps to finish

        return wires.get(0).take();

    }

    @SuppressWarnings("UnstableApiUsage")
    public static List<Integer> optimizeThrusters1(int[] mem) {
        //noinspection OptionalGetWithoutIsPresent
        return Collections2.orderedPermutations(List.of(0, 1, 2, 3, 4)).stream()
                .max(Comparator.comparing((l) -> computeThrusterPower(l, mem)))
                .get();

    }

    public static List<Integer> optimizeThrusters2(int[] mem) {
        //noinspection OptionalGetWithoutIsPresent
        return Collections2.orderedPermutations(List.of(5, 6, 7, 8, 9)).stream()
                .max(Comparator.comparing((l) -> {
                    try {
                        return feedbackLoopThrusterPower(l, mem);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }))
                .get();

    }

}

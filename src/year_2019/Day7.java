package year_2019;


import com.google.common.collect.Collections2;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

public class Day7 {

    public static int computeThrusterPower(List<Integer> phaseSettings, int[] mem) throws InterruptedException {
        BlockingQueue<Integer> rollingInputs = new LinkedBlockingQueue<>();
        int rollingResult = 0;
        for (int i=0; i<5; i++) {
            rollingInputs.add(phaseSettings.get(i));
            rollingInputs.add(rollingResult);
            IntCode.createAndRun(mem, rollingInputs, rollingInputs);
            rollingResult = rollingInputs.take();
        }
        return rollingResult;
    }

    @SuppressWarnings("UnstableApiUsage")
    public static List<Integer> optimizeThrusters(int[] mem) {
        //noinspection OptionalGetWithoutIsPresent
        return Collections2.orderedPermutations(List.of(0, 1, 2, 3, 4)).stream()
                .max(Comparator.comparing((l) -> {
                    try {
                        return computeThrusterPower(l, mem);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return null;
                    }
                }))
                .get();

    }

//    public static void main(String[] args) {
//        @SuppressWarnings("UnstableApiUsage")
//        Collection<List<Integer>> perms = Collections2.orderedPermutations(List.of(1, 2, 3, 4, 5));
//        for (List<Integer> p : perms) {
//            System.out.println(computeThrusterPower(p, ));
//        }
//    }
}

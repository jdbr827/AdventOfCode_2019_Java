package utils;


import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class BFSUtil {

    public static <T> Long doBFSToPoint(T origin, T destination, Function<T, Collection<T>> neighborFunction) {

        BFSHelper<T> myBFSHelper = new BFSHelper<T>(origin, neighborFunction) {
            @Override
            boolean endCondition() {
                return distanceFromOrigin.getOrDefault(destination, Long.MAX_VALUE) != Long.MAX_VALUE;
            }
        };

        myBFSHelper.doBFS();
        return myBFSHelper.distanceFromOrigin.getOrDefault(destination, Long.MAX_VALUE);
    }

    public static <T> Map<T, Long> doBFSExhaustive(T origin, Function<T, Collection<T>> neighborFunction) {
        Map<T, Long> distanceFromOrigin = new HashMap<>();
        Queue<T> old_bfs = new LinkedList<>();
        old_bfs.add(origin);
        distanceFromOrigin.put(origin, 0L);

        while (!old_bfs.isEmpty())  {
            T thisNode = old_bfs.remove();
            for (T neighbor: neighborFunction.apply(thisNode)) {
                if (distanceFromOrigin.getOrDefault(neighbor, Long.MAX_VALUE) == Long.MAX_VALUE) {
                    distanceFromOrigin.put(neighbor, distanceFromOrigin.get(thisNode) + 1);
                    old_bfs.add(neighbor);
                }
            }
        }

        return distanceFromOrigin;
    }


    public static <T> Map<T, Long> doBFSAnyDestination(T origin, Collection<T> destinations, Function<T, Collection<T>> neighborFunction) {

        BFSHelper<T> myBFS = new BFSHelper<T>(origin, neighborFunction) {
            @Override
            boolean endCondition() {
                return destinations.stream().allMatch(dest -> distanceFromOrigin.getOrDefault(dest, Long.MAX_VALUE) != Long.MAX_VALUE);
            }
        };

        myBFS.doBFS();
        return myBFS.distanceFromOrigin;
    }


    @RequiredArgsConstructor
    private abstract static class BFSHelper<T> {
         @NotNull  Map<T, Long> distanceFromOrigin = new HashMap<>();
         @NotNull  Queue<T> old_bfs = new LinkedList<>();
         @NotNull  T origin;
         @NotNull Function<T, Collection<T>> neighborFunction;

        abstract boolean endCondition();

         void doBFS() {
             distanceFromOrigin.put(origin, 0L);
             old_bfs.add(origin);
             while (!(endCondition() || old_bfs.isEmpty())) {
                 T thisNode = old_bfs.remove();
                 for (T neighbor : neighborFunction.apply(thisNode)) {
                     if (distanceFromOrigin.getOrDefault(neighbor, Long.MAX_VALUE) == Long.MAX_VALUE) {
                         distanceFromOrigin.put(neighbor, distanceFromOrigin.get(thisNode) + 1);
                         old_bfs.add(neighbor);
                     }
                 }
             }
         }
    }
}

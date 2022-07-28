package year_2019.day06;

import java.util.*;
import java.util.function.Function;

public class BFSUtil {

    public static <T> Long doBFS(T origin, T destination, Function<T, Collection<T>> neighborFunction) {
        Map<T, Long> distanceFromOrigin = new HashMap<>();
        Queue<T> old_bfs = new LinkedList<>();
        old_bfs.add(origin);
        distanceFromOrigin.put(origin, 0L);

        while (distanceFromOrigin.getOrDefault(destination, Long.MAX_VALUE) == Long.MAX_VALUE || !old_bfs.isEmpty())  {
             T thisNode = old_bfs.remove();
            for (T neighbor: neighborFunction.apply(thisNode)) {
                if (distanceFromOrigin.getOrDefault(neighbor, Long.MAX_VALUE) == Long.MAX_VALUE) {
                    distanceFromOrigin.put(neighbor, distanceFromOrigin.get(thisNode) + 1);
                    old_bfs.add(neighbor);
                }
            }
        }

        return distanceFromOrigin.getOrDefault(destination, Long.MAX_VALUE);


    }
}

package year_2024.day_01;

import org.testng.internal.collections.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day1 {
    List<Integer> list1;
    List<Integer> list2;
    int n;

    public Day1(String filename) {
        Pair<List<Integer>, List<Integer>> pr = new Day1Scanner(filename).scan();
        list1 = pr.first();
        list2 = pr.second();
        n = list1.size();
    }

    public int getDistanceSum() {
        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());
        return IntStream.range(0, n)
                .map((i) -> Math.abs(list1.get(i) - list2.get(i)))
                .sum();
    }
}

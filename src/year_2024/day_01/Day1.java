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
        list1.sort(Comparator.naturalOrder()); // N Log N
        list2.sort(Comparator.naturalOrder()); // N Log N
        n = list1.size();
    }

    public int getDistanceSum() {
        return IntStream.range(0, n)
                .map((i) -> Math.abs(list1.get(i) - list2.get(i)))
                .sum();
    }

    public int getSimilarityScore() {

        // Because they are already sorted we can go O(n) two-pointer

        int left=0;

        // invar left: freqI = number of times list1.get(left) appears in list2
        int freqI = 0;
        int totalSimilarity = 0;
        for (int right=0; right<n; right++) {
            //System.out.println(left + " " + right + " " + list1.get(left) + " " + list2.get(right));
            while (left < n && list1.get(left) < list2.get(right)) {
                totalSimilarity += list1.get(left) * freqI;
                left++;
                if (left < n && !(list1.get(left).equals(list1.get(left - 1)))) {
                    freqI = 0;
                }
            }
            if (left < n && list1.get(left).equals(list2.get(right))) {
                freqI++;
            }
        }
        return totalSimilarity;
    }
}

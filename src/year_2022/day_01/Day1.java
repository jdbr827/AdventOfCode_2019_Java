package year_2022.day_01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static utils.BinarySearchUtil.binaryInsert;

/*
For complexity analysis:
E = number of Elves
T = total number of items carried by the elves
N = we want the total number of calories carried by the top N elves (in the problem N=3)

We know that N <= E <= T, and suspect E << T
 */
public class Day1 {



    /*
    TIME: O(T + E Log E + N) = O(T + E Log E)
    SPACE: O(E)
     */
    public static int part2Method1(String fileName, int topN) throws FileNotFoundException {
        Day1Scanner scanner = new Day1Scanner(fileName);
        ArrayList<Integer> elves = new ArrayList<>(); // O(E) space since we will add all of the elves
        int current;

        while ((current = scanner.getNextElfCalories()) != 0) { // O(T) scan
            elves.add(current);
        }
        elves.sort(Comparator.reverseOrder()); // O(E Log E) time
        return elves.subList(0, topN).stream().reduce(0, Math::addExact); // O(N) scan
    }


    /*
    TIME: O(N+TLogN) = O(TLogN) = O(T)
    SPACE: O(N) = O(1)
    */
    public static int part2Method2(String fileName, int topN) throws IOException {
        Day1Scanner scanner = new Day1Scanner(fileName);

        ArrayList<Integer> elves = new ArrayList<>(); // O(N) space because of invariant
        for (int i=0; i<topN; i++) {elves.add(0);} // O(N) time to fill up

        int current;

        /* LOOP INVARIANT: elves has size N */
        while ((current = scanner.getNextElfCalories()) != 0) { // O(T) scan
            binaryInsert(elves, current); // O(logN) binary insert
            elves.remove(0);
        }

        return elves.stream().reduce(0, Math::addExact); // O(N) scan
    }



    /* O(T) time to scan all the items and O(1) Space */
    public static int part1(String fileName) throws FileNotFoundException {
        Day1Scanner scanner = new Day1Scanner(fileName);

        int bestSoFar = 0;
        int current;

        while ((current = scanner.getNextElfCalories()) != 0) { // O(T)
            bestSoFar = Math.max(current, bestSoFar);
        }
        return bestSoFar;
    }

}

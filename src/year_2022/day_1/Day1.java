package year_2022.day_1;

import com.sun.jdi.IntegerValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static utils.BinarySearchUtil.binaryInsert;

/*
For complexity analysis:
E = number of Elves
T = number of items carried by the elves
 */
public class Day1 {



    /* O(T + E Log E) time and O(E) additional space */
    public static int part2Method1(String fileName) throws FileNotFoundException {
        Day1Scanner scanner = new Day1Scanner(fileName);
        ArrayList<Integer> elves = new ArrayList<>(); // O(E) space
        int current;
        while ((current = scanner.getNextElfCalories()) != 0) { // O(T) time
            elves.add(current);
        }
        elves.sort(Comparator.reverseOrder()); // O(E Log E) time
        return elves.get(0) + elves.get(1) + elves.get(2);
    }


    /* O(T) time and O(1) additional space */
    public static int part2Method2(String fileName) throws IOException {
        Day1Scanner scanner = new Day1Scanner(fileName);

        ArrayList<Integer> elves = new ArrayList<>(Arrays.asList(0, 0, 0)); // O(1) space because of invariant

        int current;

        // LOOP INVARIANT: elves has size 3
        while ((current = scanner.getNextElfCalories()) != 0) { // O(T) time to scan
            binaryInsert(elves, current); // O(1) to insert [logN but N constant]
            elves.remove(0);
        }

        return elves.stream().reduce(0, Math::addExact);
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

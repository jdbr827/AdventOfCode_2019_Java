package year_2022.day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {


    public static int readInNumbers(String fileName) throws FileNotFoundException {
        Day1Scanner scanner = new Day1Scanner(fileName);
        ArrayList<Integer> elves = new ArrayList<>();
        int current;
        while ((current = scanner.getNextElfCalories()) != 0) {
            elves.add(current);
        }
        elves.sort(Comparator.reverseOrder());
        return elves.get(0) + elves.get(1) + elves.get(2);
    }

    public static int part1(String fileName) throws FileNotFoundException {
        Day1Scanner scanner = new Day1Scanner(fileName);

        int bestSoFar = 0;
        int current;
        while ((current = scanner.getNextElfCalories()) != 0) {
            bestSoFar = Math.max(current, bestSoFar);
        }
        return bestSoFar;
    }

    public static int part2(String fileName) throws FileNotFoundException {
        return readInNumbers(fileName);
    }
}

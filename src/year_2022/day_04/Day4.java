package year_2022.day_04;


import jdk.nashorn.api.tree.AssignmentTree;

import java.io.FileNotFoundException;
import java.util.function.Predicate;

public class Day4 {
    Day4Scanner scanner;

    Day4(String fileName) throws FileNotFoundException {
       scanner = new Day4Scanner(fileName);
    }

    int countPairsWithProperty(Predicate<Day4AssignmentPair> predicate) {
        Day4AssignmentPair assignmentPair;
        int num_contained = 0;
        while ((assignmentPair = scanner.getNextLine()) != null) {
            if (predicate.test(assignmentPair)) {
                num_contained += 1;
            }
        }
        return num_contained;

    }


    public static int part1(String fileName) throws FileNotFoundException {
        return new Day4(fileName).countPairsWithProperty(Day4AssignmentPair::completelyContains);
    }

     public static int part2(String fileName) throws FileNotFoundException {
       return new Day4(fileName).countPairsWithProperty(Day4AssignmentPair::hasOverlap);
    }



}
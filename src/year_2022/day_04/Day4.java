package year_2022.day_04;


import com.google.common.collect.Streams;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Day4 {
    Day4Scanner scanner;

    Day4(String fileName) throws FileNotFoundException {
       scanner = new Day4Scanner(fileName);
    }

    int countPairsWithProperty(Predicate<Day4AssignmentPair> property) {
        return (int) Streams.stream(scanner)
                .filter(property)
                .count();
    }

    public static int part1(String fileName) throws FileNotFoundException {
        return new Day4(fileName).countPairsWithProperty(Day4AssignmentPair::completelyContains);
    }

     public static int part2(String fileName) throws FileNotFoundException {
       return new Day4(fileName).countPairsWithProperty(Day4AssignmentPair::hasOverlap);
    }



}
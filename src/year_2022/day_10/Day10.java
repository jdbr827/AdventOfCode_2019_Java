package year_2022.day_10;

import java.util.LinkedList;
import java.util.Queue;

public class Day10 {

    public static int part1(String fileName) {
        Day10Scanner scanner = new Day10Scanner(fileName);
        Day10CPU cpu = new Day10CPU();
        Queue<Day10Operation> operations = new LinkedList<>();
        scanner.forEachRemaining(operations::add);
        cpu.operations = operations;
        return cpu.begin();
    }
}

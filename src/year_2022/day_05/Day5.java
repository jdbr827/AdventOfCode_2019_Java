package year_2022.day_05;

import java.io.FileNotFoundException;
import java.util.List;

public class Day5 {

    public static int part1(String fileName) throws FileNotFoundException {
        Day5Scanner scanner = new Day5Scanner(fileName);
        List<Character> thisLine;
        while (!(thisLine = scanner.getNextLine()).isEmpty()) {
            System.out.println(thisLine);
        }

        return 0;

    }
}

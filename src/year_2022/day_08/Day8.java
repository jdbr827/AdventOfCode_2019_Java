package year_2022.day_08;

import java.io.FileNotFoundException;

public class Day8 {


    public static String part1(String fileName) throws FileNotFoundException {
        return new Day8Scanner(fileName).readInMatrix().toString();
    }
}

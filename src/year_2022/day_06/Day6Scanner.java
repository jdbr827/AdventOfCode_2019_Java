package year_2022.day_06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6Scanner {
    private final Scanner scanner;
    int numScanned = 0;

    Day6Scanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        scanner = new Scanner(file);
        scanner.useDelimiter("");
    }

    Character getNextChar() {
        if (scanner.hasNext()){
            numScanned++;
            return scanner.next().charAt(0);
        }
        return null;
    }

    int getNumScanned() {
        return numScanned;
    }
}

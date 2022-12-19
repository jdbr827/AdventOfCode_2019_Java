package year_2022.day_06;

import utils.AOCScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6Scanner extends AOCScanner {
    int numScanned = 0;

    Day6Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
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

package year_2022.day_06;

import utils.AOCScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6Scanner extends AOCScanner {

    Day6Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
        scanner.useDelimiter("");
    }

    Character getNextChar() {
        if (scanner.hasNext()){
            return scanner.next().charAt(0);
        }
        return null;
    }
}

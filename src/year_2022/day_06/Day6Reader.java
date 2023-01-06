package year_2022.day_06;

import utils.AOCScanner;

import java.io.FileNotFoundException;

public class Day6Reader extends AOCScanner {

    Day6Reader(String fileName) throws FileNotFoundException {
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

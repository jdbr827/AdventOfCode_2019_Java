package year_2022.day_03;

import utils.AOCScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3Scanner extends AOCScanner {

    public Day3Scanner(String filename) throws FileNotFoundException {
        super(filename);
    }

    public String getNextLine() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
}

package year_2022.day_03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3Scanner {
    private Scanner scanner;

    public Day3Scanner(String filename) throws FileNotFoundException {
        File file = new File(filename);
        scanner = new Scanner(file);
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

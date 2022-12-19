package year_2022.day_01;

import utils.AOCScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Scanner extends AOCScanner {

    Day1Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public int getNextElfCalories() {
        int total = 0;
        while (scanner.hasNextLine()) {
              String data = scanner.nextLine();
            if (data.isEmpty()) {
                return total;
            } else {
                total += Integer.parseInt(data);
            }
        }
        // Only way we should get an elf with 0 calories is at end of file;
        return total;

    }
}

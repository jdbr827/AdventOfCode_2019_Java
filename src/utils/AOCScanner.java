package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AOCScanner {
    protected final Scanner scanner;

    public AOCScanner(String fileName) {
        File file = new File(fileName);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
           throw new Error("Did not recognize file name " + fileName);
        }
    }
}

package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AOCScanner {
    protected final Scanner scanner;

    public AOCScanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        scanner = new Scanner(file);
    }
}

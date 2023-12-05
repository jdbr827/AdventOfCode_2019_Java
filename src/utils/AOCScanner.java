package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

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

    public void forEachLine(Consumer<String> processLine) {
        while (scanner.hasNextLine()) {
            processLine.accept(scanner.nextLine());
        }
    }

    public List<List<Character>> scanAsCharMatrix() {
        List<List<Character>> matrix = new java.util.ArrayList<>(List.of());
        while (scanner.hasNextLine()) {
            List<Character> row = new java.util.ArrayList<>(List.of());
            char[] charArr = scanner.nextLine().toCharArray();
            for (char myChar : charArr) {
                row.add(myChar);
            }
            matrix.add(row);
        }
        return matrix;
    }
}



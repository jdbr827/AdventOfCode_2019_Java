package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ReadIn {


    public static void findOrElseThrow(Matcher m, String errorString) {
        if (!m.find()) {
            throw new RuntimeException(errorString);
        }
    }
    public static List<Integer> readInNumbers(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        List<Integer> arr = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            arr.add(Integer.valueOf(data));
        }
        return arr;
    }
}

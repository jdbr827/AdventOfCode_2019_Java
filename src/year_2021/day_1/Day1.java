package year_2021.day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {


    public static int readInNumbers(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        int bestSoFar = 0;
        int current = 0;

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.isEmpty()) {
                bestSoFar = Math.max(bestSoFar, current);
                current = 0;
            } else {
                current += Integer.parseInt(data);
            }
        }
        return bestSoFar;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(readInNumbers("src/year_2021/day_1/day_1_input.txt"));
    }
}

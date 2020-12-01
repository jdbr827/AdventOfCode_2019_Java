import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Target;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static final String INPUT_FILENAME = "C:\\Users\\Jake\\IdeaProjects\\AdventOfCode_2020\\src\\input_aoc_2020_1.txt";
    public static final int TARGET = 2020;

    public static List<Integer> readIn(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        List<Integer> arr = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            arr.add(Integer.valueOf(data));
        }
        return arr;
    }

    /**
     * Returns the product of two integers in arr whose sum is 2020
     * Or -1 if no such pair exists
     * @param arr Input array
     */
    public static int findExpenses(List<Integer> arr) {
        boolean[] isInArr = new boolean[TARGET];
        Arrays.fill(isInArr, false); // Not strictly necessary, mostly for readability

        for (int num : arr) {
            if (isInArr[TARGET - num]) { return num * (TARGET - num); }
            isInArr[num] = true;
        }
        return (-1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> testArr = Arrays.asList(1721, 979, 366, 299, 675, 1456);
        System.out.println(findExpenses(testArr) == 514579);
        System.out.println(findExpenses(readIn(INPUT_FILENAME)));

    }
}

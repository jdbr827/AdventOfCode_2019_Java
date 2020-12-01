package year_2020;

import java.io.File;
import java.io.FileNotFoundException;
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
     * Solves Part 1
     * @param arr Input array
     * @return the product of two integers in arr whose sum is TARGET, or -1 if no such pair exists
     */
    public static int find2Expenses(List<Integer> arr) {
        boolean[] isInArr = new boolean[TARGET];
        Arrays.fill(isInArr, false); // Not strictly necessary, mostly for readability

        for (int num : arr) {
            if (isInArr[TARGET - num]) { return num * (TARGET - num); }
            isInArr[num] = true;
        }
        return (-1);
    }

    /**
     * Solves Part 2
     * @param arr Input array of integers
     * @return the product of three integers in arr whose sum is TARGET, or -1 if no such pair exists
     */
    public static int find3Expenses(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            int x = arr.get(i);
            for (int j = i+1; j < n; j++) {
                int y = arr.get(j);
                for (int k = j+1; k < n; k++) {
                    int z = arr.get(k);
                    if (x + y + z == TARGET) {
                        return x*y*z;
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> testArr = Arrays.asList(1721, 979, 366, 299, 675, 1456);
        List<Integer> realArr = readIn(INPUT_FILENAME);

        /* Part 1 */
        System.out.println(find2Expenses(testArr) == 514579);
        System.out.println(find2Expenses(realArr) == 1005459); // Solved

        /* Part 2 */
        System.out.println(find3Expenses(testArr) == 241861950);
        System.out.println(find3Expenses(realArr));

    }
}

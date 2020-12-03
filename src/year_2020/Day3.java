package year_2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    /**
     * Determines the number of trees you would run into for a given slope
     * @param right the delta-x of the slope
     * @param down the delta-y of the slope
     * @param data the terrain, as described in a list of strings, so terrain at (x, y) is represented as data.get(y).charAt(x)
     * @return the number of trees you would run into in the given slope
     */
    public static int checkSlopes(int right, int down, List<String> data) {
        int count = 0;
        int xMax = data.get(0).length();
        for (int x=0,y=0;   y<data.size();  x=(x+right)%xMax, y+=down) {
            if (data.get(y).charAt(x) == '#') {
                count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "C:\\Users\\Jake\\IdeaProjects\\AdventOfCode_2020\\src\\year_2020\\input_aoc_2020_3";
        File file = new File(inputFile);
        Scanner scanner = new Scanner(file);

        List<String> arr = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            arr.add(data);
        }

        long a = checkSlopes(1, 1, arr);
        long b = checkSlopes(3, 1, arr); // Part 1
        long c = checkSlopes(5, 1, arr);
        long d = checkSlopes(7, 1, arr);
        long e = checkSlopes(1, 2, arr);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        System.out.println(a*b*c*d*e); // Part 2

    }
}

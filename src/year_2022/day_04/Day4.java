package year_2022.day_04;


import java.io.FileNotFoundException;
import java.util.regex.Matcher;

public class Day4 {


    public static int part1(String fileName) throws FileNotFoundException {
        Day4Scanner scanner = new Day4Scanner(fileName);
        Matcher m;
        int num_contained = 0;
        while ((m = scanner.getNextLine()) != null) {
            int A_low = Integer.parseInt(m.group(1));
            int A_high = Integer.parseInt(m.group(2));
            int B_low = Integer.parseInt(m.group(3));
            int B_high = Integer.parseInt(m.group(4));
            if ((A_low <= B_low && B_high <= A_high) || (B_low <= A_low && A_high <= B_high)) {
                System.out.println(m.group(0));
                num_contained += 1;
            }
        }
        return num_contained;
    }

     public static int part2(String fileName) throws FileNotFoundException {
        Day4Scanner scanner = new Day4Scanner(fileName);
        Matcher m;
        int num_contained = 0;
        while ((m = scanner.getNextLine()) != null) {
            int A_low = Integer.parseInt(m.group(1));
            int A_high = Integer.parseInt(m.group(2));
            int B_low = Integer.parseInt(m.group(3));
            int B_high = Integer.parseInt(m.group(4));
            //System.out.println(A_low + " " + A_high + " " + B_low + " " + B_high);
            if (!((A_low <= B_low && A_high < B_low) || (B_low <= A_low && B_high < A_low))) {
                //System.out.println(m.group(0));
                num_contained += 1;
            }
        }
        return num_contained;
    }
}
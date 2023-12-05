package year_2023.day_01;

import utils.AOCScanner;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class CalibrationDocumentReader {


    public static void main(String[] args) {
        new Day1Scanner("src/year_2023/day_01/day_1_2023_input.txt").scan();
    }
}


class Day1Scanner extends AOCScanner {

    public Day1Scanner(String fileName) {
        super(fileName);
    }

    public static final List<Character> INTEGERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    static int charIsNumber(char c) {
        if (INTEGERS.contains(c)) {
            return Integer.parseInt(String.valueOf(c));
        }
        return -1;
    }

    int scan() {
        return scan(false);
    }

    int scan(boolean usePart2) {
        int grandTotal = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            int total;
            if (usePart2) {
                total = getCalibrationValue(data, true);
            } else {
                total = getCalibrationValue(data, false);
            }
            grandTotal += total;
        }
        System.out.println(grandTotal);
        return grandTotal;
    }

    static int getCalibrationValue(String data, boolean includeSpelledOut) {
        int firstNumber = 0;
        int lastNumber = 0;

        BiFunction<String, Integer, Integer> startChecker = includeSpelledOut
                ? Day1Scanner::char_at_i_is_or_starts_number
                : Day1Scanner::char_at_i_is_number;


        BiFunction<String, Integer, Integer> endChecker = includeSpelledOut
                ? Day1Scanner::char_at_i_is_or_ends_number
                : Day1Scanner::char_at_i_is_number;

        for (int i=0; i<data.length(); i++) {
            if ((firstNumber = startChecker.apply(data, i)) != -1) {
                break;
            }
        }
        for (int i = data.length() - 1; i>=0; i--) {
            if ((lastNumber = endChecker.apply(data, i)) != -1) {
                break;
            }
        }

        return firstNumber * 10 + lastNumber;
    }


    static final Map<String, Integer> SPELLED_OUT_INTEGERS = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    static int char_is_on_boundary_of_number(String data, int i, boolean checkStart) {
        int n = data.length();

        Predicate<Integer> boundary = checkStart
            ? (d -> i > n-d)
            : (d -> i+1 < d);

        Function<Integer, String> subStr = checkStart
            ? d -> data.substring(i, i+d)
            : d -> data.substring(i-d+1, i+1);

        for (int delta=3; delta<6; delta++) { // since all digits contain between 3 and 5 letters
            if (boundary.test(delta)) {
                return -1;
            }
            String sub = subStr.apply(delta);
            int val = SPELLED_OUT_INTEGERS.getOrDefault(sub, -1);
            if (val != -1) {
                return val;
            }
        }
        return -1;
    }



     static int char_at_i_is_or_is_boundary_of_number(String data, int i, boolean checkStart) {
        int charAsNumber;
        if ((charAsNumber = char_at_i_is_number(data, i)) != -1) {
            return charAsNumber;
        }
        return char_is_on_boundary_of_number(data, i, checkStart);


    }


    static int char_at_i_is_or_starts_number(String data, int i) {
        return char_at_i_is_or_is_boundary_of_number(data, i, true);
    }

    static int char_at_i_is_number(String data, int i) {
        char x = data.charAt(i);
        return charIsNumber(x);
    }

    static int char_at_i_is_or_ends_number(String data, int i) {
       return char_at_i_is_or_is_boundary_of_number(data, i, false);

    }




}


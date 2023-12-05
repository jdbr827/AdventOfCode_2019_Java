package year_2023.day_01;

import utils.AOCScanner;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

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

    static int charStartsNumber(String data, int i) {
        int n = data.length();
        if (i > n-3) {
            return -1;
        }
        String sub3 = data.substring(i, i+3);
        int val3 = SPELLED_OUT_INTEGERS.getOrDefault(sub3, -1);
        if (val3 != -1) {
            return val3;
        }

        if (i > n-4) {
            return -1;
        }

        String sub4 = data.substring(i, i+4);
        int val4 = SPELLED_OUT_INTEGERS.getOrDefault(sub4, -1);
        if (val4 != -1) {
            return val4;
        }


         if (i > n-5) {
            return -1;
        }

        String sub5 = data.substring(i, i+5);
        return SPELLED_OUT_INTEGERS.getOrDefault(sub5, -1);
    }

    static int charEndsNumber(String data, int i) {
        if (i < 3) {
            return -1;
        }
        String sub3 = data.substring(i-3, i);
        int val3 = SPELLED_OUT_INTEGERS.getOrDefault(sub3, -1);
        if (val3 != -1) {
            return val3;
        }

        if (i < 4) {
            return -1;
        }

        String sub4 = data.substring(i-4, i);
        int val4 = SPELLED_OUT_INTEGERS.getOrDefault(sub4, -1);
        if (val4 != -1) {
            return val4;
        }


         if (i < 5) {
            return -1;
        }

        String sub5 = data.substring(i-5, i);
        return SPELLED_OUT_INTEGERS.getOrDefault(sub5, -1);
    }


     static int char_at_i_is_or_is_boundary_of_number(String data, int i, boolean checkStart) {
        int charAsNumber;
        if ((charAsNumber = char_at_i_is_number(data, i)) != -1) {
            return charAsNumber;
        }
        return checkStart ? charStartsNumber(data, i) : charEndsNumber(data, i+1);


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


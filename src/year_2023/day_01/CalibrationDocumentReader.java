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

    public static final int NOT_A_NUMBER = -1;
    public static final List<Character> INTEGERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

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

    int scan() {
        return scan(false);
    }

    int scan(boolean includeSpelledOut) {
        int grandTotal = 0;
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            int total = getCalibrationValue(data, includeSpelledOut);
            grandTotal += total;
        }
        System.out.println(grandTotal);
        return grandTotal;
    }

    /**
     * Returns the calibration value (the first number, then the last number) for a given line
     * @param data the given line
     * @param includeSpelledOut whether to count a spelled out number (e.g. "two") as a number
     * @return The calibration value for the line.
     */
    static int getCalibrationValue(String data, boolean includeSpelledOut) {
        int firstNumber = getFirstNumber(data, includeSpelledOut);
        int lastNumber = getLastNumber(data, includeSpelledOut);
        return firstNumber * 10 + lastNumber;
    }

    /**
     * Returns the first available number in the given line
     * @param data the given line
     * @param includeSpelledOut whether to count a spelled out number (e.g. "two") as a number
     * @return the integer value of the first number in the given line
     */
    static int getFirstNumber(String data, boolean includeSpelledOut) {
         int firstNumber;

         BiFunction<String, Integer, Integer> checkFirstNumber = includeSpelledOut
                ? Day1Scanner::number_char_at_i_is_or_starts_if_any
                : Day1Scanner::number_char_at_i_is_if_any;

         for (int i=0; i<data.length(); i++) {
            if ((firstNumber = checkFirstNumber.apply(data, i)) != NOT_A_NUMBER) {
                return firstNumber;
            }
        }

         // Problem statement guarantees we never get here.
        return NOT_A_NUMBER;
    }


     /**
     * Returns the last available number in the given line
     * @param data the given line
     * @param includeSpelledOut whether to count a spelled out number (e.g. "two") as a number
     * @return the integer value of the last number in the given line
     */
    static int getLastNumber(String data, boolean includeSpelledOut) {
        int lastNumber;

        BiFunction<String, Integer, Integer> checkLastNumber = includeSpelledOut
                ? Day1Scanner::number_char_at_i_is_or_ends_if_any
                : Day1Scanner::number_char_at_i_is_if_any;

        for (int i = data.length() - 1; i>=0; i--) { // going backwards
            if ((lastNumber = checkLastNumber.apply(data, i)) != NOT_A_NUMBER) {
                return lastNumber;
            }
        }

        return NOT_A_NUMBER;
    }



    static int number_char_at_i_is_if_any(String data, int i) {
        char x = data.charAt(i);
        if (INTEGERS.contains(x)) {
            return Integer.parseInt(String.valueOf(x));
        }
        return NOT_A_NUMBER;
    }



     static int number_char_at_i_is_or_is_at_boundary_of_if_any(String data, int i, boolean checkStart) {
        int charAsNumber;
        if ((charAsNumber = number_char_at_i_is_if_any(data, i)) != NOT_A_NUMBER) {
            return charAsNumber;
        }
        return number_char_at_i_is_at_boundary_of_if_any(data, i, checkStart);


    }


    static int number_char_at_i_is_or_starts_if_any(String data, int i) {
        return number_char_at_i_is_or_is_at_boundary_of_if_any(data, i, true);
    }



    static int number_char_at_i_is_or_ends_if_any(String data, int i) {
       return number_char_at_i_is_or_is_at_boundary_of_if_any(data, i, false);
    }


    static int number_char_at_i_is_at_boundary_of_if_any(String data, int i, boolean checkStart) {
        int n = data.length();
        int val;

        Predicate<Integer> boundary = checkStart
            ? (d -> i > n-d)
            : (d -> i+1 < d);

        Function<Integer, String> subStr = checkStart
            ? d -> data.substring(i, i+d)
            : d -> data.substring(i-d+1, i+1);

        for (int delta=3; delta<6; delta++) { // since all digits contain between 3 and 5 letters
            if (boundary.test(delta)) {
                return NOT_A_NUMBER;
            }
            String sub = subStr.apply(delta);
            if ((val = SPELLED_OUT_INTEGERS.getOrDefault(sub, NOT_A_NUMBER)) != NOT_A_NUMBER) {
                return val;
            }
        }
        return NOT_A_NUMBER;
    }




}


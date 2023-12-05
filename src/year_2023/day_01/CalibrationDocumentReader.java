package year_2023.day_01;

import utils.AOCScanner;

import java.util.List;
import java.util.Map;

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
                total = getCalibrationValue_part2(data);
            } else {
                total = getCalibrationValue(data);
            }
            grandTotal += total;
        }
        System.out.println(grandTotal);
        return grandTotal;
    }

    private int getCalibrationValue(String data) {
        Integer firstNumber = 0;
        Integer lastNumber = 0;
        for (char x : data.toCharArray()) {
            int charAsNumber;
            if ((charAsNumber = charIsNumber(x)) != -1) {
                firstNumber = charAsNumber;
                break;
            }
        }
        for (int i = data.length() - 1; i>=0; i--) {
            char x = data.charAt(i);
            int charAsNumber = charIsNumber(x);
            if (charAsNumber != -1) {
                lastNumber = charAsNumber;
                break;
            }
        }

        int total = firstNumber * 10 + lastNumber;

        return total;
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

     static int getCalibrationValue_part2(String data) {
        Integer firstNumber = 0;
        Integer lastNumber = 0;
        for (int i=0; i<data.length(); i++) {
            char x = data.charAt(i);
            int charIsNumber = charIsNumber(x);
            if (charIsNumber == -1) {
                charIsNumber = charStartsNumber(data, i);
            }
            if (charIsNumber != -1) {
                firstNumber = charIsNumber;
                break;
            }
        }
        for (int i = data.length() - 1; i>=0; i--) {
            char x = data.charAt(i);
            int charIsNumber = charIsNumber(x);
            if (charIsNumber == -1) {
                charIsNumber = charEndsNumber(data, i+1);
            }
            if (charIsNumber != -1) {
                lastNumber = charIsNumber;
                break;
            }
        }


        int total = firstNumber * 10 + lastNumber;
        //System.out.println(data + " " + total);
        return total;
    }


}


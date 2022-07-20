package year_2019;

import java.util.stream.IntStream;

public class Day4 {

    public static final int PUZZLE_INPUT_MIN = 347312;
    public static final int PUZZLE_INPUT_MAX = 805915;

    public static boolean is_valid_password(Integer num) {
        if (num < PUZZLE_INPUT_MIN || num > PUZZLE_INPUT_MAX) {
            return false;
        }

        char[] digitList = num.toString().toCharArray();

        boolean hasDouble = false;
        for (int i = 0; i < 5; i++) {
            if (digitList[i] > digitList[i+1]) {
                    return false;
            }
            if (digitList[i] == digitList[i+1]) {

                /* Begin part2 only */
                if (i < 4 && digitList[i+1] == digitList[i+2]) {
                    while (i < 4 && digitList[i + 1] == digitList[i + 2]) {
                        i++;
                    }
                    continue;
                }
                /* end part2 only */

                hasDouble = true;

            }
        }
        return hasDouble;
    }

    public static void main(String[] args) {
        long tot = IntStream.rangeClosed(PUZZLE_INPUT_MIN, PUZZLE_INPUT_MAX)
                .filter(Day4::is_valid_password)
                .count();


        System.out.println(tot);
    }
}

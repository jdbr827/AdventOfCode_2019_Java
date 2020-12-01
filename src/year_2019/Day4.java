package year_2019;

public class Day4 {

    public static final int PUZZLE_INPUT = 347312;
    public static final int PUZZLE_OUTPUT = 805915;

    public static boolean is_valid_password(Integer num) {
        if (num < PUZZLE_INPUT || num > PUZZLE_OUTPUT) {
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

        int tot = 0;
        for (int i=PUZZLE_INPUT; i < PUZZLE_OUTPUT; i++) {
            if (is_valid_password(i)) {
                tot += 1;
            }
        }

        System.out.println(tot);
    }
}

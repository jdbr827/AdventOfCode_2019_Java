package year_2022.day_25;

import java.util.Map;

public class SNAFUConverter {

    static Map<Character, Integer> snafuMap = Map.of(
            '2', 2,
            '1', 1,
            '0', 0,
            '-', -1,
            '=', -2
    );

    public static int convertFromSNAFU(String snafu) {
        int total = 0;

        for (Character snafuChar: snafu.toCharArray()) {
            total *= 5;
            total += snafuMap.get(snafuChar);
        }
        return total;
    }
}

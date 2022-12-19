package year_2022.day_06;

import java.io.FileNotFoundException;

public class Day6 {


    public static int part1(String fileName) throws FileNotFoundException {
        return findStepsUntilLastNAllDiff(fileName, 4);
    }

    public static int part2(String fileName) throws FileNotFoundException {
        return findStepsUntilLastNAllDiff(fileName, 14);
    }

    private static int findStepsUntilLastNAllDiff(String fileName, int N) throws FileNotFoundException {
        Day6Scanner scanner = new Day6Scanner(fileName);
        Character nextChar;
        int c = 0;

        Character[] previous = new Character[N];
        for (int i = 0; i < N; i++) {
            previous[N-1 - i] = scanner.getNextChar();
            c++;
        }

        while (true) {
            boolean allDiff = true;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (previous[i].equals(previous[j])) {
                        allDiff = false;
                        break;
                    }
                }
            }
            if (allDiff) {
                return c;
            }

            for (int i = N-1; i > 0; i--) {
                previous[i] = previous[i - 1];
            }

            previous[0] = scanner.getNextChar();
            c++;
        }
    }


}

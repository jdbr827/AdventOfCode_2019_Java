package year_2023.day_15;

import utils.AOCScanner;

import java.util.Arrays;

public class Day15 {
    String[] initializationSequence;


    Day15(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        initializationSequence = scanner.nextLine().split(",");
    }

    static int runHASHAlgo(String inputStr) {
        int currentValue = 0;
        for (Character c : inputStr.toCharArray()) {
            int asciiVal = (int) c;
            currentValue += asciiVal;
            currentValue *= 17;
            currentValue %= 256;
        }
        return currentValue;
    }

    int sumOfHashResults() {
        return Arrays.stream(initializationSequence)
                .map(Day15::runHASHAlgo)
                .reduce(0, Math::addExact);
    }
}

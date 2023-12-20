package year_2023.day_15;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import utils.AOCScanner;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
    String[] initializationSequence;
    Box[] lensConfiguration = new Box[256];

     private static final Pattern initializationOpPattern = Pattern.compile("([\\w]+)([-|=])([1-9]?)");




    Day15(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        initializationSequence = scanner.nextLine().split(",");
        for (int i=0; i<256; i++) {
            lensConfiguration[i] = new Box();
        }
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

    public void executeInitializationSequence() {
        for (String s : initializationSequence) {
            Operation op = parseInitializationStep(s);
            int boxNum = runHASHAlgo(op.label);
            Box boxToOperateOn = lensConfiguration[boxNum];
            if (op.action == '-') {
                boxToOperateOn.removeLens(op.label);
            } else if (op.action == '=') {
                boxToOperateOn.addLens(op.label, op.focalLength);
            } else {
                throw new Error("Could not parse operation code " + op.action);
            }
        }
    }

    private Operation parseInitializationStep(String s) {
         // create matcher for pattern p and given string
        Matcher m = initializationOpPattern.matcher(s);

        if (m.find()) {
            String label = m.group(1);
            Character actionChar = m.group(2).charAt(0);

            if (actionChar == '=') {
                int focalLength = Integer.parseInt(m.group(3));
                return new Operation(label, actionChar, focalLength);
            } else {
                return new Operation(label, actionChar);
            }
        } else {
            throw new RuntimeException("Could not extract initialization op from Regex: " + s);
        }
    }

    public int computeFocusingPower() {
        int total = 0;
        for (int i=0; i < 256; i++) {
            Box box = lensConfiguration[i];
            total += (i+1) * box.getFocalPowerOfThisBox();
        }
        return total;
    }
}

@AllArgsConstructor
@RequiredArgsConstructor
class Operation {
    @NonNull  String label;
    @NonNull Character action;
    Integer focalLength;
}

@NoArgsConstructor
class Box {
    LinkedList<String> lensLabelQueue = new LinkedList<>();
    Map<String, Integer> lensFocalLengths = new HashMap<>();

    int getFocalPowerOfThisBox() {
        int total = 0;
        for (int i=0; i<lensLabelQueue.size(); i++) {
            total += (i+1) * lensFocalLengths.get(lensLabelQueue.get(i));
        }
        return total;
    }


    void addLens(String lensLabel, int focalLength){
        if (lensFocalLengths.containsKey(lensLabel)) {
            lensFocalLengths.put(lensLabel, focalLength);
        } else {
            lensLabelQueue.add(lensLabel);
            lensFocalLengths.put(lensLabel, focalLength);
        }
    }

    void removeLens(String lensLabel) {
        if (lensFocalLengths.containsKey(lensLabel)) {
            lensLabelQueue.remove(lensLabel);
            lensFocalLengths.remove(lensLabel);
        }
    }
}

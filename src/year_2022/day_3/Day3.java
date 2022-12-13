package year_2022.day_3;

import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.KeyException;

public class Day3 {

    public static void main(String[] args) {

    }

    public static int part1(String filename) throws FileNotFoundException, KeyException {
        Day3Scanner scanner = new Day3Scanner(filename);
        String thisLine;
        int totalMissing = 0;
        while ((thisLine = scanner.getNextLine()) != null) {
            int thisPriority = getMissingPriority(thisLine);
            totalMissing += thisPriority;
        }
        return totalMissing;
    }

     public static int part2(String filename) throws FileNotFoundException, KeyException {
        Day3Scanner scanner = new Day3Scanner(filename);
        int totalMissing = 0;
        while (scanner.hasNextLine()) { // Faith that the number of lines is a multiple of 3
            int thisPriority = getBadgePriority(scanner.getNextLine(), scanner.getNextLine(), scanner.getNextLine());
            totalMissing += thisPriority;
        }
        return totalMissing;
    }

    public static int getBadgePriority(String line1, String line2, String line3) throws InvalidKeyException {// could optimize for off-by-1 later
        boolean[] priorityPresent1 = new boolean[53]; // could optimize for off-by-1 later
        boolean[] priorityPresent2 = new boolean[53];
        boolean[] priorityPresent3 = new boolean[53];
        for (char c : line1.toCharArray()) {
            priorityPresent1[getPriority(c)] = true;
        }
        for (char c : line2.toCharArray()) {
            priorityPresent2[getPriority(c)] = true;
        }
        for (char c : line3.toCharArray()) {
            priorityPresent3[getPriority(c)] = true;
            if (priorityPresent1[getPriority(c)] && priorityPresent2[getPriority(c)]) {
                return getPriority(c);
            }
        }
        throw new InvalidKeyException("No shared element found");
    }

    protected static int getPriority(char thisChar) throws InvalidKeyException {
        int thisOrd = (int) thisChar;
        if (thisOrd > 96) {
            return thisOrd - 96;
        } else if (thisOrd > 64 && thisOrd < 91) {
            return thisOrd - 64 + 26;
        } else {
            throw new InvalidKeyException("Unrecognized character " + thisChar + " for which we tried to get priority");
        }
    }

    protected static int getMissingPriority(String thisLine) throws KeyException {
        boolean[] priorityPresent = new boolean[53]; // could optimize for off-by-1 later
        int n = thisLine.length();
        for (int i=0; i<n/2; i++) {
            priorityPresent[getPriority(thisLine.charAt(i))] = true;
        }
        for (int i=n/2; i<n; i++) {
            if (priorityPresent[getPriority(thisLine.charAt(i))]) {
                return getPriority(thisLine.charAt(i));
            }
        }
        throw new InvalidKeyException("No shared element found");
        // execution should never reach here
    }
}

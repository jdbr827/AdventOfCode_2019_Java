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

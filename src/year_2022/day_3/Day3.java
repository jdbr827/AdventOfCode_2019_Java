package year_2022.day_3;

import java.io.FileNotFoundException;

public class Day3 {

    public static void main(String[] args) {

    }

    public static int part1(String filename) throws FileNotFoundException {
        Day3Scanner scanner = new Day3Scanner(filename);
        String thisLine;
        int totalMissing = 0;
        while ((thisLine = scanner.getNextLine()) != null) {
            int thisPriority = getMissingPriority(thisLine);
            totalMissing += thisPriority;
        }
        return totalMissing;
    }

    protected static int getPriority(char thisChar) throws Exception {
        int thisOrd = (int) thisChar;
        if (thisOrd > 96) {
            return thisOrd - 96;
        } else if (thisOrd > 64 && thisOrd < 91) {
            return thisOrd - 64 + 26;
        } else {
            throw new Exception("Unrecognized character " + thisChar + " for which we tried to get priority");
        }
    }

    private static int getMissingPriority(String thisLine) {
        return 0;
    }
}

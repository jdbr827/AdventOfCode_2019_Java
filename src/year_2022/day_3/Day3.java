package year_2022.day_3;

import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.util.Arrays;

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
            int thisPriority = getUniqueSharedChar(scanner.getNextLine(), scanner.getNextLine(), scanner.getNextLine());
            totalMissing += thisPriority;
        }
        return totalMissing;
    }

    /**
     * Finds the priority of the unique char shared among an arbitrary number of strings.
     * @param lines
     * @return
     * @throws InvalidKeyException if no char is shared among them all;
     */
     public static int getUniqueSharedChar(String... lines) throws InvalidKeyException {// could optimize for off-by-1 later

         boolean[] priorityPresentAll = new boolean[53]; // could optimize for off-by-1 later;
         Arrays.fill(priorityPresentAll, true);

         /*
          * INVAR @ iteration i:
          * priorityPresentAll is true iff the character with priority p is present in lines[0, ..., i-1]
          */
         for (String line : lines) {                                // O(L) time
             boolean[] priorityPresentThis = new boolean[53];
             for (char c: line.toCharArray()) {                     // O(M) time
                 priorityPresentThis[getPriority(c)] = true;        // O(P) time
             }
             for (int i=0; i<53; i++) {
                 priorityPresentAll[i] &= priorityPresentThis[i];
             }
         }

         for (int i=0; i<53; i++) {                                 // Constant time
             if (priorityPresentAll[i]) {
                 return i;
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
        int n = thisLine.length();
        return getUniqueSharedChar(thisLine.substring(0, n/2), thisLine.substring(n/2));
    }
}

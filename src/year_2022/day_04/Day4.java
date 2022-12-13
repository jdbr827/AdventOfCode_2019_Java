package year_2022.day_04;


import java.io.FileNotFoundException;

public class Day4 {


    public static int part1(String fileName) throws FileNotFoundException {
        Day4Scanner scanner = new Day4Scanner(fileName);
        Day4AssignmentPair assignmentPair;
        int num_contained = 0;
        while ((assignmentPair = scanner.getNextLine()) != null) {
            if (assignmentPair.completelyContains()) {
                num_contained += 1;
            }
        }
        return num_contained;
    }

     public static int part2(String fileName) throws FileNotFoundException {
        Day4Scanner scanner = new Day4Scanner(fileName);
        Day4AssignmentPair assignmentPair;
        int num_contained = 0;
        while ((assignmentPair = scanner.getNextLine()) != null) {
           if (assignmentPair.hasOverlap()) {
                num_contained += 1;
            }
        }
        return num_contained;
    }



}
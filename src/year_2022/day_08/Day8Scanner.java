package year_2022.day_08;

import utils.AOCScanner;

import java.io.FileNotFoundException;
import java.util.List;

public class Day8Scanner extends AOCScanner {

    public Day8Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }


    List<List<Integer>> readInMatrix() {
        int N = 0;
        List<List<Integer>> matrix = new java.util.ArrayList<>(List.of());
        while (scanner.hasNextLine()) {
            List<Integer> row = new java.util.ArrayList<>(List.of());
            char[] charArr = scanner.nextLine().toCharArray();
            for (char myChar : charArr) {
                Integer myInt = Character.getNumericValue(myChar);
                row.add(myInt);
            }
            matrix.add(row);
        }
        return matrix;
    }
}

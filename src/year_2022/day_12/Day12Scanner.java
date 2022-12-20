package year_2022.day_12;

import utils.AOCScanner;
import viewModelUtil.JavaPoint;

import java.io.FileNotFoundException;
import java.util.List;

public class Day12Scanner extends AOCScanner {

    public Day12Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    List<List<Character>> readInMatrix() {
        List<List<Character>> matrix = new java.util.ArrayList<>(List.of());
        int i=0;
        while (scanner.hasNextLine()) {
            i++;
            List<Character> row = new java.util.ArrayList<>(List.of());
            char[] charArr = scanner.nextLine().toCharArray();
            for (char myChar : charArr) {
                row.add(myChar);
            }
            matrix.add(row);
        }
        return matrix;
    }


}

package year_2023.day_03;

import utils.AOCScanner;
import utils.AOCScanner_2023;
import year_2023.day_01.Day1Scanner;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static year_2023.day_01.Day1Scanner.NOT_A_NUMBER;

public class Day3Scanner {

    public static Day3 scan(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        List<List<Character>> charMatrix = scanner.scanAsCharMatrix();
        int numRows = charMatrix.size();
        int numCols = charMatrix.get(0).size();

        List<Point> symbolLocations = new ArrayList<>();
        List<SchematicNumber> schematicNumberList = new ArrayList<>();
        List<Point> gearLocations = new ArrayList<>();


        for (int row = 0; row < numRows; row++) {
            int currentNumberValue = 0;
            int startOfNumber = -1;

            List<Character> charRow = charMatrix.get(row);
            for (int col = 0; col < numRows; col++) {
                Character thisChar = charRow.get(col);
                int thisNum;
                if ((thisNum = Day1Scanner.number_char_is_if_any(thisChar)) != NOT_A_NUMBER) {
                    if (startOfNumber == -1) {
                        startOfNumber = col;
                    }
                    currentNumberValue *= 10;
                    currentNumberValue += thisNum;
                } else {
                    if (startOfNumber != -1) {
                        int endOfNumber = col - 1;
                        schematicNumberList.add(new SchematicNumber(currentNumberValue, row, startOfNumber, endOfNumber));
                        currentNumberValue = 0;
                        startOfNumber = -1;
                    }
                    if (thisChar != '.') { // it is a symbol
                        symbolLocations.add(new Point(row, col));

                        if (thisChar == '*') { // it is a gear
                            gearLocations.add(new Point(row, col));
                        }
                    }
                }
            }

            //schematic number ending at end of row use case
            if (startOfNumber != -1) {
                int endOfNumber = numCols - 1;
                schematicNumberList.add(new SchematicNumber(currentNumberValue, row, startOfNumber, endOfNumber));
            }
        }

        return new Day3(symbolLocations, schematicNumberList, gearLocations);
    }
}



package year_2023.day_03;

import lombok.AllArgsConstructor;
import lombok.ToString;
import year_2023.day_01.Day1Scanner;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static year_2023.day_01.Day1Scanner.NOT_A_NUMBER;



@AllArgsConstructor
public class Day3 {
    // 1 pass to get schematic number info and symbol locations
    List<Point> symbolLocations;
    List<SchematicNumber> schematicNumberList;
    List<Point> gearLocations;


    public static int day_3_part_1_2023(String filename) {
        Day3 day3 = new Day3Scanner(filename).scan();
        day3.markPartNumbers();
        return day3.sumPartNumbers();
    }

    public static int day_3_part_2_2023(String filename) {
        Day3 day3 = new Day3Scanner(filename).scan();
        return day3.determineAndSumGearRatios();
    }

    private int determineAndSumGearRatios() {
        int totalGearRatio = 0;
        for (Point gearLocation : gearLocations) {
            int numTouching = 0;
            int gearRatio = 1;
            for (SchematicNumber schematicNumber : schematicNumberList) {
                if (schematicNumber.isTouchedBySymbolAt(gearLocation)) {
                    numTouching += 1;
                    if (numTouching > 2) {
                        break;
                    }
                    gearRatio *= schematicNumber.value;
                }
            }

            if (numTouching == 2) {
                totalGearRatio += gearRatio;
            }
        }
        return totalGearRatio;
    }



        //System.out.println(schematicNumberList);


    void markPartNumbers() {    // go through all symbols, marking schematic numbers that are touching
        for (Point symbol : symbolLocations) {
            for (SchematicNumber schematicNumber : schematicNumberList) {
                if (schematicNumber.isTouchedBySymbolAt(symbol)) {
                    schematicNumber.isPartNumber = true;
                }
            }
        }
    }

    public int sumPartNumbers() {
        // add all marked schematic number values
        int total = 0;
        for (SchematicNumber schematicNumber : schematicNumberList) {
            if (schematicNumber.isPartNumber) {
                total += schematicNumber.value;
            }
        }
        return total;
    }

}



@AllArgsConstructor
@ToString
class SchematicNumber {
    int value;
    int row;
    int colStart;
    int colEnd;
    boolean isPartNumber;

    SchematicNumber(int value, int row, int colStart, int colEnd) {
        this(value, row, colStart, colEnd, false);
    }

    boolean isTouchedBySymbolAt(Point symbol) {
        if (List.of(symbol.x - 1, symbol.x, symbol.x + 1).contains(row)) {
            for (int schemCol = colStart; schemCol <= colEnd; schemCol++) {
                if (List.of(symbol.y - 1, symbol.y, symbol.y + 1).contains(schemCol)) {
                    return true;
                }
            }
        }
        return false;
    }
}
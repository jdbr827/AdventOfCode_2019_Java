package year_2023.day_03;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;


@AllArgsConstructor
public class Day3 {
    // 1 pass to get schematic number info and symbol locations
    List<Point> symbolLocations;
    List<SchematicNumber> schematicNumberList;

    public static void main(String[] args) {
        System.out.println(new Day3Scanner("src/year_2023/day_03/day_3_2023_small_input.txt").scanAsCharMatrix());

    }
    // then go through all symbols, marking schematic numbers that are touching


    // add all marked schematic number values
}



@AllArgsConstructor
class SchematicNumber {
    int value;
    int row;
    int colStart;
    int colEnd;
    boolean isPartNumber;

    SchematicNumber(int value, int row, int colStart, int colEnd) {
        this(value, row, colStart, colEnd, false);
    }

}
package year_2023.day_03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class Day3 {
    List<Point> symbolLocations;
    List<SchematicNumber> schematicNumberList;
    List<Point> starSymbolLocations;


    public static int day_3_part_1_2023(String filename) {
        Day3 day3 = new Day3Scanner(filename).scan();
        return day3.getSumOfPartNumbers();
    }

    public static int day_3_part_2_2023(String filename) {
        Day3 day3 = new Day3Scanner(filename).scan();
        return day3.determineAndSumGearRatios();
    }

    private int getSumOfPartNumbers() {
        return schematicNumberList.stream()
                .filter((schematicNumber -> symbolLocations.stream().anyMatch(schematicNumber::isTouchedBySymbolAt)))
                .map(SchematicNumber::getValue)
                .reduce(0, Math::addExact);
    }

    private int determineAndSumGearRatios() {
        return starSymbolLocations.stream()

                // Determine if it is a gear
                .map(starSymbolLocation ->
                    schematicNumberList.stream()
                            .filter(schematicNumber -> schematicNumber.isTouchedBySymbolAt(starSymbolLocation))
                            .collect(Collectors.toList())
                )
                .filter(schematicNumbersTouchingThisStarSymbol -> schematicNumbersTouchingThisStarSymbol.size() == 2)

                // Calculate Gear Ratio
                .map(schematicNumbersTouchingThisGear -> schematicNumbersTouchingThisGear.stream()
                        .map(SchematicNumber::getValue)
                        .reduce(1, Math::multiplyExact)
                )

                // Sum over all gear ratios
                .reduce(0, Math::addExact);
    }
}



@AllArgsConstructor
@ToString
class SchematicNumber {
    @Getter int value;
    int row;
    int colStart;
    int colEnd;

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
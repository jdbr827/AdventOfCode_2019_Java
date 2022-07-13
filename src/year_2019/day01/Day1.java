package year_2019.day01;
import java.io.FileNotFoundException;
import java.util.List;

import static utils.ReadIn.readInNumbers;

public class Day1 {


    static int part1(String filename) throws FileNotFoundException {
        List<Integer> masses = readInNumbers(filename);
        return FuelCalculator.getTotalFuelRequired(masses);
    }

    static int part2(String filename) throws FileNotFoundException {
        List<Integer> masses = readInNumbers(filename);
        return FuelCalculator.getTotalMetaFuelRequired(masses);
    }

}


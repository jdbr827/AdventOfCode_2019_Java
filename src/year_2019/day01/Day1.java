package year_2019.day01;
import java.io.FileNotFoundException;
import java.util.List;

import static utils.ReadIn.readInNumbers;

public class Day1 {


    /**
     * Computes the required fuel for an object
     * @param mass provided mass of an object
     * @return the fuel required for the object
     */
    public static int getFuelRequired(int mass) {
        return (mass / 3) - 2;
    }

    public static int getMetaFuelRequired(int mass) {
        int additionalFuel = getFuelRequired(mass);
        return additionalFuel >= 0
                ? additionalFuel + getMetaFuelRequired(additionalFuel)
                : 0;
    }

    private static int getTotalMetaFuelRequired(List<Integer> masses) {
        return masses.stream()
                .mapToInt(Day1::getMetaFuelRequired)
                .sum();
    }

    static int part2(String filename) throws FileNotFoundException {
        List<Integer> masses = readInNumbers(filename);
        return getTotalMetaFuelRequired(masses);
    }

    static int part1(String filename) throws FileNotFoundException {
        List<Integer> masses = readInNumbers(filename);
        return getTotalFuelRequired(masses);
    }

    private static int getTotalFuelRequired(List<Integer> masses) {
        return masses.stream()
                .mapToInt(Day1::getFuelRequired)
                .sum();
    }
}

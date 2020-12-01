package year_2019;
import java.io.FileNotFoundException;
import java.util.List;

import static utils.ReadIn.readInNumbers;

public class Day1 {
    public static final String INPUT_FILENAME = "C:\\Users\\Jake\\IdeaProjects\\AdventOfCode_2020\\src\\year_2019\\input_aoc_2019_1.txt";

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


    public static void test() {

        /* Part 1 */
        System.out.println(getFuelRequired(12) == 2);
        System.out.println(getFuelRequired(14) == 2);
        System.out.println(getFuelRequired(1969) == 654);
        System.out.println(getFuelRequired(100756) == 33583);

        /* Part 2 */
        System.out.println(getMetaFuelRequired(14) == 2);
        System.out.println(getMetaFuelRequired(1969) == 966);
        System.out.println(getMetaFuelRequired(100756) == 50346);
    }

    public static void main(String[] args) throws FileNotFoundException {
        test();
        List<Integer> masses = readInNumbers(INPUT_FILENAME);

        /* Part 1 */
        int cargoFuel = masses.stream()
                .map(Day1::getFuelRequired)
                .mapToInt(Integer::intValue).sum();
        System.out.println(cargoFuel == 3150224);

        /* Part 2 */
        int fuelFuel = masses.stream()
                .map(Day1::getMetaFuelRequired)
                .mapToInt(Integer::intValue).sum();
        System.out.println(fuelFuel == 4722484);




    }
}

package year_2019.day01;

import java.util.List;

public class FuelCalculator {

    /**
     * Computes the required fuel for an object
     *
     * @param mass provided mass of an object
     * @return the fuel required for the object
     */
    public static int getFuelRequired(int mass) {
        return (mass / 3) - 2;
    }

    static int getTotalFuelRequired(List<Integer> masses) {
        return masses.stream()
                .mapToInt(FuelCalculator::getFuelRequired)
                .sum();
    }



    public static int getMetaFuelRequired(int mass) {
        int fuel = getFuelRequired(mass);
        return fuel >= 0 ? fuel + getMetaFuelRequired(fuel) : 0;
    }

    static int getTotalMetaFuelRequired(List<Integer> masses) {
        return masses.stream()
                .mapToInt(FuelCalculator::getMetaFuelRequired)
                .sum();
    }



}

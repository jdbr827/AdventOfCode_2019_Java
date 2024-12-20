package year_2024.day_03;

public class Day3 {

    int sumOfProducts;

    public Day3(String smallInputFilename) {
        sumOfProducts = new Day3Scanner(smallInputFilename).scan();
    }

    public int sumValidOps() {
        return sumOfProducts;
    }
}

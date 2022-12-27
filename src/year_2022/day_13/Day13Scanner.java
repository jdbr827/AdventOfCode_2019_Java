package year_2022.day_13;

import utils.AOCScanner;

import java.io.FileNotFoundException;

public class Day13Scanner extends AOCScanner {


    public Day13Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }


    public void scanStuff() {

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());
            System.out.println("-------");
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // should be blank;
            }
        }
    }
}

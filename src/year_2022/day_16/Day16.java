package year_2022.day_16;

import java.io.FileNotFoundException;
import java.util.Collection;

public class Day16 {

    public static String part1(String fileName) throws FileNotFoundException {
        Day16Scanner myScanner = new Day16Scanner(fileName);

        Collection<Valve> valveGraph = myScanner.scanAll();

        return valveGraph.toString();

    }
}

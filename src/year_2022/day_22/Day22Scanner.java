package year_2022.day_22;

import utils.AOCScanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day22Scanner extends AOCScanner {
    public Day22Scanner(String fileName) throws FileNotFoundException {
        super(fileName);
    }



    public List<String> readInDiagram() {
        List<String> diagram = new ArrayList<>();
        String thisLine;
        while (!(thisLine = scanner.nextLine()).equals("")) {
         //System.out.println(thisLine);
         diagram.add(thisLine);
        }
        return diagram;
    }
}

package year_2022.day_20;

import utils.AOCScanner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Day20Scanner extends AOCScanner {
    public Day20Scanner(String fileName) {
        super(fileName);
    }

    List<Integer> createListToMix() {
        List<Integer> lst = new ArrayList<>();
        while (scanner.hasNextLine()) {
            Integer num = Integer.parseInt(scanner.nextLine());
            if (lst.contains(num)) {
                System.out.println("Duplicate of " + num);
            }
            lst.add(num);
        }
        return lst;
    }

}

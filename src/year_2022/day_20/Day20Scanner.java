package year_2022.day_20;

import utils.AOCScanner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Day20Scanner extends AOCScanner {
    public Day20Scanner(String fileName) {
        super(fileName);
    }

    List<Long> createListToMix() {
        List<Long> lst = new ArrayList<>();
        while (scanner.hasNextLine()) {
            Long num = Long.parseLong(scanner.nextLine());
            lst.add(num);
        }
        return lst;
    }

}

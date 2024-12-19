package year_2024.day_01;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;

import java.util.ArrayList;
import java.util.List;

public class Day1Scanner extends AOCScanner {

    public Day1Scanner(String fileName) {
        super(fileName);
    }

    public Pair<List<Integer>, List<Integer>> scan() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();


        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] splt = data.split("\\s+");
            list1.add(Integer.parseInt(splt[0]));
            list2.add(Integer.parseInt(splt[1]));
        }
        return Pair.of(list1, list2);
    }
}

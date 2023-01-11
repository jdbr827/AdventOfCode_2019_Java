package year_2022.day_18;

import utils.AOCScanner;
import utils.ReadIn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18Scanner extends AOCScanner {
    static final Pattern obisidianPattern = Pattern.compile("([0-9]*),([0-9]*),([0-9]*)");

    public Day18Scanner(String fileName) {
        super(fileName);
    }

    public List<Integer> nextObsidianDroplet() {
        if (scanner.hasNextLine()) {
            Matcher m = obisidianPattern.matcher(scanner.nextLine());
            ReadIn.findOrElseThrow(m, "Could not read in RPS Strategy info");
            return List.of(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
        }
        return List.of();
    }


}

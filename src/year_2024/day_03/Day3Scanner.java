package year_2024.day_03;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Scanner extends AOCScanner {
    static final String multOpRegex = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)";
    static final String doOpRegex = "do\\(\\)";
    static final String dontOpRegex = "don\\'t\\(\\)";
    static final Pattern multOpPattern = Pattern.compile(multOpRegex);
    static final Pattern OpPattern = Pattern.compile(multOpRegex + "|" + doOpRegex + "|" + dontOpRegex);

    public Day3Scanner(String fileName) {
        super(fileName);
    }


    public List<Pair<Integer, Integer>> scan() {
        AtomicInteger tot = new AtomicInteger();
        List<Pair<Integer, Integer>> operations = new LinkedList<>();
        forEachLine((String line) -> {
            Matcher opMatcher = OpPattern.matcher(line);
            while (opMatcher.find()) {
                //System.out.println("FOUND ONE!");
                String found = line.substring(opMatcher.start(), opMatcher.end());
                Matcher multOpMatcher = multOpPattern.matcher(found);
                if (multOpMatcher.matches()) {
                    operations.add(new Pair<>(Integer.parseInt(multOpMatcher.group(1)), Integer.parseInt(multOpMatcher.group(2))));
                } else if (found.equals("do()")) {
                    operations.add(new Pair<>(-1, 1));
                } else { // found is "don't()" {
                    operations.add(new Pair<>(-1, -1));
                }
            }
        });
        return operations;
    }
}

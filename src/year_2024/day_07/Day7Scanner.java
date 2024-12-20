package year_2024.day_07;

import org.testng.internal.collections.Pair;
import utils.AOCScanner;
import utils.ReadIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Scanner extends AOCScanner {

    static final Pattern equationPattern = Pattern.compile("(\\d+): ([\\d+\\s]+)");

    public Day7Scanner(String fileName) {
        super(fileName);
    }

    public Collection<Pair<Long, List<Long>>> scan() {
        List<Pair<Long, List<Long>>> equations = new ArrayList<>();
        forEachLine(line -> {
            Matcher equationMatcher = equationPattern.matcher(line);
            ReadIn.findOrElseThrow(equationMatcher, "Cannot read equation regex for line " + line);
            List<Long> parameters = Arrays.stream(equationMatcher.group(2).split("\\s")).map(Long::parseLong).toList();
            equations.add(Pair.of(Long.parseLong(equationMatcher.group(1)), parameters));
        });
        return equations;

    }
}

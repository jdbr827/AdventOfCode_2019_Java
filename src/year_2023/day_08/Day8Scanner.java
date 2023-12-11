package year_2023.day_08;

import utils.AOCScanner;
import utils.ReadIn;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8Scanner {

    static final Pattern mapLinePattern = Pattern.compile("([\\w]{3}) = \\(([\\w]{3}), ([\\w]{3})\\)");

    public static Day8 scan(String filename) {
        AOCScanner scanner = new AOCScanner(filename);

        String instructions = scanner.nextLine();

        scanner.nextLine(); // blank

        Map<String, Map<Character, String>> desertMap = new HashMap<>();

        scanner.forEachLine(line -> {
            Matcher m = mapLinePattern.matcher(line);
            ReadIn.findOrElseThrow(m, "Could not read in map line information");
            String key = m.group(1);
            String left = m.group(2);
            String right = m.group(3);
            desertMap.put(key, Map.of('L', left, 'R', right));
        });

        return new Day8(desertMap, instructions);
    };
}

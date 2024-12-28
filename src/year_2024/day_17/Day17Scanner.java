package year_2024.day_17;

import utils.AOCScanner;
import utils.ReadIn;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17Scanner extends AOCScanner {

    static final Pattern registerAPattern = Pattern.compile("Register A: (\\d+)");
    static final Pattern registerBPattern = Pattern.compile("Register B: (\\d+)");
    static final Pattern registerCPattern = Pattern.compile("Register C: (\\d+)");
    static final Pattern programPattern = Pattern.compile("Program: ([0-7][,[0-7]]*)");

    public Day17Scanner(String fileName) {
        super(fileName);
    }

    public Day17 scan() {
        String lineA = scanner.nextLine();
        Matcher mA = registerAPattern.matcher(lineA);
        ReadIn.findOrElseThrow(mA, "Could not read register A Pattern: \"" + lineA + "\"");

        String lineB = scanner.nextLine();
        Matcher mB = registerBPattern.matcher(lineB);
        ReadIn.findOrElseThrow(mB, "Could not read register B Pattern: \"" + lineB + "\"");

        String lineC = scanner.nextLine();
        Matcher mC = registerCPattern.matcher(lineC);
        ReadIn.findOrElseThrow(mC, "Could not read register C Pattern: \"" + lineC + "\"");

        scanner.nextLine(); // blank line before the program

        String program = scanner.nextLine();
        Matcher programMatcher = programPattern.matcher(program);
        ReadIn.findOrElseThrow(programMatcher, "Could not read program Pattern: \"" + program + "\"");

        return new Day17(Integer.parseInt(mA.group(1)), Integer.parseInt(mB.group(1)), Integer.parseInt(mC.group(1)),
                Arrays.stream(programMatcher.group(1).split(",")).map(Integer::parseInt).toList());
    }
}
